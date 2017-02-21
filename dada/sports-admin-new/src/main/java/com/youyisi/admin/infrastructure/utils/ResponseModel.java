package com.youyisi.admin.infrastructure.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果集信息
 * 
 * @author hetao
 *
 */
public class ResponseModel implements Serializable {

	private static final long serialVersionUID = 8569165736963437211L;

	// 状态代码 默认是错误
	private String code = "error";

	// 错误信息
	private String errorMsg;

	// 结果集
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public void setMap(String key, Object value) {
		this.resultMap.put(key, value);
	}

	public void success() {
		setCode("SUCCEED");
	}

}
