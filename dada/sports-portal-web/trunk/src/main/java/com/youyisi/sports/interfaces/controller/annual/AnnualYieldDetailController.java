package com.youyisi.sports.interfaces.controller.annual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.app.soa.remote.annual.AnnualYieldDetailServiceRemote;
import com.youyisi.sports.domain.annual.AnnualYieldDetail;
import com.youyisi.lang.Page;

import com.youyisi.sports.interfaces.controller.BaseController;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import com.youyisi.lang.domain.WebResultInfoWrapper;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Controller
@RequestMapping("/annualyielddetail")
public class AnnualYieldDetailController extends BaseController{

	@Autowired
	private AnnualYieldDetailServiceRemote annualYieldDetailServiceRemote;

	private Logger log = LoggerFactory.getLogger(AnnualYieldDetailController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Integer currentPage, Integer pageSize) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		Page<AnnualYieldDetail> page = new Page<AnnualYieldDetail>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		webResultInfoWrapper.addResult("page", annualYieldDetailServiceRemote.queryPage(page));
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
		webResultInfoWrapper.addResult("annualYieldDetail", annualYieldDetailServiceRemote.get(id));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper update(AnnualYieldDetail annualYieldDetail) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		annualYieldDetailServiceRemote.update(annualYieldDetail);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

