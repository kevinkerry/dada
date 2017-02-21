package com.youyisi.admin.interfaces.member.memberimage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.member.memberimage.MemberImageService;
import com.youyisi.admin.domain.member.memberimage.MemberImage;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-07-28
 */
@Controller
@RequestMapping("/memberimage")
public class MemberImageController{

	@Autowired
	private MemberImageService memberImageService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<MemberImage> page = new Page<MemberImage>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
		}
		model.addAttribute("page", memberImageService.queryPage(page));
		return "memberimage/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("memberImage", memberImageService.get(id));
		return "memberimage/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(MemberImage memberImage) {
		memberImageService.update(memberImage);
		return "redirect:memberimage/list";
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
    public String delete(Long imgId) {
	    MemberImage memberImage = memberImageService.get(imgId);
        memberImageService.delete(memberImage);
        return "success";
    }
}

