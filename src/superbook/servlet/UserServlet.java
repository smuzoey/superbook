package superbook.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import superbook.bean.User;
import superbook.dao.UserDao;
import superbook.util.RedisUtil;
import superbook.util.UserUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * 登陆,获取openid等放在redis和mysql等
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 所需参数：nickname、（code）、uuid
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RedisUtil redis = new RedisUtil();
		
		//获取数据code，从请求头中获取
		String code = (String) request.getParameter("code");
		System.out.println("code " + code);
		//连接微信服务器进行获取数据，openid和session_key
		JSONObject json = JSONObject.fromObject(new UserUtil().getUser(code));
		if(json.containsKey("errcode")) {//获取失败
			write(response," shibaile ");
			return;
		}
		
		//存储到redis
		System.out.println("jixuzhixing");
		String openid = json.getString("openid");
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();//获取uuid
		redis.setUser(uuid, openid);
		System.out.println("存取成功");
		
		//存储到mysql
		 //获取nickName
		Map<String, Object> map = (Map)getJSONParameter(request);
		String nickName = (String) map.get("nickName");
		System.out.println(nickName);
		User user = new UserDao().selectUser(openid);
		if(user == null) {//数据库中不存在，第一次登陆
			new UserDao().add(openid, nickName,uuid);
			System.out.println("cundao");
			write(response,uuid);
			return;
		} else {
			System.out.println("heheh");
			write(response,"yicunzai");
			return;
		}
	}

}
