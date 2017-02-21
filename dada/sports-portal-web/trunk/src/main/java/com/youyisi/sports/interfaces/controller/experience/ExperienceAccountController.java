package com.youyisi.sports.interfaces.controller.experience;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.experience.ExperienceAccountServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.experience.ExperienceAccount;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.user.UserMoreInfo;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Controller
@RequestMapping("/experienceaccount")
public class ExperienceAccountController extends BaseController {

	@Autowired
	private ExperienceAccountServiceRemote experienceAccountServiceRemote;

	private Logger log = LoggerFactory.getLogger(ExperienceAccountController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Integer currentPage, Integer pageSize) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			Page<ExperienceAccount> page = new Page<ExperienceAccount>();
			if (null != currentPage) {
				page.setCurrentPage(currentPage);
			}
			if (null != pageSize) {
				page.setPageSize(pageSize);
			} else {
				page.setPageSize(10);
			}
			webResultInfoWrapper.addResult("page", experienceAccountServiceRemote.queryPage(page));
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
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
			webResultInfoWrapper.addResult("experienceAccount", experienceAccountServiceRemote.get(id));
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper update(ExperienceAccount experienceAccount) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			experienceAccountServiceRemote.update(experienceAccount);
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/getExperienceMoney", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper getExperienceMoney(String token) {
		WebResultInfoWrapper result = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			UserMoreInfo us = experienceAccountServiceRemote.addExperienceAccount(user);
			us.setToken(token);
			result.addResult("user", us);
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			result.setState(ERROR);
			result.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return result;
	}

}
