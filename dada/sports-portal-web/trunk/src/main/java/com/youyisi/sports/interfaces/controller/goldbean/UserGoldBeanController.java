package com.youyisi.sports.interfaces.controller.goldbean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.goldbean.UserGoldBeanServiceRemote;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.goldbean.UserGoldBean;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/usergoldbean")
public class UserGoldBeanController extends BaseController{

	@Autowired
	private UserGoldBeanServiceRemote userGoldBeanServiceRemote;

	private Logger log = LoggerFactory.getLogger(UserGoldBeanController.class);

	@ResponseBody
	@RequestMapping(value = "/add", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper add(UserGoldBean userGoldBean) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(userGoldBean.getToken());
			userGoldBean.setPayStatus(0);
			userGoldBean.setCreateTime(System.currentTimeMillis());
			userGoldBean.setStatus(0);
			userGoldBean.setUserId(user.getId());
			webResultInfoWrapper.addResult("userGoldBean", userGoldBeanServiceRemote.save(userGoldBean));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pay", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper pay(UserGoldBean userGoldBean) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			int result = userGoldBeanServiceRemote.pay(userGoldBean.getId());
			if(result==-1){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("不能重复支付！");
			}
			if(result==-2){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("余额不足！");
			}
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

