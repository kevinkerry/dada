package com.youyisi.admin.interfaces.medal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.medal.MedalService;
import com.youyisi.admin.domain.medal.Medal;
import com.youyisi.admin.infrastructure.utils.ResponseModel;
import com.youyisi.admin.interfaces.BaseController;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-07
 */
@Controller
@RequestMapping("/medal")
public class MedalController extends BaseController {

	@Autowired
	private MedalService medalService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<Medal> page = new Page<Medal>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(10);
		}
		model.addAttribute("page", medalService.queryPage(page));
		return "medal/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("Medal", medalService.get(id));
		return "medal/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Medal Medal) {
		medalService.update(Medal);
		return "redirect:medal/list";
	}

	@RequestMapping(value = "/gotoPage", method = RequestMethod.GET)
	public String gotoPage(Model model, String page, Long id) {
		if (id != null) {
			model.addAttribute("medal", medalService.get(id));
		}
		return "medal/" + page;
	}

	@RequestMapping(value = "/addMedal", method = RequestMethod.POST)
	public String addMedal(Model model, Medal medal) {
		Integer row = medalService.addMedal(medal);
		if (row == 0) {
			model.addAttribute(result, "添加失败");
		}
		return list(model, 1, null);
	}

	@RequestMapping(value = "/updateMedal", method = RequestMethod.POST)
	public String updateMedal(Model model, Medal medal) {
		Integer row = medalService.updateMedal(medal);
		if (row == 0) {
			model.addAttribute(result, "更新失败");
		}
		return list(model, 1, null);
	}

	@RequestMapping(value = "/delMedal", method = RequestMethod.GET)
	public String delMedal(Model model, Long id) {
		Integer row = medalService.delMedal(id);
		if (row == 0) {
			model.addAttribute(result, "删除失败");
		}
		return list(model, 1, null);
	}

	@ResponseBody
	@RequestMapping(value = "/getMedalList", method = RequestMethod.GET)
	public ResponseModel getMedalList(Integer category, Integer type) {
		ResponseModel response = new ResponseModel();
		response.setMap("medallist", medalService.getMedalList(category, type));
		response.success();
		return response;
	}

}
