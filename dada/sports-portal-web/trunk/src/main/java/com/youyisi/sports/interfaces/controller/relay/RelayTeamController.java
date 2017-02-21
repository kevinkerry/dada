package com.youyisi.sports.interfaces.controller.relay;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.activity.RelayRaceActivityServiceRemote;
import com.youyisi.app.soa.remote.relay.RelayMemberServiceRemote;
import com.youyisi.app.soa.remote.relay.RelayTeamServiceRemote;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.activity.RelayRaceActivity;
import com.youyisi.sports.domain.relay.RelayMember;
import com.youyisi.sports.domain.relay.RelayTeam;
import com.youyisi.sports.domain.relay.RelayTeamWithMore;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-09-05
 */
@Controller
@RequestMapping("/relayteam")
public class RelayTeamController extends BaseController{

	@Autowired
	private RelayTeamServiceRemote relayTeamServiceRemote;
	@Autowired
	private RelayMemberServiceRemote relayMemberServiceRemote;
	@Autowired
	private RelayRaceActivityServiceRemote relayRaceActivityServiceRemote;
	
	private Logger log = LoggerFactory.getLogger(RelayTeamController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Long activityId,Integer end,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			List<RelayTeamWithMore> list = relayTeamServiceRemote.getList(activityId,user,end);
		webResultInfoWrapper.addResult("list",list);
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
		webResultInfoWrapper.addResult("relayTeam", relayTeamServiceRemote.get(id));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper add(RelayTeam relayTeam) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(relayTeam.getToken());
			RelayRaceActivity relayRaceActivity = relayRaceActivityServiceRemote.getByActivityId(relayTeam.getActivityId());
			Integer count = relayTeamServiceRemote.getCountByActivityId(relayTeam.getActivityId());
			if(count.intValue()>=relayRaceActivity.getTeamLimit().intValue()){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("队伍数已经达到上限了");
				return webResultInfoWrapper;
			}
			RelayTeam r = relayTeamServiceRemote.getByActivityIdAndUserId(relayTeam.getActivityId(),user.getId());
			if(r!=null){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("您已经有队伍了");
				return webResultInfoWrapper;
			}
			RelayMember entity = relayMemberServiceRemote.getByActivityIdAndUserId(relayTeam.getActivityId(),user.getId());
			if(entity!=null){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("您已经参加了其他队伍不能创建队伍了");
				return webResultInfoWrapper;
			}

			if(r==null){
				relayTeam.setUserId(user.getId());
				relayTeam.setCreateTime(System.currentTimeMillis());
				relayTeam.setUpdateTime(System.currentTimeMillis());
				relayTeam.setStatus(0);
				relayTeam = relayTeamServiceRemote.save(relayTeam);
			}
			entity = new RelayMember();
			entity.setActivityId(relayTeam.getActivityId());
			entity.setCreateTime(System.currentTimeMillis());
			entity.setLevel(1);
			entity.setParentId(0l);
			entity.setPayStatus(0);
			entity.setStatus(0);
			entity.setTeamId(relayTeam.getId());
			entity.setUsercode(user.getUsercode());
			entity.setUserId(user.getId());
			entity = relayMemberServiceRemote.save(entity);
			
			webResultInfoWrapper.addResult("relayTeam", relayTeam);
			webResultInfoWrapper.addResult("relayMember", entity);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	
}

