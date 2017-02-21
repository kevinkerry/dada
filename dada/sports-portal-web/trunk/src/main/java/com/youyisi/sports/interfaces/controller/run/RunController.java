package com.youyisi.sports.interfaces.controller.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.run.RunServiceRemote;
import com.youyisi.app.soa.remote.user.UserServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.Constants;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.run.Run;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.interfaces.helper.RedisClient;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DES3Helper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Controller
@RequestMapping("/run")
public class RunController extends BaseController{

	@Autowired
	private RunServiceRemote runServiceRemote;
	@Autowired
	private UserServiceRemote userServiceRemote;

	private Logger log = LoggerFactory.getLogger(RunController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Integer currentPage, Integer pageSize) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		Page<Run> page = new Page<Run>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		webResultInfoWrapper.addResult("page", runServiceRemote.queryPage(page));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper detail(Run r) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		webResultInfoWrapper.addResult("run", runServiceRemote.getDetail(r.getId()));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper delete(@PathVariable("id") Long id) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		Run entity = new Run();
		entity.setId(id);
		webResultInfoWrapper.addResult("run", runServiceRemote.delete(entity));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@Deprecated
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper add(Run run,String distances) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			run.setCreateTime(System.currentTimeMillis());
			User user = getUserByToken(run.getToken());
			run.setDate(DateUtil.currentDateForDay());
			run.setUserId(user.getId());
		webResultInfoWrapper.addResult("run", runServiceRemote.save(run));
		webResultInfoWrapper.setState(SUCCEED);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/adds", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper adds(Run run,String distances) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			run.setCreateTime(System.currentTimeMillis());
			if(run.getType().intValue()==1){
				try {
					distances = DES3Helper.decode(distances);
					run.setDistance(Double.parseDouble(distances));
				} catch (Exception e1) {
					webResultInfoWrapper.setState(ERROR);
					webResultInfoWrapper.setMessage("非法请求！请更新到最新版本");
					return webResultInfoWrapper;
				}
			}else{
				run.setDistance(null);
			}
			User user = getUserByToken(run.getToken());
			run.setDate(DateUtil.currentDateForDay());
			run.setUserId(user.getId());
		webResultInfoWrapper.addResult("run", runServiceRemote.save(run));
		webResultInfoWrapper.setState(SUCCEED);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	@RequestMapping(value = "/correction", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public WebResultInfoWrapper correction(Run run) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(run.getToken());
			Run r = runServiceRemote.get(run.getId());
			r.setRealDistance(run.getRealDistance());
		webResultInfoWrapper.addResult("run", runServiceRemote.correction(r));
		webResultInfoWrapper.addResult("indoorRunning", updateUserIndoorRunning(r,user,run.getToken()));
		webResultInfoWrapper.setState(SUCCEED);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	

	private Double updateUserIndoorRunning(Run r, User user,String token) throws SoaException {
		Double indoorRunning = null;
		if(user.getHeight()>0&&r.getStep()>0){
			double _indoorRunning = ArithHelper.div(r.getRealDistance(), r.getStep(),6);
			if(user.getSex()==1){
				if(_indoorRunning>0.0007&&_indoorRunning<0.0012){
					user.setIndoorRunning(_indoorRunning);
					userServiceRemote.update(user);
					RedisClient.set(Constants.USER_KEY + token, user);
					indoorRunning = _indoorRunning;
				}
			}else{
				if(_indoorRunning>0.0006&&_indoorRunning<0.0011){
					user.setIndoorRunning(_indoorRunning);
					userServiceRemote.update(user);
					RedisClient.set(Constants.USER_KEY + token, user);
					indoorRunning = _indoorRunning;
				}
			}
		}
		return indoorRunning;
	}

	@ResponseBody
	@Deprecated
	@RequestMapping(value = "/update", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper update(Run run,String distances) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			Run r = runServiceRemote.get(run.getId());
			r.setAvspeed(run.getAvspeed());
			r.setDistance(run.getDistance());
			r.setStep(run.getStep());
			r.setTotalTime(run.getTotalTime());
			r.setMaxspeed(run.getMaxspeed());
			r.setMinspeed(run.getMinspeed());
			
		    runServiceRemote.update(r);
		    webResultInfoWrapper.setState(SUCCEED);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updates", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper updates(Run run,String distances) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			distances = DES3Helper.decode(distances);
			run.setDistance(Double.parseDouble(distances));
		} catch (Exception e1) {
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage("非法请求！请更新到最新版本");
			return webResultInfoWrapper;
		}
		try {
			Run r = runServiceRemote.get(run.getId());
			r.setAvspeed(run.getAvspeed());
			r.setDistance(run.getDistance());
			r.setStep(run.getStep());
			r.setTotalTime(run.getTotalTime());
			r.setMaxspeed(run.getMaxspeed());
			r.setMinspeed(run.getMinspeed());
			if(r.getAvspeed()==null||r.getAvspeed()<25){
				r.setStatus(0);
				 webResultInfoWrapper.addResult("status", 0);
			}else{
				r.setStatus(-1);
				webResultInfoWrapper.addResult("status", -1);
			}
		    runServiceRemote.update(r);
		    webResultInfoWrapper.setState(SUCCEED);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
}

