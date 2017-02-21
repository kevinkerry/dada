package com.youyisi.admin.interfaces.sportuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.sportuser.SportUserService;
import com.youyisi.admin.domain.sportuser.SportUser;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
@Controller
@RequestMapping("/sportuser")
public class SportUserController{
    
    @Autowired
    private SportUserService sportuserService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        Page<SportUser> page = new Page<SportUser>();
        model.addAttribute("page", sportuserService.queryPage(page));
        return "sportuser/list";
    }
    
    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id,Model model) {
        model.addAttribute("sportuser", sportuserService.get(id));
        return "sportuser/form";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String detail(SportUser sportuser) {
        sportuserService.update(sportuser);
        return "redirect:sportuser/list";
    }
}

