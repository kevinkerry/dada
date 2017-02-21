package com.youyisi.sports.interfaces.controller.relay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.relay.RelayMemberServiceRemote;
import com.youyisi.app.soa.remote.relay.RelayMessageServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.relay.RelayMember;
import com.youyisi.sports.domain.relay.RelayMessage;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * @author shuye
 * @time 2016-10-08
 */
@Controller
@RequestMapping("/relaymessage")
public class RelayMessageController extends BaseController{

	@Autowired
	private RelayMessageServiceRemote relayMessageServiceRemote;
	@Autowired
	private RelayMemberServiceRemote relayMemberServiceRemote;
	private Logger log = LoggerFactory.getLogger(RelayMessageController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Integer currentPage, Integer pageSize,Long teamId,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		Page<RelayMessage> page = new Page<RelayMessage>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		page.addParam("teamId", teamId);
		webResultInfoWrapper.addResult("page", relayMessageServiceRemote.queryPage(page));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	

	@ResponseBody
	@RequestMapping(value = "/somenew", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper somenew(Long teamId,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			webResultInfoWrapper.addResult("newUser", relayMessageServiceRemote.newUser(teamId));
			webResultInfoWrapper.addResult("totalCount", relayMessageServiceRemote.totalCount(teamId));
		    webResultInfoWrapper.addResult("newMessage", relayMessageServiceRemote.newMessage(teamId));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	

	@ResponseBody
	@RequestMapping(value = "/add", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper add(RelayMessage relayMessage) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(relayMessage.getToken());
			RelayMember relayMember = relayMemberServiceRemote.getByTeamIdAndUserId(relayMessage.getTeamId(),user.getId());
			if(relayMember==null){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("您未加入本队不能发布留言！");
				return webResultInfoWrapper;
			}
			relayMessage.setUserId(user.getId());
			relayMessage.setCreateTime(System.currentTimeMillis());
		relayMessageServiceRemote.save(relayMessage);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	
	
}

