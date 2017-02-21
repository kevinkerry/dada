package com.youyisi.sports.interfaces.controller.relay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.activity.ActivityServiceRemote;
import com.youyisi.app.soa.remote.activity.RelayRaceActivityServiceRemote;
import com.youyisi.app.soa.remote.relay.RelayMemberServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.activity.ActivityWithRelayRaceActivity;
import com.youyisi.sports.domain.activity.RelayRaceActivity;
import com.youyisi.sports.domain.relay.RelayMember;
import com.youyisi.sports.domain.relay.RelayMemberWithChildrenAndUser;
import com.youyisi.sports.domain.relay.RelayMemberWithMore;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-09-05
 */
@Controller
@RequestMapping("/relaymember")
public class RelayMemberController extends BaseController{

	@Autowired
	private RelayMemberServiceRemote relayMemberServiceRemote;
	@Autowired
	private ActivityServiceRemote activityServiceRemote;
	@Autowired
	private RelayRaceActivityServiceRemote relayRaceActivityServiceRemote;
	private Logger log = LoggerFactory.getLogger(RelayMemberController.class);

	@ResponseBody
	@RequestMapping(value = "/teamlist", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Integer currentPage, Integer pageSize,Long teamId,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
		Page<RelayMemberWithMore> page = new Page<RelayMemberWithMore>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		page.addParam("teamId", teamId);
		webResultInfoWrapper.addResult("page", relayMemberServiceRemote.queryPageForTeam(page,user.getId()));
		if(null != currentPage&&currentPage==1){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", user.getId());
			map.put("teamId", teamId);
			webResultInfoWrapper.addResult("myRelayMemberWithMore", relayMemberServiceRemote.getMyRelayMemberWithMore(map));
		}
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getByUsercode", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper getByCode(String token,String usercode,Long activityId) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			ActivityWithRelayRaceActivity activity = activityServiceRemote.getActivityWithRelayRaceActivityById(activityId);
		    if(activity.getEndTime()<System.currentTimeMillis()){
		    	webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("活动已结束！");
				return webResultInfoWrapper;
			}
		   
			
			if(user.getUsercode()!=null&&user.getUsercode().equals(usercode)){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("自己不能接力自己哦！");
				return webResultInfoWrapper;
			}
			RelayMember r = relayMemberServiceRemote.getByActivityIdAndUserId(activityId, user.getId());
			if(r!=null){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("您已经加入有队伍了");
				return webResultInfoWrapper;
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("usercode", usercode);
			map.put("activityId", activityId);
			 RelayMemberWithMore relayMember = relayMemberServiceRemote.getMyRelayMemberWithMore(map);
			 if(relayMember!=null){
				 List<RelayMemberWithChildrenAndUser> list = relayMemberServiceRemote.getRelayMemberWithChildrenAndUserByParentId(relayMember.getId());
					if((!list.isEmpty())&&activity.getRelayRaceActivity().getRelayBatonLimit()<=list.size()){
						webResultInfoWrapper.setState(ERROR);
						webResultInfoWrapper.setMessage("他的接力棒已经用完了");
						return webResultInfoWrapper;
					}
			 }
			 
		    webResultInfoWrapper.addResult("relayMemberWithMore",relayMember );
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/mylist", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper mylist(String token,Long id) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		webResultInfoWrapper.addResult("map", relayMemberServiceRemote.mylist(id));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	
	

	@ResponseBody
	@RequestMapping(value = "/{id}/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper detail(@PathVariable("id") Long id) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		webResultInfoWrapper.addResult("relayMember", relayMemberServiceRemote.get(id));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper add(RelayMember relayMember) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(relayMember.getToken());
			RelayMember rm = relayMemberServiceRemote.get(relayMember.getParentId());
			RelayRaceActivity relayRaceActivity = relayRaceActivityServiceRemote.getByActivityId(rm.getActivityId());
			List<RelayMemberWithChildrenAndUser> list = relayMemberServiceRemote.getRelayMemberWithChildrenAndUserByParentId(relayMember.getParentId());
			if(list!=null&&list.size()>0&&relayRaceActivity.getRelayBatonLimit()<=list.size()){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("他的接力棒已经用完了");
				return webResultInfoWrapper;
			}
			
			RelayMember r = relayMemberServiceRemote.getByActivityIdAndUserId(rm.getActivityId(), user.getId());
			if(r!=null){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("您已经加入有队伍了");
				return webResultInfoWrapper;
			}
			relayMember.setCreateTime(System.currentTimeMillis());
			relayMember.setPayStatus(0);
			relayMember.setStatus(0);
			relayMember.setUsercode(user.getUsercode());
			relayMember.setUserId(user.getId());
			relayMember.setLevel(rm.getLevel()+1);
			relayMember.setActivityId(rm.getActivityId());
			relayMember.setTeamId(rm.getTeamId());
			
			webResultInfoWrapper.addResult("relayMember", relayMemberServiceRemote.save(relayMember));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pay", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper pay(RelayMember relayMember) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(relayMember.getToken());
			RelayMember rm = relayMemberServiceRemote.get(relayMember.getId());
			if(user.getId().longValue()!=rm.getUserId().longValue()){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("没有权限哦");
				return webResultInfoWrapper;
			}
			if(rm.getPayStatus().intValue()==1){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("已经支付过了");
				return webResultInfoWrapper;
			}else{
				int result = relayMemberServiceRemote.pay(rm);
				if(result==-1){
					 webResultInfoWrapper.setState(ERROR);
		    		 webResultInfoWrapper.setMessage("余额不足!");
		    		 return webResultInfoWrapper;
				}
			}
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/isTeamMember", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper isTeamMember(Long teamId,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			RelayMember relayMember = relayMemberServiceRemote.getByTeamIdAndUserId(teamId, user.getId());
			if(relayMember==null){
				webResultInfoWrapper.addResult("isTeamMember", 0);
			}else{
				webResultInfoWrapper.addResult("isTeamMember", 1);
			}
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

