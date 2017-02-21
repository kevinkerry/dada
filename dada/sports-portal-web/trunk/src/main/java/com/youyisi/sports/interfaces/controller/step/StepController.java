package com.youyisi.sports.interfaces.controller.step;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.step.StepServiceRemote;
import com.youyisi.app.soa.remote.user.UserServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.step.Step;
import com.youyisi.sports.domain.step.StepWithUser;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.user.UserMoreInfo;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.utils.DES3Helper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Controller
@RequestMapping("/step")
public class StepController extends BaseController {

	@Autowired
	private StepServiceRemote stepServiceRemote;
	@Autowired
	private UserServiceRemote userServiceRemote;

	private Logger log = LoggerFactory.getLogger(StepController.class);

	@ResponseBody
	@RequestMapping(value = "/ranklist", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Integer currentPage, Integer pageSize, String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			Page<StepWithUser> page = new Page<StepWithUser>();
			if (null != currentPage) {
				page.setCurrentPage(currentPage);
			}
			if (null != pageSize) {
				page.setPageSize(pageSize);
			} else {
				page.setPageSize(20);
			}
			webResultInfoWrapper.addResult("page", stepServiceRemote.queryPageRanklist(page));
			Step s = stepServiceRemote.getByUserIdAndDate(user.getId());
			webResultInfoWrapper.addResult("myStep", s);
			Integer step = 0;
			Long id = 0l;
			if(s!=null&&s.getStep()!=null){
				step = s.getStep();
				id = s.getId();
			}
			Long myRanking = stepServiceRemote.getMyRanking(step,id);
			if(myRanking==null){
				myRanking=1l;
			}else{
				myRanking = myRanking+1l;
			}
			webResultInfoWrapper.addResult("myRanking",myRanking);
			webResultInfoWrapper.setState(SUCCEED);
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
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
			webResultInfoWrapper.addResult("step", stepServiceRemote.get(id));
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@Deprecated
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper update(Step step,Long currentDate) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			if(currentDate/1000==DateUtil.currentDateForDay()){
				User user = getUserByToken(step.getToken());
				user.setToken(step.getToken());
				step.setUserId(user.getId());
				UserMoreInfo userMoreInfo = stepServiceRemote.updateStep(step, user);
				userMoreInfo = setPassword(userMoreInfo);
				userMoreInfo.setToken(step.getToken());
				webResultInfoWrapper.addResult("user",userMoreInfo);
				if(userMoreInfo.getExperienceAccount()!=null){
					webResultInfoWrapper.addResult("isExpiry",dateDiff(new Date(userMoreInfo.getExperienceAccount().getExpiryTime()),new Date()));
				}
				if(userMoreInfo.getRegisterTime()!=null){
					webResultInfoWrapper.addResult("registerDate",dateDiff(new Date(userMoreInfo.getRegisterTime()),new Date()));
				}
				webResultInfoWrapper.setState(SUCCEED);
				return webResultInfoWrapper;
			}
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage("无效数据");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/updates", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper updates(Step step,Long currentDate,String steps) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			steps = DES3Helper.decode(steps);
			step.setStep(Integer.parseInt(steps));
		} catch (Exception e1) {
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage("非法请求！请更新到最新版本");
			return webResultInfoWrapper;
		}
		try {
			if(currentDate/1000==DateUtil.currentDateForDay()){
				User user = getUserByToken(step.getToken());
				user.setToken(step.getToken());
				step.setUserId(user.getId());
				UserMoreInfo userMoreInfo = stepServiceRemote.updateStep(step, user);
				userMoreInfo = setPassword(userMoreInfo);
				userMoreInfo.setToken(step.getToken());
				webResultInfoWrapper.addResult("user",userMoreInfo);
				if(userMoreInfo.getExperienceAccount()!=null){
					webResultInfoWrapper.addResult("isExpiry",dateDiff(new Date(userMoreInfo.getExperienceAccount().getExpiryTime()),new Date()));
				}
				if(userMoreInfo.getRegisterTime()!=null){
					webResultInfoWrapper.addResult("registerDate",dateDiff(new Date(userMoreInfo.getRegisterTime()),new Date()));
				}
				webResultInfoWrapper.setState(SUCCEED);
				return webResultInfoWrapper;
			}
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage("无效数据");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	private UserMoreInfo setPassword(UserMoreInfo u) {
		if(StringUtils.isBlank(u.getPassword())){
			u.setPassword("no");
		}else{
			u.setPassword("yes");
		}
		if(StringUtils.isBlank(u.getPayPassword())){
			u.setPayPassword("no");
		}else{
			u.setPayPassword("yes");
		}
		return u;
	}
	public static int dateDiff(Date fromDate, Date toDate) throws Exception{
	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	Date from = df.parse(df.format(fromDate));
	Date to = df.parse(df.format(toDate));
	int days = (int) Math.abs((to.getTime() - from.getTime())
	 / (24 * 60 * 60 * 1000)) ;
	 return days;
	}
}
