package superbook.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author 再几何
 *
 *通过ISBN码调用书籍的信息
 */
public class BookUtil {
	//请求参数
	private String showapi_appid = "94112";
	 //数字签名，可以在你的个人中心看到
	private String showapi_sign =  "9a147a4260a34c9c8c4b304decf73744";
	
	public String getBook(String isbn) {
		StringBuffer buff = new StringBuffer();//保存信息
		
		//拼接URL,形如http://route.showapi.com/1626-1?showapi_appid=94112&isbn=9787208061644&showapi_sign=9a147a4260a34c9c8c4b304decf73744
		String httpUrl = String.format("http://route.showapi.com/1626-1?showapi_appid=%s&isbn=%s&showapi_sign=%s", showapi_appid,isbn,showapi_sign);
		
		//请求数据
		try {
			URL url = new URL(httpUrl);		
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setDoInput(true);//URL 连接可用于输入
			httpUrlConnection.setRequestMethod("GET");//设置请求方法
			httpUrlConnection.connect();//打开到此 URL 引用的资源的通信链接
			
			//获得输入
			InputStream inputStream = httpUrlConnection.getInputStream();//字节流
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");//字符流
			BufferedReader reader = new BufferedReader(inputStreamReader);//缓冲流
			
			//储存
			String str = null;
			while((str = reader.readLine())!=null) {
				buff.append(str);
			}
			
			//关闭
			reader.close();
			inputStreamReader.close();
			inputStream.close();
			
			//断开连接
			httpUrlConnection.disconnect();
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return buff.toString();
	}
}