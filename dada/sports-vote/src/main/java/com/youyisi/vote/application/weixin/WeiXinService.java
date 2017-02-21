package com.youyisi.vote.application.weixin;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author shuye
 *
 */
public interface WeiXinService {

	String processRequest(HttpServletRequest request);	
	
}
