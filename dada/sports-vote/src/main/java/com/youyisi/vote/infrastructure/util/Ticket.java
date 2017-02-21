package com.youyisi.vote.infrastructure.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.youyisi.lang.helper.JsonHelper;
import com.youyisi.vote.domain.luck.UserInfo;
import com.youyisi.vote.domain.weixin.AccessToken;
import com.youyisi.vote.infrastructure.cache.redis.RedisClient;
public class Ticket{
	private static Logger log = LoggerFactory.getLogger(Ticket.class);

	
	public static  String getTicket(){
		
		 String ticket = (String) RedisClient.get("vote:ticket");
		 if(StringUtils.isBlank(ticket)){
			 AccessToken accessToken = getAccessToken();  
			 ticket = getTiket(ticket, accessToken);  
		 }
			return ticket;
		  
	}

	private static String getTiket(String ticket, AccessToken accessToken) {
		String tiket_url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken.getAccess_token()+"&type=jsapi";
		 // log.info(tiket_url);
		  String jsonObject2 = httpRequest(tiket_url, "GET", null);
		  System.out.println("jsonObject2:"+jsonObject2);
		  if (null != jsonObject2) {  
			  
		        try {  
		        	Map<String,String> map = JsonHelper.fromJsonString(jsonObject2, Map.class);
		        	ticket=map.get("ticket");  
		        	RedisClient.set("vote:ticket", ticket,7000l, TimeUnit.SECONDS);
		           // log.info("tt="+ticket);
		        } catch (Exception e) {  
		            accessToken = null;  
		            // 获取token失败   
		           // log.error("获取token失败 tiket:{} errmsg:{}", jsonObject2.getInt("errcode"), jsonObject2.getString("errmsg"));  
		        }  
		    }
		return ticket;
	}

	private static AccessToken getAccessToken() {
		AccessToken accessToken = null;
		String result = null;
		result = (String) RedisClient.get("vote:accessToken");
		if (StringUtils.isNotBlank(result)) {
			return JsonHelper.fromJsonString(result, AccessToken.class);
		}
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx20d7737dce82964e&secret=ea71309f8929be96862e288d13dbb8b1";
		result = httpRequest(url, "GET", null);
		System.out.println("result:" + result);
		if (null != result) {
			try {
				accessToken = JsonHelper.fromJsonString(result,
						AccessToken.class);
				RedisClient.set("vote:accessToken", result, 7000l,
						TimeUnit.SECONDS);
				// log.info("ac="+jsonObject.getString("access_token"));
			} catch (Exception e) {
				accessToken = null;
				// 获取token失败
				// log.error("获取token失败 errcode:{} errmsg:{}",
				// jsonObject.getInt("errcode"),
				// jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	public static UserInfo getUserInfo(String code) {
		Map<String,String> map = getOpenIds(code);
		String result = null;
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+map.get("access_token")+"&openid="+map.get("openid")+"&lang=zh_CN";
		result = httpRequest(url, "GET", null);
		System.out.println("result:" + result);
		if (null != result) {
			try {
				return JsonHelper.fromJsonString(result,
						UserInfo.class);
				// log.info("ac="+jsonObject.getString("access_token"));
			} catch (Exception e) {
				// 获取token失败
				// log.error("获取token失败 errcode:{} errmsg:{}",
				// jsonObject.getInt("errcode"),
				// jsonObject.getString("errmsg"));
			}
		}
		return null;
	}
	
	//https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
		public static  Map<String,String> getOpenIds(String code){
			String url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxf8bd7ea02a023bb7&secret=8ec09ab1d9e46427a6a88fd4a0884082&code="+code+"&grant_type=authorization_code";
			String result = httpRequest(url, "GET", null);
			System.out.println("result:"+result);
			  if (null != result) {  
				  
			        try {  
			        	  return JsonHelper.fromJsonString(result, Map.class);
			        	 // openid=map.get("openid");  
			        } catch (Exception e) {  
			        	e.printStackTrace();
			        }  
			    }  
				return null;
		}
	
	//https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
	public static  String getOpenId(String code){
		String url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxf8bd7ea02a023bb7&secret=8ec09ab1d9e46427a6a88fd4a0884082&code="+code+"&grant_type=authorization_code";
		  String openid = "";
		String result = httpRequest(url, "GET", null);
		System.out.println("result:"+result);
		  if (null != result) {  
			  
		        try {  
		        	  Map<String,String> map = JsonHelper.fromJsonString(result, Map.class);
		        	  openid=map.get("openid");  
		        } catch (Exception e) {  
		        	e.printStackTrace();
		        }  
		    }  
			return openid;
	}
	
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return buffer.toString();
	}


}
