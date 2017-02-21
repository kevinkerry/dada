package com.youyisi.admin.interfaces.activity.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.activity.ActivityService;
import com.youyisi.admin.application.activity.comment.CommentService;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.activity.comment.Comment;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
@Controller
@RequestMapping("/comment")
public class CommentController{

	@Autowired
	private CommentService commentService;
	
	@Autowired
    private ActivityService activityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Long activityId) {
		Page<Comment> page = new Page<Comment>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
		}
		page.addParam("activityId", activityId);
        
        model.addAttribute("activityId", activityId);
        Activity activity = activityService.get(activityId);
        model.addAttribute("activityTitle", activity.getActivityTitle());
		model.addAttribute("page", commentService.queryPage(page));
		return "activity/comment/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("comment", commentService.get(id));
		return "activity/comment/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Comment comment) {
		commentService.update(comment);
		return "redirect:comment/list";
	}
	
	@RequestMapping(value = "/changeStatus")
	public String changeStatus(Model model, Long commentId, String status) {
	    Comment comment = commentService.get(commentId);
	    comment.setStatus(status);
	    commentService.save(comment);
	    
	    return list(model, 1, null ,comment.getActivityId());
	}
}

