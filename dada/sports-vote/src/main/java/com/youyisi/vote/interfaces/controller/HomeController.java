package com.youyisi.vote.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyisi.vote.infrastructure.util.SignUtil;

/**
 * @author shuye
 * @time 2014年5月8日
 */
@Controller
public class HomeController {
	private String url="http://vote.dadasports.cn";
	
	@RequestMapping(value="/index")
	public String index(Model model){
		model.addAttribute("sign", SignUtil.getSin(url+"/index"));
		return "vote";
	}

	@RequestMapping(value="/activityResult")
	public String activityResult(Model model){
		model.addAttribute("sign", SignUtil.getSin(url+"/activityResult"));
		return "activity-result";
	}
	
}
