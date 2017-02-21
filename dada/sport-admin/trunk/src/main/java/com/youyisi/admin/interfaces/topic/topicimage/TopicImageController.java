package com.youyisi.admin.interfaces.topic.topicimage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.topic.topicimage.TopicImageService;
import com.youyisi.admin.domain.topic.topicimage.TopicImage;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Controller
@RequestMapping("/topicimage")
public class TopicImageController{

	@Autowired
	private TopicImageService topicImageService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<TopicImage> page = new Page<TopicImage>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
		}
		model.addAttribute("page", topicImageService.queryPage(page));
		return "topicimage/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("topicImage", topicImageService.get(id));
		return "topicimage/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(TopicImage topicImage) {
		topicImageService.update(topicImage);
		return "redirect:topicimage/list";
	}
}

