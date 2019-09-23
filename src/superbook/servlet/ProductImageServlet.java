package superbook.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import superbook.bean.ProductImage;
import superbook.util.ImageUtil;


/**
 * Servlet implementation class ProductImageServlet
 */
@WebServlet(urlPatterns = "/ProductImageServlet")
public class ProductImageServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Yes");
		//获取文件流
		InputStream is = null;
		
		//参数
		Map<String, String> params = new HashMap<>();
		
		//解析上传参数
		is = parseUpload(request,params);
		
		//根据上传的参数生成productImage对象
		ProductImage img = new ProductImage();
		String type = params.get("type");
		int pid = Integer.parseInt(params.get("pid"));
		
		img.setPid(pid);
		img.setType(type);
		
		//创建文件保存路径
		String fileName =img.getPid()+".jpg";
		String imgFolder;
		String imgFolder_small = null;
		String imgFolder_middle = null;
		
		imgFolder = request.getSession().getServletContext().getRealPath("img/productSingle");
		System.out.println(imgFolder);
		imgFolder_small = request.getSession().getServletContext().getRealPath("img/productSingle_small");
		System.out.println(imgFolder);
		imgFolder_middle = request.getSession().getServletContext().getRealPath("img/productSingle_middle");
		System.out.println(imgFolder);
		
		 File f = new File(imgFolder, fileName);
	     f.getParentFile().mkdirs();//文件不存在的时候，进行创建
	     //将内容文件写入
	     try {
	    	 if(is != null && 0!=is.available()) {
	    		 try {
	    			 FileOutputStream fos = new FileOutputStream(f);
	    			 byte[] b = new byte[1024*1024];
	    			 int length =0;
	    			 while((length = is.read(b))!= -1) {
	    				 fos.write(b, 0, length);
	    			 }
	    			 fos.flush();
	    			//创建缩略图文件
	    			File f_small = new File(imgFolder_small, fileName);
			        File f_middle = new File(imgFolder_middle, fileName);
			        
			        //文件进行压缩
			        ImageUtil.resizeImage(f, 56, 56, f_small);
			        ImageUtil.resizeImage(f, 217, 190, f_middle);
	    			 
	    		 }catch(Exception e) {
	    			 e.printStackTrace();
	    		 }
	    	 }
	     }catch (IOException e) {
	    	 e.printStackTrace();
	     }
		
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取文件流
		InputStream is = null;
		
		//参数
		Map<String, String> params = new HashMap<>();
		
		//解析上传参数
		is = parseUpload(request,params);
		
		int id = Integer.parseInt(params.get("id"));//获取图片编号
		System.out.println(params.get("id"));
		
		//创建文件路径
		String imgFolder_single = request.getSession().getServletContext().getRealPath("img/productSingle");
		String imgFolder_small = request.getSession().getServletContext().getRealPath("img/productSingle_small");
		String imgFolder_middle = request.getSession().getServletContext().getRealPath("img/productSingle_middle");
		
		//创建文件
		File f_single = new File(imgFolder_single,id+".jpg");
		f_single.delete();
		File f_small = new File(imgFolder_small,id+".jpg");
		f_small.delete();
		File f_middle = new File(imgFolder_middle,id+".jpg");
		f_middle.delete();
		
		
	}

}
