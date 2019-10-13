package superbook.servlet;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import superbook.bean.Orders;
import superbook.bean.Product;
import superbook.dao.BookDao;
import superbook.dao.OrdersDao;
import superbook.dao.ProductDao;
import superbook.dao.ReviewDao;



@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	

	
	//首页展示书籍
	public void showIndex() {}
	
	
	/**
	 * 测试完成
	 * 分类展示书籍
	 * 参数: cid
	 * 前端: product+book
	 * @param request
	 * @param response
	 */
	public void showByCid(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map)getJSONParameter(request);
		//根据cid获取书籍 
		int cid = (int)map.get("cid");
		List<Product> list = new ProductDao().selectByCid(cid);
		for(Product p: list) {
			System.out.println(p.toString());
		}
		System.out.println(list.size());
		System.out.println("**************");
		JSONArray jsonArray = new JSONArray();
		for(Product product : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("product", product);
			jsonObject.put("book", (new BookDao().selectByIsbn(product.getIsbn())));
			jsonArray.add(jsonObject);
		}
		write(response,jsonArray.toString());
	}
	
	
	/**
	 * 测试完成
	 * 获取书籍详情
	 * 参数: pid
	 * 前端: book + product + review
	 * @param request
	 * @param response
	 */
	public void showBookMessage(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		//根据pid找出product 与 book 信息
		Integer pid = (Integer)map.get("pid");
		Product product = new ProductDao().selectByPid(pid);
		JSONObject json = new JSONObject();
		json.put("product", product);
		json.put("book", (new BookDao().selectByIsbn(product.getIsbn())));
		json.put("review", (new ReviewDao().selectByPid(pid)));
		write(response, json.toString());
	}
	
	/**
	 * 测试完成
	 * 买书
	 * 参数: pid, phone, userMessage
	 * 前端: true
	 * @param request
	 * @param response
	 */
	public void buyBook(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		//根据pid找到订单
		int pid = (int) map.get("pid");
		Orders order = new OrdersDao().selectByPid(pid);
		//完善订单: createTime, 订单状态, 收获人, 手机号, 
		order.setCreateTime(new Date());
		order.setOrderState(3);
		order.setReceiver((Integer)request.getAttribute("uid"));
		//order.setReceiver(3);
		order.setPhone((String)map.get("phone"));
		order.setUserMessage((String)map.get("userMessage"));
		//更新订单信息
		new OrdersDao().updateOrder(order);
		//返回前端信息
		JSONObject json = new JSONObject();
		json.put("flag", "true");
		write(response, json.toString());
	}
	
	/**
	 * 支付
	 * 参数: pid
	 * 前端: true
	 * @param request
	 * @param response
	 */
	public void payBook(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		//根据pid找到订单
		int pid = (int) map.get("pid");
		Orders order = new OrdersDao().selectByPid(pid);
		//修改订单状态,支付日期
		order.setPayDate(new Date());
		order.setOrderState(4);
		//更新订单信息
		new OrdersDao().updateOrder(order);
		//返回前端信息
		JSONObject json = new JSONObject();
		json.put("flag", "true");
		write(response, json.toString());
	}
	
	/**
	 * 测试完
	 * 出货
	 * 参数:pid
	 * @param request
	 * @param response
	 */
	public void deliverBook(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		//根据pid找到订单
		int pid = (int) map.get("pid");
		Orders order = new OrdersDao().selectByPid(pid);
		//修改订单状态,箱子号码,发货日期
		new OrdersDao().changeBid(order.getOid(), 1);  //将箱子号码设为1
		new OrdersDao().changeState(order.getOid(), 5);
		new OrdersDao().changedeliverDate(order.getOid(), new Date());
		//返回前端信息
		JSONObject json = new JSONObject();
		json.put("flag", "true");
		write(response, json.toString());
	}
	
	/**
	 * 测试完
	 * 确认收货
	 * 参数: pid
	 * @param request
	 * @param response
	 */
	public void confirmBook(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		//根据pid找到订单
		int pid = (int) map.get("pid");
		Orders order = new OrdersDao().selectByPid(pid);
		//修改订单状态,收获日期 (后期--改箱子状态)
		new OrdersDao().changeState(order.getOid(), 6);
		new OrdersDao().changeconfirmDate(order.getOid(), new Date());
		//返回前端信息
		JSONObject json = new JSONObject();
		json.put("flag", "true");
		write(response, json.toString());
	}
	
	/**测试完
	 * 根据uid返回他卖的订单
	 * 参数: 无
	 * @param request
	 * @param response
	 */
	public void showSellOrders(HttpServletRequest request, HttpServletResponse response) {
		//获取uid
		int uid = (int)request.getAttribute("uid");
		//int uid = 1;
		List<Orders> list = new OrdersDao().selectByUid(uid);
		JSONArray jsonArray = new JSONArray();
		for(Orders order: list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("order", order);
			Product product = (new ProductDao().selectByPid(order.getPid()));
			jsonObject.put("product", product);
			jsonObject.put("book", new BookDao().selectByIsbn(product.getIsbn()));
			jsonArray.add(jsonObject);
		}
		write(response,jsonArray.toString());
	}
	
	/**
	 * 测试完
	 * 根据uid返回他买的订单
	 * 参数: 无
	 * @param request
	 * @param response
	 */
	public void showBuyOrders(HttpServletRequest request, HttpServletResponse response) {
		//获取uid
		int receiver = (int)request.getAttribute("uid");
		List<Orders> list = new OrdersDao().selectByReceiver(receiver);
		JSONArray jsonArray = new JSONArray();
		System.out.println("******************");
		for(Orders order: list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("order", order);
			Product product = (new ProductDao().selectByPid(order.getPid()));
			jsonObject.put("product", product);
			jsonObject.put("book", new BookDao().selectByIsbn(product.getIsbn()));
			jsonArray.add(jsonObject);
		}
		write(response,jsonArray.toString());
	}
	
	
}
