package com.youyisi.admin.interfaces;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model) {
		
		return "index";
	}
	
	
	
	
}
