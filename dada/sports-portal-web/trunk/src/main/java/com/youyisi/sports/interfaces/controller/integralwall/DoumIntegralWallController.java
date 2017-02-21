package com.youyisi.sports.interfaces.controller.integralwall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.app.soa.remote.integralwall.DoumIntegralWallServiceRemote;
import com.youyisi.sports.domain.integralwall.DoumIntegralWall;
import com.youyisi.lang.Page;

import com.youyisi.sports.interfaces.controller.BaseController;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import com.youyisi.lang.domain.WebResultInfoWrapper;

/**
 * @author shuye
 * @time 2016-08-15
 */
@Controller
@RequestMapping("/doumintegralwall")
public class DoumIntegralWallController extends BaseController{

	@Autowired
	private DoumIntegralWallServiceRemote doumIntegralWallServiceRemote;

	private Logger log = LoggerFactory.getLogger(DoumIntegralWallController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Integer currentPage, Integer pageSize) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		Page<DoumIntegralWall> page = new Page<DoumIntegralWall>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		webResultInfoWrapper.addResult("page", doumIntegralWallServiceRemote.queryPage(page));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper detail(@PathVariable("id") Long id) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		webResultInfoWrapper.addResult("doumIntegralWall", doumIntegralWallServiceRemote.get(id));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper update(DoumIntegralWall DoumIntegralWall) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		doumIntegralWallServiceRemote.update(DoumIntegralWall);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

