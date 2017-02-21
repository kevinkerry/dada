package com.youyisi.sports.domain;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.youyisi.lang.annotation.Id;

@JsonIgnoreProperties(value = { "token" })
@JsonInclude(Include.ALWAYS)
public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4531672504017634216L;
	@Id
	protected Long id;
	/** 令牌 **/
	protected String token;
	
	protected Long iid;
	public Long getId() {
		return id;
	}
	public Long getIid() {
		return id;
	}
	public void setIid(Long iid) {
		this.iid = iid;
	}
	public void setId(Long id) {
		setIid(id);
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}