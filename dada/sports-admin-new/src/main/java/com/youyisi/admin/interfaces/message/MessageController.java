package com.youyisi.admin.interfaces.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.message.MessageService;
import com.youyisi.admin.domain.message.Message;
import com.youyisi.admin.infrastructure.helper.CurrentUserHelper;
import com.youyisi.admin.infrastructure.utils.StrUtil;
import com.youyisi.admin.interfaces.BaseController;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-24
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Integer status, String condition) {
		Page<Message> page = new Page<Message>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(defaultPageSize);
		}
		if (StrUtil.notEmpty(status)) {
			page.addParam("status", status);
		}
		if (StrUtil.notEmpty(condition)) {
			page.addParam("condition", condition);
		}
		model.addAttribute("page", messageService.queryPage(page));
		return "message/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("message", messageService.get(id));
		return "message/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, Message message) {
		Integer row = 0;
		if (message.getId() != null) {
			row = messageService.update(message);
		}
		if (row == 0) {
			model.addAttribute(result, "更新失败");
		}
		return list(model, 1, null, null, null);
	}

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String send(Model model, Long mid) {
		Integer row = messageService.send(mid);
		if (row == 0) {
			model.addAttribute(result, "推送失败");
		}
		return list(model, 1, null, null, null);
	}

	@RequestMapping(value = "/sendOne", method = RequestMethod.GET)
	@ResponseBody
	public String sendOne(Long mid, Long userId) {
		Integer row = messageService.sendOne(mid, userId);
		return "success";
	}

	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public String del(Model model, Long mid) {
		if (mid != null) {
			Message message = new Message();
			message.setId(mid);
			Integer row = messageService.delete(message);
			if (row == 0) {
				model.addAttribute(result, "删除失败");
			}
		}
		return list(model, 1, null, null, null);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model, Message message) {
		if (message.getTitle() != null) {
			message.setAdminId(CurrentUserHelper.getCurrentUser().getId().longValue());
			Message save = messageService.save(message);
			if (save.getId() == null) {
				model.addAttribute(result, "新增失败");
			}
		}
		return list(model, 1, null, null, null);
	}

}
