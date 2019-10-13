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
	 * 添加评论
	 * 参数:评论内容content + pid + uid
	 * @param request
	 * @param response
	 */
	public void addReview(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		Review review = (Review) getBean(map, "Review");
		review.setCreateDate(new Date());
		//添加到数据库
		new ReviewDao().add(review);
		System.out.println("****");
		JSONObject json = new JSONObject();
		json.put("flag", "true");
		write(response, json.toString());
	}
}
