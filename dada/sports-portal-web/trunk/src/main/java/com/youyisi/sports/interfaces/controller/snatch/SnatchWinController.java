package com.youyisi.sports.interfaces.controller.snatch;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.activity.ActivityServiceRemote;
import com.youyisi.app.soa.remote.snatch.SnatchActivityServiceRemote;
import com.youyisi.app.soa.remote.snatch.SnatchWinServiceRemote;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.activity.Activity;
import com.youyisi.sports.domain.snatch.ActivityWithSnatchActivity;
import com.youyisi.sports.domain.snatch.SnatchWin;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-09-21
 */
@Controller
@RequestMapping("/snatchwin")
public class SnatchWinController extends BaseController{

	@Autowired
	private SnatchWinServiceRemote snatchWinServiceRemote;
	@Autowired
	private ActivityServiceRemote activityServiceRemote;
	@Autowired
	private SnatchActivityServiceRemote snatchActivityServiceRemote;
	
	private Logger log = LoggerFactory.getLogger(SnatchWinController.class);

	@ResponseBody
	@RequestMapping(value = "/result", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper result(String token,Long activityId) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		webResultInfoWrapper.addResult("list", snatchWinServiceRemote.winResult(activityId));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/latest", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper latest(String token,Long activityId) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			Activity a = activityServiceRemote.get(activityId);
			ActivityWithSnatchActivity activity = activityServiceRemote.getLatest(a.getBeginTime(),4);
			webResultInfoWrapper.addResult("activity", activity);
			if(activity!=null){
				webResultInfoWrapper.addResult("list", snatchWinServiceRemote.winResult(activity.getId()));
			}
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/haveWin", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper haveWin(String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			SnatchWin snatchWin = snatchWinServiceRemote.getByUserId(user.getId());
			if(snatchWin!=null){
				ActivityWithSnatchActivity activityWithSnatchActivity = activityServiceRemote.getActivityWithSnatchActivityById(snatchWin.getActivityId());
				Calendar calendar = new GregorianCalendar();
				calendar.add(Calendar.DATE, activityWithSnatchActivity.getSnatchActivity().getExpiryDay());
				webResultInfoWrapper.addResult("expiryTime", calendar.getTime().getTime());
			}
		webResultInfoWrapper.addResult("snatchWin", snatchWin);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper update(String token,Long id) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			SnatchWin snatchWin = snatchWinServiceRemote.get(id);
			snatchWin.setStatus(1);
			snatchWinServiceRemote.update(snatchWin);
		webResultInfoWrapper.addResult("snatchWin", snatchWin);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

