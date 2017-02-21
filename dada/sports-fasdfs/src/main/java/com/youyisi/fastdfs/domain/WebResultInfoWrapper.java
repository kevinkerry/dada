package com.youyisi.fastdfs.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**返回结果的包装类
 * @author shuye
 * @time 2013-9-4
 */
public class WebResultInfoWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -498982388474650914L;

	// 获取数据成功
	public final static String SUCCESS = "success";

	// 获取数据失败
	public final static String FAILED = "failed";

	// 权限认证失败
	public final static String AUTH_FAILED = "authFailed";

	/**
	 * 结果状态
	 */
	private String state = SUCCESS;
	/**
	 * 消息体
	 */
	private String message;

	private Map<String,Object> results = new HashMap<String,Object>();

	public WebResultInfoWrapper() {

	}

	public WebResultInfoWrapper(String state, Map<String,Object> results) {
		this.state = state;
		this.results = results;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Map<String,Object> getResults() {
		return results;
	}

	public void setResults(Map<String,Object> results) {
		this.results = results;
	}
	
	public WebResultInfoWrapper success(){
		this.setState(SUCCESS);
		return this;
	}
	
	public WebResultInfoWrapper failed(){
		this.setState(FAILED);
		return this;
	}
	
	public WebResultInfoWrapper authFailed(){
		this.setState(AUTH_FAILED);
		return this;
	}
	
	/**
	 * 
	 * @param name
	 * @param result
	 * @return 
	 */
	public WebResultInfoWrapper addResult(String name,Object result){
		this.results.put(name, result);
		return this;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
