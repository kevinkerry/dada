package com.youyisi.admin.interfaces.relay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.relay.RelayMemberFavourService;
import com.youyisi.admin.domain.relay.RelayMemberFavour;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-10
 */
@Controller
@RequestMapping("/relaymemberfavour")
public class RelayMemberFavourController{

	@Autowired
	private RelayMemberFavourService RelayMemberFavourService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<RelayMemberFavour> page = new Page<RelayMemberFavour>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", RelayMemberFavourService.queryPage(page));
		return "relaymemberfavour/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("RelayMemberFavour", RelayMemberFavourService.get(id));
		return "relaymemberfavour/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(RelayMemberFavour RelayMemberFavour) {
		RelayMemberFavourService.update(RelayMemberFavour);
		return "redirect:relaymemberfavour/list";
	}
}

