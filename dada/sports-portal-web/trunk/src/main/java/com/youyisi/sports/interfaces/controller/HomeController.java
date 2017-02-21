package com.youyisi.sports.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.lang.domain.WebResultInfoWrapper;

@Controller
public class HomeController extends BaseController{

	@ResponseBody
	@RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper test() {
		return null;
	}
}
