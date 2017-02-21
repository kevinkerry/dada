package com.youyisi.sports.interfaces.controller.relay;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.relay.RelayMemberFavourServiceRemote;
import com.youyisi.app.soa.remote.relay.RelayMemberServiceRemote;
import com.youyisi.app.soa.remote.user.UserServiceRemote;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.relay.RelayMember;
import com.youyisi.sports.domain.relay.RelayMemberFavour;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.gexinpush.PushToSingleHelper;
import com.youyisi.sports.gexinpush.TransmissionContent;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-10-08
 */
@Controller
@RequestMapping("/relaymemberfavour")
public class RelayMemberFavourController extends BaseController{

	@Autowired
	private RelayMemberFavourServiceRemote relayMemberFavourServiceRemote;
	@Autowired
	private RelayMemberServiceRemote relayMemberServiceRemote;
	
	@Autowired
	private UserServiceRemote userServiceRemote;
	private Logger log = LoggerFactory.getLogger(RelayMemberFavourController.class);


	@ResponseBody
	@RequestMapping(value = "/add", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper add(RelayMemberFavour relayMemberFavour) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(relayMemberFavour.getToken());
			Long date = DateUtil.currentDateForDay();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", user.getId());
			map.put("type", relayMemberFavour.getType());
			map.put("date",date);
			map.put("relayMemberId", relayMemberFavour.getRelayMemberId());
			RelayMemberFavour rmf = relayMemberFavourServiceRemote.getByMap(map);
			if(rmf!=null){
				webResultInfoWrapper.setState(ERROR);
				if(relayMemberFavour.getType().intValue()==1){
					webResultInfoWrapper.setMessage("您今天已经赞过了，明天再来！");
				}else{
					webResultInfoWrapper.setMessage("您今天已经加过油了，明天再来！");
				}
				return webResultInfoWrapper;
			}
			relayMemberFavour.setCreateTime(System.currentTimeMillis());
			relayMemberFavour.setDate(date);
			relayMemberFavour.setUserId(user.getId());
			relayMemberFavour = relayMemberFavourServiceRemote.save(relayMemberFavour);
			pushMessage(user,relayMemberFavour);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	private void pushMessage(User sender,RelayMemberFavour relayMemberFavour) throws SoaException {
		
		RelayMember relayMember = relayMemberServiceRemote.get(relayMemberFavour.getRelayMemberId());
		User user = userServiceRemote.get(relayMember.getUserId());
		TransmissionContent content = new TransmissionContent();
		if(relayMemberFavour.getType().intValue()==1){
			if(user.getPraiseRemind()==null||user.getPraiseRemind().intValue()==0){
				content.setTitle(sender.getNickname()+"赞了您！");
				content.setType("RELAY_PRAISE");
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("relayMemberId",relayMember.getId());
				param.put("activityId",relayMember.getActivityId());
				content.setEntity(param);
				content.setToUserId(user.getId());
				PushToSingleHelper.push(user, content);
			}
			
		}else{
			if(user.getCheerRemind()==null||user.getCheerRemind().intValue()==0){
				content.setTitle(sender.getNickname()+"为您加油啦！");
				content.setType("RELAY_CHEER");
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("relayMemberId",relayMember.getId());
				param.put("activityId",relayMember.getActivityId());
				content.setEntity(param);
				content.setToUserId(user.getId());
				PushToSingleHelper.push(user, content);
			}
			
		}
		
	}
}

