package superbook.servlet;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class BaseServlet
 */
@WebServlet(
		urlPatterns="/BaseServlet"
		)
public class BaseServlet extends HttpServlet {
	
	//将文件流和其他参数分开
	public InputStream parseUpload(HttpServletRequest request, Map<String ,String> params) {
		InputStream  is = null; 
		
		//使用Apache文件上传组件处理文件上传步骤： 
		try {
			//1创建文件上传工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
	        factory.setSizeThreshold(1024 * 1024);
	        
	        //2、创建文件上传解析器
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        //解析请求参数，封装为list
	        List items = upload.parseRequest(request);
	        Iterator iter = items.iterator();//创建迭代器
	        
	        while(iter.hasNext()) {
	        	FileItem item = (FileItem) iter.next();
	        	if(!item.isFormField()) {//不是普通表单类型
	        		is = item.getInputStream();
	        	} else {//普通的表单类型
	        		String paramName = item.getFieldName();//键
	        		String paramValue = item.getString();//值
	        		//tomcat容器默认采用了iso-8859-1的编码方法
	        		//通过本为UTF-8编码却被tomcat用iso-8859-1解码的字进行恢复，
	        		//其将解码后的字通过iso-8859-1反解码成二进制数组，再将该字节数组用UTF-8解码。
	        		//最终被new String成字符串。
	        		paramValue = new String(paramValue.getBytes("ISO-8859-1"),("utf-8"));
	        		params.put(paramName, paramValue);
	        	}
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return is;
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		/*借助反射，调用对应的方法*/
		String method = (String) req.getAttribute("method");
		System.out.println(this.toString());
		Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
				javax.servlet.http.HttpServletResponse.class);//获取方法
		System.out.println(this.toString());
		        m.invoke(this,req, resp);//执行方法
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}