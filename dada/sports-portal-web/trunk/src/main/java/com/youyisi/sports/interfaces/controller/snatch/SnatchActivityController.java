package com.youyisi.sports.interfaces.controller.snatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.snatch.SnatchActivityServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.snatch.SnatchActivityWithSnatchWinWithMore;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-09-21
 */
@Controller
@RequestMapping("/snatchactivity")
public class SnatchActivityController extends BaseController{

	@Autowired
	private SnatchActivityServiceRemote snatchActivityServiceRemote;

	private Logger log = LoggerFactory.getLogger(SnatchActivityController.class);

	@ResponseBody
	@RequestMapping(value = "/history", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper history(Integer currentPage, Integer pageSize,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		Page<SnatchActivityWithSnatchWinWithMore> page = new Page<SnatchActivityWithSnatchWinWithMore>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		page.addParam("time", System.currentTimeMillis());
		webResultInfoWrapper.addResult("page", snatchActivityServiceRemote.queryPageForHistory(page));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

}

