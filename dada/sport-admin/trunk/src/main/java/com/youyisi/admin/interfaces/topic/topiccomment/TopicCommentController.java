package com.youyisi.admin.interfaces.topic.topiccomment;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.topic.TopicService;
import com.youyisi.admin.application.topic.topiccomment.TopicCommentService;
import com.youyisi.admin.domain.topic.topiccomment.TopicComment;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Controller
@RequestMapping("/topiccomment")
public class TopicCommentController{

	@Autowired
	private TopicCommentService topicCommentService;
	
	@Resource 
	private TopicService topicService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Long topicId) {
		Page<TopicComment> page = new Page<TopicComment>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
		}
		page.addParam("topicId", topicId);
		model.addAttribute("topicId", topicId);
		model.addAttribute("page", topicCommentService.queryPage(page));
		return "topic/topiccomment/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("topicComment", topicCommentService.get(id));
		return "topic/topiccomment/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(TopicComment topicComment) {
		topicCommentService.update(topicComment);
		return "redirect:topiccomment/list";
	}
	
	@RequestMapping(value = "/changeStatus")
    public String changeStatus(Model model, Long commentId, String status) {
        TopicComment topicComment = topicCommentService.get(commentId);
        topicComment.setStatus(status);
        topicCommentService.save(topicComment);
        
        return list(model, 1, null ,topicComment.getTopicId());
    }
}

