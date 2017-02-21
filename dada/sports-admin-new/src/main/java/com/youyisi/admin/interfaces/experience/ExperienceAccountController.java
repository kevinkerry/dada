package com.youyisi.admin.interfaces.experience;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.experience.ExperienceAccountService;
import com.youyisi.admin.domain.experience.ExperienceAccount;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Controller
@RequestMapping("/experienceaccount")
public class ExperienceAccountController{

	@Autowired
	private ExperienceAccountService ExperienceAccountService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<ExperienceAccount> page = new Page<ExperienceAccount>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", ExperienceAccountService.queryPage(page));
		return "experienceaccount/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("ExperienceAccount", ExperienceAccountService.get(id));
		return "experienceaccount/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(ExperienceAccount ExperienceAccount) {
		ExperienceAccountService.update(ExperienceAccount);
		return "redirect:experienceaccount/list";
	}
}

