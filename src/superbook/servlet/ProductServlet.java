package superbook.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import superbook.bean.Product;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(
		urlPatterns = "/ProductServlet"
		)
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
//  添加产品，卖书
	public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
//	    1、解析传入参数
//	    2、调用图片存储，并获取地址
//	    3、根据ISBN码创建书籍信息
//	    4、创建产品信息，包括图片存储位置
//	    5、创建产品的箱子地址
//		JSONObject json = getJSONParameter(request);
	    //第一种方式  
        Map<String,Object> map = (Map)getJSONParameter(request);;  
//        System.out.println("这个是用JSON类来解析JSON字符串!!!");  
//        for (Object map : maps.entrySet()){  
//            System.out.println(((Map.Entry)map).getKey().getClass().getName()+"     " + 
//        ((Map.Entry)map).getValue().getClass().getName());  
//        } 
        Product product = (Product) getBean(map, "Product");
		System.out.println(product.toString());
		String a = (String) map.get("asd");
		System.out.println(a);
//	          返回信息
	    write(response," c");
		
	}
	
	public void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		
	}
}
