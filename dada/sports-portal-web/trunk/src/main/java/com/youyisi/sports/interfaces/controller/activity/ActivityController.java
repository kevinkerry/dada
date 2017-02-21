package com.youyisi.sports.interfaces.controller.activity;

import java.util.Calendar;
import java.util.Date;
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
import com.youyisi.app.soa.remote.relay.RelayLineServiceRemote;
import com.youyisi.app.soa.remote.relay.RelayMemberServiceRemote;
import com.youyisi.app.soa.remote.relay.RelayTeamServiceRemote;
import com.youyisi.app.soa.remote.snatch.SnatchActivityServiceRemote;
import com.youyisi.app.soa.remote.snatch.UserSnatchServiceRemote;
import com.youyisi.app.soa.remote.thigh.HugThighServiceRemote;
import com.youyisi.app.soa.remote.thigh.ThighServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.activity.Activity;
import com.youyisi.sports.domain.activity.ActivityWithHugThighActivity;
import com.youyisi.sports.domain.activity.ActivityWithInviteFriendActivity;
import com.youyisi.sports.domain.activity.ActivityWithRelayRaceActivity;
import com.youyisi.sports.domain.relay.RelayMember;
import com.youyisi.sports.domain.relay.RelayTeam;
import com.youyisi.sports.domain.snatch.ActivityWithSnatchActivity;
import com.youyisi.sports.domain.thigh.Thigh;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-07-13
 */
@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController{

	@Autowired
	private ActivityServiceRemote activityServiceRemote;
	@Autowired
	private ThighServiceRemote thighServiceRemote;
	@Autowired
	private RelayTeamServiceRemote relayTeamServiceRemote;
	@Autowired
	private HugThighServiceRemote hugThighServiceRemote;
	@Autowired
	private RelayMemberServiceRemote relayMemberServiceRemote;
	@Autowired
	private UserSnatchServiceRemote userSnatchServiceRemote;
	@Autowired
	private RelayLineServiceRemote relayLineServiceRemote;
	@Autowired
	private SnatchActivityServiceRemote snatchActivityServiceRemote;
	
	private Logger log = LoggerFactory.getLogger(ActivityController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(String token,Integer type) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			if(type==null){
				type=3;
			}
		Page<Activity> page = new Page<Activity>();
		page.addParam("currentDate",System.currentTimeMillis());
		page.addParam("status", 0);
		page.addParam("type", type);
		webResultInfoWrapper.addResult("progressActivity", activityServiceRemote.queryPage(page).getResult());
		page.addParam("status", 1);
		webResultInfoWrapper.addResult("yetToBeginActivity", activityServiceRemote.queryPage(page).getResult());
		page.addParam("status", -1);
		webResultInfoWrapper.addResult("endActivity", activityServiceRemote.queryPage(page).getResult());
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper detail(Long id,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		User user = getUserByToken(token);
		try {
			ActivityWithHugThighActivity activity = activityServiceRemote.getActivityWithHugThighActivityById(id);
		webResultInfoWrapper.addResult("activity", activity);
		Thigh thigh = thighServiceRemote.getThigh(id,user.getId());
		Boolean isThigh = false;
		if(thigh!=null){
			isThigh = true;
		}
		webResultInfoWrapper.addResult("isThigh", isThigh);
		if(isThigh){
			webResultInfoWrapper.addResult("thigh", thigh);
			webResultInfoWrapper.addResult("hugmeCount", hugThighServiceRemote.getHugThighCount(thigh.getId(),System.currentTimeMillis()-10*60*1000l));
		}
		if(activity.getBeginTime()<System.currentTimeMillis()&&activity.getEndTime()>System.currentTimeMillis()){
			webResultInfoWrapper.addResult("thighCount", thighServiceRemote.thighCount(id,System.currentTimeMillis()-5*60*1000l));
			webResultInfoWrapper.addResult("isbegin",1 );
		}else if(activity.getBeginTime()>System.currentTimeMillis()){
			webResultInfoWrapper.addResult("isbegin",0 );
		}else if(activity.getEndTime()<System.currentTimeMillis()){
			webResultInfoWrapper.addResult("isbegin",-1 );
		}
		webResultInfoWrapper.addResult("ishugthigh",hugThighServiceRemote.getCount(user.getId(),id)); 
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/invitefrienddetail", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper invitefrienddetail(Long id,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			ActivityWithInviteFriendActivity activity = activityServiceRemote.getActivityWithInviteFriendActivityById(id);
		webResultInfoWrapper.addResult("activity", activity);
		if(activity.getBeginTime()<System.currentTimeMillis()&&activity.getEndTime()>System.currentTimeMillis()){
			webResultInfoWrapper.addResult("isbegin",1 );
		}else if(activity.getBeginTime()>System.currentTimeMillis()){
			webResultInfoWrapper.addResult("isbegin",0 );
		}else if(activity.getEndTime()<System.currentTimeMillis()){
			webResultInfoWrapper.addResult("isbegin",-1 );
		}
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/relayracedetail", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper relayracedetail(Long id,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		Double sumMoney = 0.0;
		try {
			User user = getUserByToken(token);
			ActivityWithRelayRaceActivity activity = activityServiceRemote.getActivityWithRelayRaceActivityById(id);
		webResultInfoWrapper.addResult("activity", activity);
		if(activity.getBeginTime()<System.currentTimeMillis()&&activity.getEndTime()>System.currentTimeMillis()){
			sumMoney = getSumMoney(activity);
			webResultInfoWrapper.addResult("isbegin",1 );
			webResultInfoWrapper.addResult("list", relayLineServiceRemote.getByActivityId(id));
		}else if(activity.getBeginTime()>System.currentTimeMillis()){
			webResultInfoWrapper.addResult("isbegin",0 );
		}else if(activity.getEndTime()<System.currentTimeMillis()){
			sumMoney = getSumMoney(activity);
			webResultInfoWrapper.addResult("isbegin",-1 );
			webResultInfoWrapper.addResult("list", relayLineServiceRemote.getByActivityId(id));
		}
		
		RelayTeam r = relayTeamServiceRemote.getByActivityIdAndUserId(id,user.getId());
		if(r!=null){
			webResultInfoWrapper.addResult("haveTeam",1);
		}else{
			webResultInfoWrapper.addResult("haveTeam",0);
		}
		RelayMember entity = relayMemberServiceRemote.getByActivityIdAndUserId(id,user.getId());
		if(entity!=null){
			webResultInfoWrapper.addResult("haveMember",1);
		}else{
			webResultInfoWrapper.addResult("haveMember",0);
		}

		webResultInfoWrapper.addResult("sumMoney",sumMoney);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/snatchdetail", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper snatchdetail(Long id,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		Double sumMoney = 0.0;
		try {
			User user = getUserByToken(token);
			ActivityWithSnatchActivity activity = activityServiceRemote.getActivityWithSnatchActivityById(id);
		webResultInfoWrapper.addResult("activity", activity);
		if(activity.getBeginTime()<System.currentTimeMillis()&&activity.getEndTime()>System.currentTimeMillis()){
			Integer count = userSnatchServiceRemote.getCountByActivityId(activity.getId());
			webResultInfoWrapper.addResult("myCount",userSnatchServiceRemote.getCountByActivityIdAndUserId(activity.getId(),user.getId()));
			sumMoney = getSumMoney(activity,count);
			webResultInfoWrapper.addResult("totalCount",count);
			webResultInfoWrapper.addResult("distance",snatchActivityServiceRemote.getUserDistance(activity,user.getId()));
			webResultInfoWrapper.addResult("lotteryTime",getLotteryTime(activity));
			webResultInfoWrapper.addResult("isbegin",1 );
		}else if(activity.getBeginTime()>System.currentTimeMillis()){
			webResultInfoWrapper.addResult("isbegin",0 );
		}else if(activity.getEndTime()<System.currentTimeMillis()){
			Integer count = userSnatchServiceRemote.getCountByActivityId(activity.getId());
			webResultInfoWrapper.addResult("myCount",userSnatchServiceRemote.getCountByActivityIdAndUserId(activity.getId(),user.getId()));
			sumMoney = getSumMoney(activity,count);
			webResultInfoWrapper.addResult("distance",snatchActivityServiceRemote.getUserDistance(activity,user.getId()));
			webResultInfoWrapper.addResult("totalCount",count);
			webResultInfoWrapper.addResult("lotteryTime",getLotteryTime(activity));
			webResultInfoWrapper.addResult("isbegin",-1 );
		}
		webResultInfoWrapper.addResult("sumMoney",sumMoney);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	private String getLotteryTime(ActivityWithSnatchActivity activity) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date(activity.getEndTime()));
		calendar.add(Calendar.DATE, 1);
		return DateUtil.formatDateToDay(calendar.getTime().getTime());
	}

	private Double getSumMoney(ActivityWithRelayRaceActivity activity) throws SoaException {
		Double sumMoney;
		Integer count = relayMemberServiceRemote.getCountByActivityId(activity.getId());
		if(count==null){
			count=0;
		}
		sumMoney = count*activity.getRelayRaceActivity().getContributeBonus();
		return sumMoney;
	}
	
	private Double getSumMoney(ActivityWithSnatchActivity activity,Integer count) throws SoaException {
		Double sumMoney;
		if(count==null){
			count=0;
		}
		if(count<=activity.getSnatchActivity().getMinNum()){
			return activity.getSnatchActivity().getMinNum()*activity.getSnatchActivity().getContributeBonus();
		}
		sumMoney = count*activity.getSnatchActivity().getContributeBonus();
		return sumMoney;
	}
}

