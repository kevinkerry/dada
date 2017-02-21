package com.youyisi.vote.interfaces.controller.spot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.vote.application.spot.SpotService;

/**
 * @author shuye
 * @time 2014年5月8日
 */
@Controller
@RequestMapping(value="/spot")
public class SpotController {
	@Autowired
	private SpotService spotService;
	
	
	private static final Log logger = LogFactory.getLog(SpotController.class);
	
	@RequestMapping(value="/lucky",method = RequestMethod.GET)
	@ResponseBody
	public String lucky(String name,String mobile,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		 Pattern p = Pattern.compile("1\\d{10}");
		 Matcher m = p.matcher(mobile);
		 boolean b = m.matches();
		 if(!b){
			return "notmobile"; 
		 }
		if(StringUtils.isNotBlank(name)&&StringUtils.isNotBlank(mobile)){
			return spotService.lucky(name,mobile);
		}
		return "noparams";
	}
	
	@RequestMapping(value="/ba",method = RequestMethod.GET)
	public String ba(){
		return "spot/ba";
	}
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String index(){
		return "spot/index";
	}
	

	@RequestMapping(value="/ju",method = RequestMethod.GET)
	public String ju(){
		return "spot/ju";
	}
	
	@RequestMapping(value="/spot",method = RequestMethod.GET)
	public String spot(Model model){
		model.addAttribute("spots", spotService.queryAllSpot());
		return "spot/spot";
	}
	
	
}
