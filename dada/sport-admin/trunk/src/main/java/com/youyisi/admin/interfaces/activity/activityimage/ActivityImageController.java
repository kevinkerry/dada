package com.youyisi.admin.interfaces.activity.activityimage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.activity.activityimage.ActivityImageService;
import com.youyisi.admin.domain.activity.activityimage.ActivityImage;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 7, 2015
 */
@Controller
@RequestMapping("/activityimage")
public class ActivityImageController{
    
    @Autowired
    private ActivityImageService activityimageService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        Page<ActivityImage> page = new Page<ActivityImage>();
        model.addAttribute("page", activityimageService.queryPage(page));
        return "activityimage/list";
    }
    
    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id,Model model) {
        model.addAttribute("activityimage", activityimageService.get(id));
        return "activityimage/form";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String detail(ActivityImage activityimage) {
        activityimageService.update(activityimage);
        return "redirect:activityimage/list";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(ActivityImage activityimage) {
        activityimageService.delete(activityimage);
        return "success";
    }
}

