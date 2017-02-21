package com.youyisi.admin.infrastructure.helper.gexinpush;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.youyisi.admin.domain.sportuser.SportUser;
import com.youyisi.lang.helper.JsonHelper;

public class PushToSingleHelper {

	static String appId = "M9vsPEtqZb85biq0n7nc66";
	static String appkey = "JMM5p7MgRg5cR8LrVDFaS6";
	static String master = "2vFz9x1yn28kb10rBYeAe6";
	static String clientType = "2";
	//static String CID = "8c027398bca909789a1be75647887100";
	static String Alias = "";
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";

	public static void push(final SportUser user,final TransmissionContent param)  {
		new Thread() {
			public void run() {
				try{
					
					IGtPush push = new IGtPush(host,appkey, master);
					SingleMessage message = new SingleMessage();
					message.setOffline(true);
					message.setOfflineExpireTime(2 * 1000 * 3600);
					if(clientType.equalsIgnoreCase(user.getClientType())){
						TransmissionTemplate	template = TransmissionTemplateDemo(param);
						message.setData(template);
					}else{
						TransmissionTemplate template = NotificationTemplateDemo(param);
						message.setData(template);
					}
					
					// message.setPushNetWorkType(1); //鏍规嵁WIFI鎺ㄩ�璁剧疆
					Target target1 = new Target();
					target1.setAppId(appId);
					target1.setClientId(user.getClientId());
					try {
						IPushResult ret = push.pushMessageToSingle(message, target1);
						System.out.println("正常：" + ret.getResponse().toString());
						
					} catch (RequestException e) {
						String requstId = e.getRequestId();
						IPushResult ret = push.pushMessageToSingle(message, target1,requstId);

						System.out.println("异常：" + ret.getResponse().toString());
					}

					Thread.sleep(3);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}.start();
	}

	public static TransmissionTemplate NotificationTemplateDemo(TransmissionContent param)
			throws Exception {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		// template.setLogoUrl("");
		// template.setIsRing(true);
		// template.setIsVibrate(true);
		// template.setIsClearable(true);
		template.setTransmissionType(2);
		template.setTransmissionContent(JsonHelper.toJsonString(param));
		// template.setPushInfo("actionLocKey", 2, "message", "sound",
		// "payload", "locKey", "locArgs", "launchImage");
		return template;
	}
	
	
	public static TransmissionTemplate TransmissionTemplateDemo(TransmissionContent param)
			throws Exception {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		template.setTransmissionType(2);
		template.setTransmissionContent(JsonHelper.toJsonString(param));
	//	 template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
//		template.setPushInfo("", 1, "", "", "", "", "", "");
		
		//**********APN简单推送********//
		APNPayload apnpayload = new APNPayload();
		com.gexin.rp.sdk.base.payload.APNPayload.SimpleAlertMsg alertMsg = new com.gexin.rp.sdk.base.payload.APNPayload.SimpleAlertMsg(
				param.getTitle().toString());
		apnpayload.setAlertMsg(alertMsg);
		apnpayload.setBadge(1);
		apnpayload.setContentAvailable(1);
		//apnpayload.addCustomMsg("param",);
	//	apnpayload.addCustomMsg("type", param.get("type"));
	//	apnpayload.setCategory("ACTIONABLE");
		template.setAPNInfo(apnpayload);
		
			//************APN高级推送*******************//
//			APNPayload apnpayload = new APNPayload();
//			apnpayload.setBadge(4);
//			apnpayload.setSound("test2.wav");
//			apnpayload.setContentAvailable(1);
//			apnpayload.setCategory("ACTIONABLE");
//			APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
//			alertMsg.setBody("body");
//			alertMsg.setActionLocKey("ActionLockey");
//			alertMsg.setLocKey("LocKey");
//			alertMsg.addLocArg("loc-args");
//			alertMsg.setLaunchImage("launch-image");
//			// IOS8.2以上版本支持
//			alertMsg.setTitle("Title");
//			alertMsg.setTitleLocKey("TitleLocKey");
//			alertMsg.addTitleLocArg("TitleLocArg");
//
//			apnpayload.setAlertMsg(alertMsg);
//			template.setAPNInfo(apnpayload);
		
		
		return template;
	}
}