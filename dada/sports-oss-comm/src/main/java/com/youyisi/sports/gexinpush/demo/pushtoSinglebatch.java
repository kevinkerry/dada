package com.youyisi.sports.gexinpush.demo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.transform.Templates;

import com.gexin.rp.sdk.base.IBatch;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.uitls.ApnsUtils;
import com.gexin.rp.sdk.base.uitls.StackTraceUtil;
import com.gexin.rp.sdk.exceptions.PushSingleException;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.PopupTransmissionTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class pushtoSinglebatch {

	// ------------椋炲父鍑�---------------
	static String appId = "rXXu5N6ulY6RB5oInfS9mA";
	static String appkey = "B6JtTw9bmu57iJb6av7nQ2";
	static String master = "DF8EYi9y1w9Jj0ARhSDjk9";
	static String CID = "8c027398bca909789a1be75647887100";

	public static void main(String[] args) throws Exception {

		// System.setProperty("gexin.rp.sdk.http.proxyHost",
		// "192.168.1.227:808");

		System.setProperty("gexin_pushSingleBatch_needAsync", "false");

		String host = "http://sdk.open.api.igexin.com/apiex.htm";
		IGtPush push = new IGtPush(host,appkey, master);
		IBatch Batch = push.getBatch();
//		Batch.setApiUrl(host);

//		 TransmissionTemplate template = TransmissionTemplateDemo();
		LinkTemplate template = linkTemplateDemo();
		// NotificationTemplate template = NotificationTemplateDemo();
		// NotyPopLoadTemplate template =NotyPopLoadTemplateDemo();
		template.setTitle("kkkkkkk");
		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		message.setOfflineExpireTime(2 * 1000 * 3600);
		message.setData(template);
//		message.setPushNetWorkType(1); //

		List<Target> targets = new ArrayList<Target>();
		
		Target target1 = new Target();
		target1.setAppId(appId);
		target1.setClientId(CID);
		Batch.add(message, target1);

		try {
			String result = Batch.submit().getResponse().toString();
			System.out.println(result);
		} catch (RequestException e) {

			System.out.println(e);
			String requstId = e.getRequestId();
			IPushResult ret = push.pushMessageToSingle(message, target1,
					requstId);

			System.out.println("异常：" + ret.getResponse().toString());
		}
	}

	public static void sf(long time) {
		Date date = new Date(time);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sf.format(date));

	}

	public static PopupTransmissionTemplate PopupTransmissionTemplateDemo() {
		PopupTransmissionTemplate template = new PopupTransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		template.setText("");
		template.setTitle("");
		template.setImg("");
		template.setConfirmButtonText("");
		template.setCancelButtonText("");
		template.setTransmissionContent("111");
		template.setTransmissionType(1);

		return template;
	}

	public static TransmissionTemplate TransmissionTemplateDemo()
			throws Exception {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		template.setTransmissionType(1);
		template.setTransmissionContent("OS-TOSingle");
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		// template.setPushInfo("", 1, "", "", "", "", "", "");
		// template.getPushInfo().toString().getBytes().length;
		// template.getPushInfo()
		return template;
	}

	public static LinkTemplate linkTemplateDemo() throws Exception {
		LinkTemplate template = new LinkTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		template.setTitle("标题");
		template.setText("文本");
		template.setLogo("text.png");
		// template.setLogoUrl("");
		// template.setIsRing(true);
		// template.setIsVibrate(true);
		// template.setIsClearable(true);
		template.setUrl("http://www.baidu.com");
		// template.setPushInfo("actionLocKey", 1, "message", "sound","payload",
		// "locKey", "locArgs", "launchImage");
		return template;
	}

	public static NotificationTemplate NotificationTemplateDemo()
			throws Exception {
		NotificationTemplate template = new NotificationTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		template.setTitle("");
		template.setText("");
		template.setLogo("icon.png");
		// template.setLogoUrl("");
		// template.setIsRing(true);
		// template.setIsVibrate(true);
		// template.setIsClearable(true);
		template.setTransmissionType(1);
		template.setTransmissionContent("dddd");
		// template.setPushInfo("actionLocKey", 2, "message", "sound",
		// "payload", "locKey", "locArgs", "launchImage");
		return template;
	}

	public static NotyPopLoadTemplate NotyPopLoadTemplateDemo() {
		NotyPopLoadTemplate template = new NotyPopLoadTemplate();
		// 婵夘偄鍟揳ppid娑撳穬ppkey
		template.setAppId(appId);
		template.setAppkey(appkey);
		// 婵夘偄鍟撻柅姘辩叀閺嶅洭顣介崪灞藉敶鐎癸拷
		template.setNotyTitle("");
		template.setNotyContent("");
		// template.setLogoUrl("");
		// 婵夘偄鍟撻崶鐐垼閺傚洣娆㈤崥宥囷拷?
		template.setNotyIcon("text.png");
		// 鐠佸墽鐤嗛崫宥夋惄閿涘矂娓块崝顭掔礉娑撳骸褰插〒鍛存珟
		// template.setBelled(false);
		// template.setVibrationed(false);
		// template.setCleared(true);

		// 鐠佸墽鐤嗗瑙勵攱閺嶅洭顣芥稉搴″敶鐎癸拷
		template.setPopTitle("");
		template.setPopContent("");
		// 鐠佸墽鐤嗗瑙勵攱閸ュ墽锟�
		template.setPopImage("http://www-igexin.qiniudn.com/wp-content/uploads/2013/08/logo_getui1.png");
		template.setPopButton1("");
		template.setPopButton2("");

		// 鐠佸墽鐤嗘稉瀣祰閺嶅洭顣介敍灞芥禈閻楀洣绗屾稉瀣祰閸︽澘锟�
		template.setLoadTitle("娑撳娴囬弽鍥暯");
		template.setLoadIcon("file://icon.png");
		template.setLoadUrl("http://gdown.baidu.com/data/wisegame/c95836e06c224f51/weixinxinqing_5.apk");
		template.setActived(true);
		template.setAutoInstall(true);
		template.setAndroidMark("");
		return template;
	}
}