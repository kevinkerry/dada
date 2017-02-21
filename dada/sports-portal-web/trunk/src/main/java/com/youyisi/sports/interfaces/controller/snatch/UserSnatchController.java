package com.youyisi.sports.interfaces.controller.snatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.snatch.UserSnatchServiceRemote;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.snatch.UserSnatch;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-09-21
 */
@Controller
@RequestMapping("/usersnatch")
public class UserSnatchController extends BaseController{

	@Autowired
	private UserSnatchServiceRemote userSnatchServiceRemote;

	private Logger log = LoggerFactory.getLogger(UserSnatchController.class);

	@ResponseBody
	@RequestMapping(value = "/add", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper add(UserSnatch userSnatch) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(userSnatch.getToken());
			userSnatch.setCreateTime(System.currentTimeMillis());
			userSnatch.setStatus(0);
			userSnatch.setPayStatus(0);
			userSnatch.setUserId(user.getId());
		   webResultInfoWrapper.addResult("userSnatch", userSnatchServiceRemote.save(userSnatch));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pay", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper pay(UserSnatch userSnatch) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			UserSnatch us = userSnatchServiceRemote.get(userSnatch.getId());
			if(us.getPayStatus().intValue()==1){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("已经支付过了");
				return webResultInfoWrapper;
			}
			int result = userSnatchServiceRemote.pay(us);
			if(result==-1){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("余额不足");
				return webResultInfoWrapper;
			}
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

