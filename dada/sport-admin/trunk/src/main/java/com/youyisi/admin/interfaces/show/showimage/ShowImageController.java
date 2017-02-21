package com.youyisi.admin.interfaces.show.showimage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.show.showimage.ShowImageService;
import com.youyisi.admin.domain.show.showimage.ShowImage;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
@Controller
@RequestMapping("/showimage")
public class ShowImageController{

	@Autowired
	private ShowImageService showImageService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<ShowImage> page = new Page<ShowImage>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
		}
		model.addAttribute("page", showImageService.queryPage(page));
		return "showimage/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("showImage", showImageService.get(id));
		return "showimage/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(ShowImage showImage) {
		showImageService.update(showImage);
		return "redirect:showimage/list";
	}
	
	@RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(Long imgId) {
        ShowImage showImage = showImageService.get(imgId);
        showImageService.delete(showImage);
        return "success";
    }
}

