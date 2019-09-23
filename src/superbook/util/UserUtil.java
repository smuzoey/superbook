package superbook.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserUtil {
	private String appid = "wxf2cac0901442aafb";                //小程序 appId
	private String secret = "05723d26bebf756cb9f06a41e2595881";  //小程序 appSecret
	private String grant_type = "authorization_code";           //授权类型，此处只需填写 authorization_code
	private String WXUrl = "https://api.weixin.qq.com/sns/jscode2session";
	
	public String getUser(String js_code) { // js_code : 登录时获取的 code
		//拼接URL
		String httpUrl = String.format("%s?&secret=%s&js_code==%s&grant_type=%s", WXUrl,secret,js_code,grant_type);
		StringBuffer buff = new StringBuffer();//存储信息
		
		try {
			URL url = new URL(httpUrl);
			
			//获取链接
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setRequestMethod("GET");
			httpUrlConnection.connect();
			
			//获取输入
			InputStream input = httpUrlConnection.getInputStream();
			InputStreamReader inputReader = new InputStreamReader(input,"utf-8");
			BufferedReader reader = new BufferedReader(inputReader); 
			
			//存储信息
			String str = null;
			while((str = reader.readLine())!=null) {
				buff.append(str);
			}
			
			//关闭连接
			reader.close();
			inputReader.close();
			input.close();
			
			//断开连接
			httpUrlConnection.disconnect();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return buff.toString();
	}
}
