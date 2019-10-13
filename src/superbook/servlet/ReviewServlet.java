package superbook.servlet;

import java.util.Date;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import superbook.bean.Review;
import superbook.dao.ReviewDao;



@WebServlet("/ReviewServlet")
public class ReviewServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	
	/**
	 * 已测试
	 * 添加评论
	 * 参数:评论内容content + pid + uid
	 * @param request
	 * @param response
	 */
	public void addReview(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		int uid = (int)request.getAttribute("uid");
		//int uid = 5;
		Review review = new Review();
		review.setUid(uid);
		review.setPid((int)map.get("pid"));
		review.setContent((String)map.get("content"));
		review.setCreateDate(new Date());
		//添加到数据库
		new ReviewDao().add(review);

		//返回给前端
		JSONObject json = new JSONObject();
		json.put("flag", "true");
		write(response, json.toString());
	}
	
	/**
	 * 已测试
	 * 删除评论
	 * 参数
	 * @param request
	 * @param response
	 */
	public void deleteReview(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		int uid = (int)request.getAttribute("uid");
		//int uid = 5;
		int pid = (int) map.get("pid");
		new ReviewDao().delete(pid, uid);
		
		//返回给前端
		JSONObject json = new JSONObject();
		json.put("flag", "true");
		write(response, json.toString());
	}
}
