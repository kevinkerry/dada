package com.youyisi.sports.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IOSMessagePushHelper {
	
	private boolean productModel = false;
	private String password;
	private String p12path;
	
	public boolean isProductModel() {
		return productModel;
	}

	public void setProductModel(boolean productModel) {
		this.productModel = productModel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getP12path() {
		return p12path;
	}

	public void setP12path(String p12path) {
		this.p12path = p12path;
	}

	private Log log = LogFactory.getLog(this.getClass());

	public void doSend(String token, String sender,String channel, String message){
		if(StringUtils.isNotBlank(token)){
			new IosPushNotification(token, sender,channel,message).start();
		}
	}
	public void doSend(String token, String sender,String channel, String message,Map<String, Object> params){
		if (StringUtils.isNotBlank(token)) {
			new IosPushNotification(token, sender, channel, message, params).start();
		}
	}

	public void doSend(Map<String, String> tokens, String channel,String sender){
		new IosPushMoreNotification(tokens, channel,sender).start();
	}
	
	
	class IosPushNotification extends Thread {

		private String token;
		private String sender;
		private String channel;
		private String message;
		private Map<String, Object> params = new HashMap<String, Object>();
		

		public IosPushNotification(String token, String sender,String channel, String message,Map<String, Object> params) {
			this.token = token;
			this.sender = sender;
			this.channel = channel;
			this.message = message;
			this.params = params;
		}
		
		public IosPushNotification(String token, String sender,String channel, String message) {
			this.token = token;
			this.sender = sender;
			this.channel = channel;
			this.message = message;
		}

		@Override
		public void run() {
			super.run();
			log.info("push to apple :" + token);
			try {
				PushNotificationPayload payLoad = new PushNotificationPayload();
				List<PushedNotification> notifications = new ArrayList<PushedNotification>();
				payLoad.addAlert(this.message);
				payLoad.addBadge(1);
				payLoad.addSound("default");
				payLoad.addCustomDictionary("sender",this.sender);
				payLoad.addCustomDictionary("channel", this.channel);
				for(String key:params.keySet()){
					payLoad.addCustomDictionary(key, params.get(key));
				}
				PushNotificationManager pushManager = new PushNotificationManager();
				pushManager.initializeConnection(new AppleNotificationServerBasicImpl(p12path, password, productModel));
				
				Device device = new BasicDevice();
				device.setToken(token);
				PushedNotification notification = pushManager.sendNotification(device,payLoad, true);
				
				notifications.add(notification);
				resultNotifications(notifications);
				pushManager.stopConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	class IosPushMoreNotification extends Thread {

		private Map<String, String> tokens;
		private String sender;
		private String channel;

		public IosPushMoreNotification(Map<String, String> tokens, String channel,String sender) {
			this.tokens = tokens;
			this.sender = sender;
			this.channel = channel;
		}

		@Override
		public void run() {
			super.run();
			try {
				List<PushedNotification> notifications = new ArrayList<PushedNotification>();
				PushNotificationManager pushManager = new PushNotificationManager();
				pushManager.initializeConnection(new AppleNotificationServerBasicImpl(p12path, password, productModel));
				
				for (String token : tokens.keySet()) {
					if (StringUtils.isNotBlank(token)) {
						log.info("push to apple :" + token);
						PushNotificationPayload payLoad = new PushNotificationPayload();
						payLoad.addAlert(tokens.get(token));
						payLoad.addBadge(1);
						payLoad.addSound("default");
						payLoad.addCustomDictionary("sender", sender);
						payLoad.addCustomDictionary("channel", channel);

						Device device = new BasicDevice();
						device.setToken(token);
						PushedNotification notification = pushManager
								.sendNotification(device, payLoad, true);
						notifications.add(notification);
					}
				}
                resultNotifications(notifications);
				
				pushManager.stopConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

	public void resultNotifications(List<PushedNotification> notifications) {
		List<PushedNotification> failedNotifications = PushedNotification
				.findFailedNotifications(notifications);
		List<PushedNotification> successfulNotifications = PushedNotification
				.findSuccessfulNotifications(notifications);
		int failed = failedNotifications.size();
		int successful = successfulNotifications.size();
		if (successful > 0 && failed == 0) {
			log.info("success count \t: " + successfulNotifications.size());
		} else if (successful == 0 && failed > 0) {
			log.info("failed  count \t: " + failedNotifications.size());
		} else if (successful == 0 && failed == 0) {
			log.info("No notifications could be sent, probably because of a critical error");
		} else {
			log.info("success count \t: " + successfulNotifications.size());
			log.info("failed  count \t: " + failedNotifications.size());
		}		
	}

}
