package com.youyisi.sports.interfaces.controller.lottery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.goldbean.GoldBeanServiceRemote;
import com.youyisi.app.soa.remote.lottery.BetCountServiceRemote;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Controller
@RequestMapping("/betcount")
public class BetCountController extends BaseController{

	@Autowired
	private BetCountServiceRemote betCountServiceRemote;
	@Autowired
	private GoldBeanServiceRemote goldBeanServiceRemote;
	private Logger log = LoggerFactory.getLogger(BetCountController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Long lotteryId,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			webResultInfoWrapper.addResult("myGoldBean", goldBeanServiceRemote.getByUserId(user.getId()));
		    webResultInfoWrapper.addResult("list", betCountServiceRemote.getByLotteryId(lotteryId));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

