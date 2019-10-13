package superbook.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;
import superbook.bean.User;
import superbook.dao.UserDao;
import superbook.servlet.BaseServlet;
import superbook.util.RedisUtil;
import superbook.util.UserUtil;

/**
 * Servlet Filter implementation class UserPowerFilter
 * @author 再几何
 * 用户鉴权
 */
@WebFilter("/UserPowerFilter")
public class UserPowerFilter implements Filter {
	private static RedisUtil redis = new RedisUtil();
    /**
     * Default constructor. 
     */
    public UserPowerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		
		String contextPath = req.getServletContext().getContextPath();
		String uri = req.getRequestURI();//形如/test/2432424.jsp?asdasd
		System.out.println(uri);
		uri = StringUtils.remove(uri, contextPath);
		
		if(uri.startsWith("/admin")) {//用户鉴权
			//获取数据code，从请求头中获取
			String uuid = (String) req.getParameter("uuid");//存在于web端和服务器端 req.getAttribute(name)只在web组件端
			
			System.out.println("uuid " + uuid);
			//判断是否存在于redis中
			if(redis.existsKey(uuid)) {//包含
				int uid = Integer.parseInt(new UserDao().selectUid(uuid));
				req.setAttribute("uid", uid);
				chain.doFilter(req, rep);
				return;
			} else {
				JSONObject json = first(req);
				if(json.getBoolean("flag")) {
				uuid = json.getString("uuid");
				write(rep,uuid);
				return;
				} else {
					write(rep,"微信服务器请求失败");
					return;
				}
			}
		}//if
		chain.doFilter(request,response);
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	//	返回书写信息
	public static void write(HttpServletResponse response, String data) {
		try {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;character=utf-8");
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
		}catch(IOException e) {
			System.out.println("返回信息失败！");
		}
	}
	
	public static JSONObject first(HttpServletRequest req) {//判断是否是第一次，就是未鉴权
		
		JSONObject result = new JSONObject();
		//获取数据code，从请求头中获取
		String code = (String) req.getParameter("code");
		//连接微信服务器进行获取数据，openid和session_key
		JSONObject json = JSONObject.fromObject(new UserUtil().getUser(code));
		if(json.containsKey("errcode")) {//获取失败
			result.put("flag", false);
			return result;
		}
		
		//存储到redis
		System.out.println("jixuzhixing");
		String openid = json.getString("openid");
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();//获取uuid
		redis.setUser(uuid, openid);
		System.out.println("存取成功");
		
		//存储到mysql
		 //获取nickName
		Map<String, Object> map = (Map)new BaseServlet().getJSONParameter(req);
		String nickName = (String) map.get("nickName");
		System.out.println(nickName);
		User user = new UserDao().selectUser(openid);
		if(user == null) {//数据库中不存在，第一次登陆
			new UserDao().add(openid, nickName, uuid);
			System.out.println("cundao");
			result.put("flag", true);
			result.put("uuid", uuid);
			return result;
		} else {
			System.out.println("heheh");
			result.put("flag", true);
			result.put("uuid", user.getUuid());
			return result;
		}
		
	}

}
