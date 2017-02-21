package com.youyisi.admin.interfaces.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.feedback.FeedbackService;
import com.youyisi.admin.domain.feedback.Feedback;
import com.youyisi.admin.infrastructure.utils.StrUtil;
import com.youyisi.admin.interfaces.BaseController;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-23
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController extends BaseController {

	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Integer status, String condition) {
		Page<Feedback> page = new Page<Feedback>();
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
		model.addAttribute("page", feedbackService.queryPageFeedback(page));
		return "feedback/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("feedback", feedbackService.get(id));
		return "feedback/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Feedback feedback) {
		feedbackService.update(feedback);
		return "redirect:feedback/list";
	}

	@RequestMapping(value = "/dispose", method = RequestMethod.GET)
	public String dispose(Model model, Long fid) {
		if (fid != null) {
			Integer row = feedbackService.dispose(fid);
			if (row > 0) {
				model.addAttribute("result", "处理成功");
			} else {
				model.addAttribute("result", "处理失败");
			}
		}
		return list(model, 1, null, null, null);
	}
}
