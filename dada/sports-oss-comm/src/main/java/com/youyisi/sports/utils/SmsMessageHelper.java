package com.youyisi.sports.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.youyisi.smspost.CHttpPost;
import com.youyisi.smspost.common.ISms;
import com.youyisi.smspost.common.StaticValue;
import com.youyisi.sports.constant.SmsConstant;
import com.youyisi.sports.domain.sms.SmsMessage;
/**
 * 
 * @author shuye
 *
 */
public class SmsMessageHelper{
	private static final Log logger = LogFactory.getLog(SmsMessageHelper.class);
	 static String userId = SmsConstant.USER_ID;
	 static String password = SmsConstant.PASSWORD;
	 static boolean bKeepAlive=false;
	 static HttpClient httpClient = new DefaultHttpClient();
	
	public static void send(final SmsMessage message) {
		new Thread() {
			public void run() {
				try {
					StaticValue.ip = SmsConstant.IP;
					StaticValue.port = SmsConstant.PORT;
					 ISms sms = new CHttpPost();
	        		 StringBuffer strPtMsgId=new StringBuffer("");
	        		//短信息发送接口（不同内容群发，可自定义不同流水号，自定义不同扩展号）POST请求,httpClient为空，则是短连接,httpClient不为空，则是长连接。
	        		 int result= sms.SendSms(strPtMsgId,userId, password, message.getTelephone(), message.getBody(), "*", "0", bKeepAlive, httpClient);
	        		 if(result==0){
		            		System.out.println("发送成功："+strPtMsgId.toString());
		            	  }else{
		            		System.out.println("发送失败："+strPtMsgId.toString());
		            	  }
					// 测试时使用
					// result="success";
					logger.debug("back value is :" + result);
					logger.debug("send mongateCsSendSmsEx end !");
				} catch (Exception e) {
					logger.error("send mongateCsSendSmsEx failure !", e);
					e.printStackTrace();
				}
			}
		}.start();
	}

}
