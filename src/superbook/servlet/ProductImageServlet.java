package superbook.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
		System.out.println(id);
		
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
	
	/**
	 * 向前端返回图片，所需数据 pid：图片编号   piid 和type 在数据库中获得
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void show(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
		//获取请求参数
		InputStream is = null;
		Map<String,String> params = new HashMap<>();
		
		//解析上传参数
		is = parseUpload(req,params);
		String type = params.get("type");//获取图片编号
		int id = Integer.parseInt(params.get("pid"));
		
		if(type == null || type.equals("")) return;
		
		//创建文件路径
		String path = null;
		switch (type) {
		case "imgFolder_single" : 
			path = req.getSession().getServletContext().getRealPath("img/productSingle");
		    break;
		case "imgFolder_small" : 
			path = req.getSession().getServletContext().getRealPath("img/productSingle_small");
            break;		
		case "imgFolder_middle" : 
			path = req.getSession().getServletContext().getRealPath("img/productSingle_middle");
		    break;
		}
		
		//创建文件
		File f = new File(path,id+".jpg");
		System.out.println(path+id +".jpg");
		InputStream  imgis = new FileInputStream(f);
		int count = imgis.available();
		byte[]  data = new byte[count];  
		resp.setHeader("Content-Type","image/jpeg");
		imgis.read(data);
		OutputStream out = resp.getOutputStream();
		out.write(data);
		out.flush();
		out.close();
		imgis.close();
	}

}
