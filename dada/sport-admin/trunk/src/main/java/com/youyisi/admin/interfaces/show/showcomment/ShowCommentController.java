package com.youyisi.admin.interfaces.show.showcomment;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.show.ShowService;
import com.youyisi.admin.application.show.showcomment.ShowCommentService;
import com.youyisi.admin.domain.show.Show;
import com.youyisi.admin.domain.show.showcomment.ShowComment;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
@Controller
@RequestMapping("/showcomment")
public class ShowCommentController{

	@Autowired
	private ShowCommentService showCommentService;
	
	@Autowired
    private ShowService showService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Long showId, String userName) {
		Page<ShowComment> page = new Page<ShowComment>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
		}
		page.addParam("showId", showId);
		
		model.addAttribute("showId", showId);
	    try {
	        if(StringUtils.isNotBlank(userName)) {
	            model.addAttribute("userName", URLDecoder.decode(userName, "UTF-8"));
	        }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		model.addAttribute("page", showCommentService.queryPage(page));
		return "show/showcomment/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("showComment", showCommentService.get(id));
		return "show/showcomment/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(ShowComment showComment) {
		showCommentService.update(showComment);
		return "redirect:showcomment/list";
	}
	
	@RequestMapping(value = "/changeStatus")
    public String changeStatus(Model model, Long commentId, String status) {
        ShowComment showComment = showCommentService.get(commentId);
        showComment.setStatus(status);
        showCommentService.save(showComment);
        
        Show show = showService.get(showComment.getShowId());
        return list(model, 1, null ,show.getShowId(), show.getShowUser().getUserName());
    }
}

