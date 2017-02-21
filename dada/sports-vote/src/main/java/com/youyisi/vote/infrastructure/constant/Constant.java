package com.youyisi.vote.infrastructure.constant;

import java.util.ResourceBundle;

public class Constant {
	private static ResourceBundle rb = ResourceBundle.getBundle("application");
	public static final String startTimeA = rb.getString("spot.startTimeA");
	public static final String endTimeA = rb.getString("spot.endTimeA");
	
	public static final String startTimeB = rb.getString("spot.startTimeB");
	public static final String endTimeB = rb.getString("spot.endTimeB");
	
	public static final String probabilityA = rb.getString("spot.probabilityA");
	public static final String probabilityB = rb.getString("spot.probabilityB");
	
	
}