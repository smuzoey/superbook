package superbook.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import superbook.bean.OrderItem;
import superbook.bean.Product;
import superbook.dao.BookDao;
import superbook.dao.OrderItemDao;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(
		urlPatterns = "/ProductServlet"
		)
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	


	
	/**
	 * 商品加入购物车
	 * 所需参数: oid,uid
	 * @param request
	 * @param response
	 */
	public void addToCart(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		OrderItem orderItem = (OrderItem) getBean(map, "OrderItem");
		System.out.println(orderItem.toString());
		//添加到购物车数据库,由于每个product只有一个,所以num默认为1,如果此订单已经存在,则不添加
		orderItem.setNumber(1);
		new OrderItemDao().add(orderItem);
	}
	
	/**
	 * 商品从购物车删除
	 * 所需参数: oid,uid
	 * @param request
	 * @param response
	 */
	public void deleteFromCart(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		OrderItem orderItem = (OrderItem) getBean(map, "OrderItem");
		new OrderItemDao().delete(orderItem.getUid(), orderItem.getOid());
	} 
	
	
	/**
	 * 展示购物车内容
	 * 所需参数: uid(不需要给)
	 * @param request
	 * @param response
	 */
	public void showCart(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		Integer uid = (Integer)request.getAttribute("uid");
		//返回此uid的全部订单
		List<Product> list = new OrderItemDao().selectByUid(uid);
		JSONArray jsonArray = new JSONArray();
		//遍历订单并将Product与book信息拼接
		for(Product product : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("product", product);
			jsonObject.put("book", (new BookDao().selectByIsbn(product.getIsbn())));
			jsonArray.add(jsonObject);
		}
		write(response,jsonArray.toString());
	}	
	

}
