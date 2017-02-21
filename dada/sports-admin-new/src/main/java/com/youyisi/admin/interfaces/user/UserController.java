package com.youyisi.admin.interfaces.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.user.UserService;
import com.youyisi.admin.domain.user.RequestUser;
import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.admin.infrastructure.utils.ResponseModel;
import com.youyisi.admin.infrastructure.utils.StrUtil;
import com.youyisi.admin.interfaces.BaseController;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-16
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, RequestUser reqUser) {
		Page<User> page = new Page<User>();
		if (null != reqUser.getCurrentPage()) {
			page.setCurrentPage(reqUser.getCurrentPage());
		}
		if (null != reqUser.getPageSize()) {
			page.setPageSize(reqUser.getPageSize());
		} else {
			page.setPageSize(50);
		}
		if (StrUtil.notEmpty(reqUser.getCondition())) {
			page.addParam("condition", reqUser.getCondition());
		}
		if (StrUtil.notEmpty(reqUser.getField())) {
			page.addParam("field", reqUser.getField());
		}
		if (StrUtil.notEmpty(reqUser.getSort())) {
			page.addParam("sort", reqUser.getSort());
		}
		if (StrUtil.notEmpty(reqUser.getBeginTime()) && StrUtil.notEmpty(reqUser.getEndTime())) {
			page.addParam("beginTime", DateUtil.strToTimestamp(reqUser.getBeginTime().replace("T", " ") + ":00"));
			page.addParam("endTime", DateUtil.strToTimestamp(reqUser.getEndTime().replace("T", " ") + ":00"));
			page.addParam("dateType", reqUser.getDateType());
		}
		Page<User> queryPageUserList = userService.queryPageUserList(page);
		queryPageUserList.addParam("beginTime", reqUser.getBeginTime());
		queryPageUserList.addParam("endTime", reqUser.getEndTime());
		queryPageUserList.addParam("dateType", reqUser.getDateType());
		model.addAttribute("page", queryPageUserList);
		return "user/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("User", userService.get(id));
		return "user/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(User user) {
		userService.update(user);
		return "redirect:user/list";
	}

	@ResponseBody
	@RequestMapping(value = "/getUserById", method = RequestMethod.GET)
	public ResponseModel getUserById(Long userId) {
		ResponseModel response = new ResponseModel();
		User user = userService.get(userId);
		response.setMap("user", user);
		response.success();
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/getManageMoney", method = RequestMethod.GET)
	public ResponseModel getManageMoney(Long userId) {
		ResponseModel response = new ResponseModel();
		if (userId != null) {
			response.setMap("manageMoney", userService.getManageMoney(userId));
			response.success();
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/getSportRecord", method = RequestMethod.GET)
	public ResponseModel getSportRecord(Long userId) {
		ResponseModel response = new ResponseModel();
		if (userId != null) {
			response.setMap("sportRecord", userService.getSportRecord(userId));
			response.success();
		}
		return response;
	}

	@RequestMapping(value = "/getClientIdList", method = RequestMethod.GET)
	public String getClientIdList(Model model, Integer currentPage, Integer pageSize, String clientId) {
		Page<User> page = new Page<User>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(defaultPageSize);
		}
		if (StrUtil.notEmpty(clientId)) {
			page.addParam("clientId", clientId);
			model.addAttribute("page", userService.queryPage(page));
		}
		List<User> clientIdAndClientIdNum = userService.getClientIdAndClientIdNum();
		model.addAttribute("num", clientIdAndClientIdNum);
		return "user/monitoring";
	}

}
