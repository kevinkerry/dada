package com.youyisi.sports.interfaces.controller.lottery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.lottery.LotteryServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.lottery.LotteryWithUserWinMore;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/lottery")
public class LotteryController extends BaseController{

	@Autowired
	private LotteryServiceRemote lotteryServiceRemote;

	private Logger log = LoggerFactory.getLogger(LotteryController.class);

	@ResponseBody
	@RequestMapping(value = "/history", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper history(Integer currentPage, Integer pageSize,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user  = getUserByToken(token);
		Page<LotteryWithUserWinMore> page = new Page<LotteryWithUserWinMore>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		page.addParam("userId", user.getId());
		page.addParam("date", DateUtil.currentDateForDay());
		webResultInfoWrapper.addResult("page", lotteryServiceRemote.queryPageForHistory(page));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/havetry", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper havetry(String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user  = getUserByToken(token);
		webResultInfoWrapper.addResult("lastLottery", lotteryServiceRemote.getByDate(DateUtil.getDateForDay(-1),user));
		webResultInfoWrapper.addResult("currentLottery", lotteryServiceRemote.getByDate(DateUtil.currentDateForDay(),user));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

}

