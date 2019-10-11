package superbook.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import superbook.bean.Book;
import superbook.bean.OrderItem;
import superbook.bean.Orders;
import superbook.bean.Product;
import superbook.dao.BookDao;
import superbook.dao.OrderItemDao;
import superbook.dao.OrdersDao;
import superbook.dao.ProductDao;
import superbook.util.BookUtil;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(
		urlPatterns = "/ProductServlet"
		)
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * 卖书(将信息存入数据库中)
	 * 所需参数:isbn,cid,promotePrice,subTitle,degree,uid
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
//	    1、解析传入参数
//	    2、调用图片存储，并获取地址
//	    3、根据ISBN码创建书籍信息
//	    4、创建产品信息，包括图片存储位置
//	    5、创建产品的箱子地址
//		JSONObject json = getJSONParameter(request);
		
		//解析传入参数

        Map<String,Object> map = (Map)getJSONParameter(request);

        /*********************添加到数据库**************************/
        //添加product
        Product product = (Product) getBean(map, "Product");
		product.setCreateDate(new Date());
		(new ProductDao()).add(product);
		System.out.println("***************" + product.toString());
		
		//添加Orders
		Orders orders = (Orders)getBean(map, "Orders");
		orders.setPid((new ProductDao()).getPid());
		(new OrdersDao()).add(orders);

		
		//添加book
		Book book = (new BookDao()).selectByIsbn(product.getIsbn());
		if(book == null) {  //如果不在数据库Book中
			System.out.println("ProductServlet.add(书籍在数据库Book中没有)");
			
			//调用ShowAPI找到书籍信息,并添加到数据库
			book = (new BookUtil()).getBook(product.getIsbn());
			(new BookDao()).add(book);
		
		} else { //在数据库Book中
			System.out.println("ProductServlet.add(书籍在数据库Book中存在)");
			System.out.println("ProductServlet.add " + book.toString());
		}
		System.out.println(product.toString());
		System.out.println(orders.toString());
	    write(response," c");
		
	}
	
	/**
	 * 删除书籍 暂时为空
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {}
	
	
	/**
	 * 首页展示(根据类别cid展示书籍)
	 * 所需参数:cid
	 * @param request
	 * @param response
	 */
	public void showOrderListByCategory(HttpServletRequest request, HttpServletResponse response) {
		//解析传入参数
		Map<String, Object> map = (Map)getJSONParameter(request);
		
		//根据cid获取书籍
		int cid = (int)map.get("cid");
		System.out.println("ProductServlet.showOrderListByCategory: " + cid);
		List<Product> list = new ProductDao().selectByCid(cid);
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
	 * 搜索框（根据书名，返回书籍订单列表）
	 * 所需参数：bookname，
	 * 返回参数：
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showOrderListByName(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		//解析上传参数
		Map<String, Object> map = (Map)getJSONParameter(request);
		//1 根据书名获取ISBN
		String title = (String) map.get("title");
		System.out.println(title);
		Book book = new BookDao().selectByBookTitle(title);
		//2 根据ISBN获取产品号（Pid）
		List<Product> product = new ProductDao().selectListByISBN(book.getIsbn());
		//3 返回信息
		JSONObject json = new JSONObject();
		json.put("book", book);
		json.put("product", product);
		write(response,json.toString());	
	}
	
	
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
	 * 所需参数: uid
	 * @param request
	 * @param response
	 */
	public void showCart(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		Integer uid = (Integer)map.get("uid");
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
	

	/**
	 * 书籍详情
	 * 所需参数: pid
	 * @param request
	 * @param response
	 */
	public void showBook(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		//根据pid找出product 与 book 信息
		Integer pid = (Integer)map.get("pid");
		Product product = new ProductDao().selectByPid(pid);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("product", product);
		jsonObject.put("book", (new BookDao().selectByIsbn(product.getIsbn())));
		write(response, jsonObject.toString());
	}
	
	/**
	 * 扫描书籍
	 * 所需参数: isbn
	 * @param request
	 * @param response
	 */
	public void scanBook(HttpServletRequest request, HttpServletResponse response) {
		//解析上传参数
		Map<String, Object> map = (Map) getJSONParameter(request);
		String isbn = (String)map.get("isbn");
		//根据isbn返回书籍详细信息
		Book book = new BookDao().selectByIsbn(isbn);
		if(book == null) {  //如果不在数据库Book中
			System.out.println("ProductServlet.add(书籍在数据库Book中没有)");
			//调用ShowAPI找到书籍信息,并添加到数据库
			book = (new BookUtil()).getBook(isbn);
			(new BookDao()).add(book);
		
		} else { //在数据库Book中
			System.out.println("ProductServlet.add(书籍在数据库Book中存在)");
			System.out.println("ProductServlet.add " + book.toString());
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("book", book);
		write(response, jsonObject.toString());
		
	}
	
	
}
