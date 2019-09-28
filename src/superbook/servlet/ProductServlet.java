package superbook.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import superbook.bean.Orders;
import superbook.bean.Product;
import superbook.dao.OrdersDao;
import superbook.dao.ProductDao;

/** 
 * Servlet implementation class ProductServlet
 */
@WebServlet(
		urlPatterns = "/ProductServlet"
		)
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
//  添加产品，卖书
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
//	    1、解析传入参数
//	    2、调用图片存储，并获取地址
//	    3、根据ISBN码创建书籍信息
//	    4、创建产品信息，包括图片存储位置
//	    5、创建产品的箱子地址
		JSONObject json = getJSONParameter(request);
		
		//新建Product类并加入数据库
		System.out.println("********");
		Product p = new Product();
	    p.setIsbn(json.getString("isbn"));
	    p.setCid(json.getInt("cid"));
	    Date date = new Date();
	    p.setCreateDate(date);
	    p.setSubTitle(json.getString("subTitle")); 
	    p.setPromotePrice(json.getDouble("promotePrice"));
	    p.setDegree(json.getInt("degree"));
	    ProductDao pd = new ProductDao();
	    p.setPid(pd.add(p));
	    
	    //新建Orders类并加入数据库
	    Orders o = new Orders();
	    o.setBid(1);  //设置箱子号码
	    o.setUid(json.getInt("uid"));
	    o.setOrderState(0);  //订单状态为0，未出售
	    o.setPid(p.getPid());
	    OrdersDao od = new OrdersDao();
	    od.add(o); 
	    System.out.println(p);
	    
	    //	          返回信息
	    write(response,"hhhh");
		
	}
	
	public void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		
	}
}
