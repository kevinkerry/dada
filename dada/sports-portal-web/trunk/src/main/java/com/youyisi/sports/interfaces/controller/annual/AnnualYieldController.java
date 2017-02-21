package com.youyisi.sports.interfaces.controller.annual;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.annual.AnnualYieldServiceRemote;
import com.youyisi.app.soa.remote.distance.DistanceServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.annual.AnnualYield;
import com.youyisi.sports.domain.annual.AnnualYieldWithRun;
import com.youyisi.sports.domain.annual.AnnualYieldWithWalletDetail;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Controller
@RequestMapping("/annualyield")
public class AnnualYieldController extends BaseController{

	@Autowired
	private AnnualYieldServiceRemote annualYieldServiceRemote;
	@Autowired
	private DistanceServiceRemote distanceServiceRemote;

	private Logger log = LoggerFactory.getLogger(AnnualYieldController.class);

	@ResponseBody
	@RequestMapping(value = "/history", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper history(Integer currentPage, Integer pageSize,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		Page<AnnualYieldWithWalletDetail> page = new Page<AnnualYieldWithWalletDetail>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		User user = getUserByToken(token);
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		page.addParam("userId", user.getId());
		page = annualYieldServiceRemote.queryPageHistory(page);
		if(page.getCurrentPage()==1){
			page.getResult().get(0).setWalletDetail(annualYieldServiceRemote.predictFortune(user.getId(),page.getResult().get(0)));
		}
		webResultInfoWrapper.addResult("page",page);
		webResultInfoWrapper.setState(SUCCEED);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/sevenDays", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper sevenDays(AnnualYield annualYield) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(annualYield.getToken());
			Page<AnnualYield> page = new Page<AnnualYield>();
			page.setPageSize(7);
			page.addParam("userId", user.getId());
			webResultInfoWrapper.addResult("page", annualYieldServiceRemote.queryPage(page));
			webResultInfoWrapper.setState(SUCCEED);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/myRun", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper myRun(Page<AnnualYieldWithRun> page,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			page.addParam("userId", user.getId());
		webResultInfoWrapper.addResult("page", annualYieldServiceRemote.queryPageDetailWithRun(page));
		webResultInfoWrapper.addResult("sumDistance", distanceServiceRemote.getSumDistance(user.getId()));
		webResultInfoWrapper.setState(SUCCEED);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper update(AnnualYield annualYield) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		annualYieldServiceRemote.update(annualYield);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

