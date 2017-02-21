package com.youyisi.vote.interfaces.controller.weixinapi;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.vote.application.weixin.WeiXinService;
import com.youyisi.vote.infrastructure.util.SignUtil;

/**
 * @author shuye
 * @time 2014年5月8日
 */
@Controller
@RequestMapping(value="/weixin")
public class WeiXinController {
	@Autowired
	private WeiXinService weiXinService;
	
	@RequestMapping(value="/check",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String check(String signature,String timestamp ,String nonce,String echostr,Model model){
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			return echostr;
			
		}
		return null;
	}
	
	@RequestMapping(value="/check",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String check(HttpServletRequest request,Model model){
		return weiXinService.processRequest(request);
	}
	
	
}
