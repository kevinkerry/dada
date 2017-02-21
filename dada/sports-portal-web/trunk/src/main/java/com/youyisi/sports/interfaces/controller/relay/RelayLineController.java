package com.youyisi.sports.interfaces.controller.relay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.relay.RelayLineServiceRemote;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-10-08
 */
@Controller
@RequestMapping("/relayline")
public class RelayLineController extends BaseController{

	@Autowired
	private RelayLineServiceRemote relayLineServiceRemote;

	private Logger log = LoggerFactory.getLogger(RelayLineController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(String token,Long activityId) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		webResultInfoWrapper.addResult("list", relayLineServiceRemote.getByActivityId(activityId));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

