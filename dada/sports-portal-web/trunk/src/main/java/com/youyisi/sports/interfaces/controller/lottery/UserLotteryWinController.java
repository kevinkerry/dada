package com.youyisi.sports.interfaces.controller.lottery;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.lottery.LotteryServiceRemote;
import com.youyisi.app.soa.remote.lottery.UserLotteryServiceRemote;
import com.youyisi.app.soa.remote.lottery.UserLotteryWinServiceRemote;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.lottery.Lottery;
import com.youyisi.sports.domain.lottery.UserLotteryWinMore;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/userlotterywin")
public class UserLotteryWinController extends BaseController{

	@Autowired
	private UserLotteryWinServiceRemote userLotteryWinServiceRemote;
	@Autowired
	private UserLotteryServiceRemote userLotteryServiceRemote;
	@Autowired
	private LotteryServiceRemote lotteryServiceRemote;

	private Logger log = LoggerFactory.getLogger(UserLotteryWinController.class);



	@ResponseBody
	@RequestMapping(value = "/getWin", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper getWin(String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			UserLotteryWinMore userLotteryWin = userLotteryWinServiceRemote.getWin(user.getId());
		    webResultInfoWrapper.addResult("userLotteryWin",userLotteryWin);
		    if(userLotteryWin!=null){
		    	Integer count  = userLotteryServiceRemote.getUserLotteryCount(userLotteryWin.getLotteryId(), userLotteryWin.getUserId());
		    	Lottery lottery = lotteryServiceRemote.get(userLotteryWin.getLotteryId());
		    	Calendar calendar = new GregorianCalendar();
		    	calendar.add(Calendar.DATE, lottery.getBonusExpiryDay());
		    	webResultInfoWrapper.addResult("count",count);
		    	webResultInfoWrapper.addResult("expiryTime",calendar.getTime().getTime());
		    }
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getLotteryWinCoupon", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper getLotteryWinCoupon(Long id,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			UserLotteryWinMore userLotteryWinMore = userLotteryWinServiceRemote.getUserLotteryWinMore(id);
			if(userLotteryWinMore.getUserId().longValue()!=user.getId().longValue()){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("非法请求！");
				return webResultInfoWrapper;
			}
			if(userLotteryWinMore.getStatus().intValue()==1){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("您已经领取过了！");
				return webResultInfoWrapper;
			}
			if(userLotteryWinMore.getExpiryTime()<System.currentTimeMillis()){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("奖券已经过期了！");
				return webResultInfoWrapper;
			}
		 userLotteryWinServiceRemote.getLotteryWinCoupon(userLotteryWinMore);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

