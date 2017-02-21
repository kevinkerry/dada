package com.youyisi.soa.infrastructure.constant;

import java.util.ResourceBundle;

public class SmsConstant {
	private static ResourceBundle rb = ResourceBundle.getBundle("application");
	public static final String USER_ID = rb.getString("sms.userId");
	public static final String PASSWORD = rb.getString("sms.password");
	public static final String IP = rb.getString("sms.ip");
	public static final String PORT = rb.getString("sms.port");
}