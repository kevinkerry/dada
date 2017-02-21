package com.youyisi.admin.interfaces.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.user.UserMedalService;
import com.youyisi.admin.domain.user.UserMedal;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-08
 */
@Controller
@RequestMapping("/usermedal")
public class UserMedalController{

	@Autowired
	private UserMedalService UserMedalService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<UserMedal> page = new Page<UserMedal>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", UserMedalService.queryPage(page));
		return "usermedal/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("UserMedal", UserMedalService.get(id));
		return "usermedal/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(UserMedal UserMedal) {
		UserMedalService.update(UserMedal);
		return "redirect:usermedal/list";
	}
}

