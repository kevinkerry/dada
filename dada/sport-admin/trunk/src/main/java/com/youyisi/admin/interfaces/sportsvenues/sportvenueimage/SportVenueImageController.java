package com.youyisi.admin.interfaces.sportsvenues.sportvenueimage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.sportsvenues.sportvenueimage.SportVenueImageService;
import com.youyisi.admin.domain.sportsvenues.sportvenueimage.SportVenueImage;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-07-24
 */
@Controller
@RequestMapping("/sportvenueimage")
public class SportVenueImageController{

	@Autowired
	private SportVenueImageService sportVenueImageService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		Page<SportVenueImage> page = new Page<SportVenueImage>();
		model.addAttribute("page", sportVenueImageService.queryPage(page));
		return "sportvenueimage/list";
	}
	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("sportVenueImage", sportVenueImageService.get(id));
		return "sportvenueimage/form";
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(SportVenueImage sportVenueImage) {
		sportVenueImageService.update(sportVenueImage);
		return "redirect:sportvenueimage/list";
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(SportVenueImage sportVenueImage) {
	    sportVenueImageService.delete(sportVenueImage);
        return "success";
    }
}

