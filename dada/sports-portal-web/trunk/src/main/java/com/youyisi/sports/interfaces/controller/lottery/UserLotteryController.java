package com.youyisi.sports.interfaces.controller.lottery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.lottery.UserLotteryServiceRemote;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.lottery.UserLottery;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/userlottery")
public class UserLotteryController extends BaseController{

	@Autowired
	private UserLotteryServiceRemote userLotteryServiceRemote;

	private Logger log = LoggerFactory.getLogger(UserLotteryController.class);

	

	@ResponseBody
	@RequestMapping(value = "/add", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper add(UserLottery userLottery) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user  = getUserByToken(userLottery.getToken());
			userLottery.setCreateTime(System.currentTimeMillis());
			userLottery.setUserId(user.getId());
		    int result = userLotteryServiceRemote.add(userLottery);
		    if(result==-1){
		    	webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("金豆余额不足，请购买金豆！");
		    }
		    if(result==-2){
		    	webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("投注时间已过！");
		    }
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

