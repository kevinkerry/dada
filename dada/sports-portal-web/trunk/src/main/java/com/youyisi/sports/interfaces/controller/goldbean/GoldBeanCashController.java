package com.youyisi.sports.interfaces.controller.goldbean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.goldbean.GoldBeanCashServiceRemote;
import com.youyisi.app.soa.remote.goldbean.GoldBeanServiceRemote;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.goldbean.GoldBean;
import com.youyisi.sports.domain.goldbean.GoldBeanCash;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/goldbeancash")
public class GoldBeanCashController extends BaseController{

	@Autowired
	private GoldBeanCashServiceRemote goldBeanCashServiceRemote;
	@Autowired
	private GoldBeanServiceRemote goldBeanServiceRemote;
	private Logger log = LoggerFactory.getLogger(GoldBeanCashController.class);

	
	@ResponseBody
	@RequestMapping(value = "/add", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper add(GoldBeanCash goldBeanCash) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(goldBeanCash.getToken());
			GoldBean goldBean = goldBeanServiceRemote.getByUserId(user.getId());
			if(goldBean.getGoldBean()<goldBeanCash.getGoldBean()){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("余额不足！");
			}
			goldBeanCash.setDate(DateUtil.currentDateForDay());
			goldBeanCash.setStatus(0);
			goldBeanCash.setUserId(user.getId());
		goldBeanCashServiceRemote.add(goldBeanCash,goldBean);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

