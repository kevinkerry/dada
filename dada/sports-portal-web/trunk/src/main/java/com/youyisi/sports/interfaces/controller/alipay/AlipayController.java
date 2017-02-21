package com.youyisi.sports.interfaces.controller.alipay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.alipay.AlipayServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.alipay.Alipay;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-05-19
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController extends BaseController{

	@Autowired
	private AlipayServiceRemote alipayServiceRemote;

	private Logger log = LoggerFactory.getLogger(AlipayController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Integer currentPage, Integer pageSize) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		Page<Alipay> page = new Page<Alipay>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		webResultInfoWrapper.addResult("page", alipayServiceRemote.queryPage(page));
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
		webResultInfoWrapper.addResult("alipay", alipayServiceRemote.get(id));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/get", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper get(Alipay alipay) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(alipay.getToken());
		    webResultInfoWrapper.addResult("alipay", alipayServiceRemote.getByUserId(user.getId()));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper save(Alipay alipay) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(alipay.getToken());
			alipay.setCreateTime(System.currentTimeMillis());
			alipay.setUserId(user.getId());
			Alipay a = alipayServiceRemote.getByUserId(user.getId());
			if(a==null){
				Alipay existAlipay = alipayServiceRemote.getByAlipay(alipay.getAlipay());
				if(existAlipay==null){
					webResultInfoWrapper.addResult("alipay", alipayServiceRemote.save(alipay));
				}else{
					webResultInfoWrapper.setState(ERROR);
					webResultInfoWrapper.setMessage("您的支付宝账号已经绑定到另外的账号了");
				}
			}else{
				a.setAlipay(alipay.getAlipay());
				a.setRealName(alipay.getRealName());
				webResultInfoWrapper.addResult("alipay", alipayServiceRemote.update(a));
			}
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

