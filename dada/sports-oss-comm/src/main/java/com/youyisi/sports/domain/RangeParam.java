package com.youyisi.sports.domain;

import java.io.Serializable;

public class RangeParam  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8495224371689042914L;
	private Object from;
	private Object to;
	public Object getFrom() {
		return from;
	}
	public void setFrom(Object from) {
		this.from = from;
	}
	public Object getTo() {
		return to;
	}
	public void setTo(Object to) {
		this.to = to;
	}

}
