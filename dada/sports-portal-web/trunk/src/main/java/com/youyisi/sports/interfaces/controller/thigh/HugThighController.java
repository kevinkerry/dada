package com.youyisi.sports.interfaces.controller.thigh;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.coupon.CouponServiceRemote;
import com.youyisi.app.soa.remote.thigh.HugThighActivityServiceRemote;
import com.youyisi.app.soa.remote.thigh.HugThighServiceRemote;
import com.youyisi.app.soa.remote.thigh.ThighServiceRemote;
import com.youyisi.app.soa.remote.wallet.WalletDetailServiceRemote;
import com.youyisi.app.soa.remote.wallet.WalletServiceRemote;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.coupon.Coupon;
import com.youyisi.sports.domain.thigh.HugThigh;
import com.youyisi.sports.domain.thigh.HugThighActivity;
import com.youyisi.sports.domain.thigh.HugThighWithUser;
import com.youyisi.sports.domain.thigh.Thigh;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-07-11
 */
@Controller
@RequestMapping("/hugthigh")
public class HugThighController extends BaseController{

	@Autowired
	private HugThighServiceRemote hugThighServiceRemote;
	@Autowired
	private HugThighActivityServiceRemote hugThighActivityServiceRemote;
	@Autowired
	private ThighServiceRemote thighServiceRemote;
	@Autowired
	private WalletDetailServiceRemote walletDetailServiceRemote;
	@Autowired
	private CouponServiceRemote couponServiceRemote;
	@Autowired
	private WalletServiceRemote walletServiceRemote;
	private Logger log = LoggerFactory.getLogger(HugThighController.class);

	@ResponseBody
	@RequestMapping(value = "/add", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper add(String token,Long thighId) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		User user = getUserByToken(token);
		try {
			Thigh thigh = thighServiceRemote.get(thighId);
			HugThighActivity hugThighActivity = hugThighActivityServiceRemote.getByActivityId(thigh.getActivityId());
		    Integer count = hugThighServiceRemote.getHugThighCount(thighId,System.currentTimeMillis()-10*60*1000l);
		    Coupon coupon = couponServiceRemote.getByActivityIdAndType(thigh.getActivityId(),thigh.getType());
		     if(hugThighActivity.getHugThighLimit()>count){
		    	 HugThigh hugThigh = hugThighServiceRemote.getByUserAndThighId(user.getId(),thighId,System.currentTimeMillis()-10*60*1000l);
		    	 if(hugThigh==null){
		    		 hugThigh = new HugThigh();
		    		 hugThigh.setActivityId(thigh.getActivityId());
		    		 hugThigh.setPayStatus(0);
		    		 hugThigh.setStatus(0);
		    		 hugThigh.setThighId(thighId);
		    		 hugThigh.setUserId(user.getId());
		    		 hugThigh.setCreateTime(System.currentTimeMillis());
		    		 hugThigh = hugThighServiceRemote.save(hugThigh);
		    		 webResultInfoWrapper.addResult("news", true);
		    	 }else{
		    		 webResultInfoWrapper.addResult("news", false);
		    	 }
		    	 webResultInfoWrapper.addResult("hugThigh", hugThigh);
		    	 webResultInfoWrapper.addResult("currentTime", System.currentTimeMillis());
		    	 webResultInfoWrapper.addResult("expiryTime",getExpiryTime(coupon));
		    	 webResultInfoWrapper.addResult("wallet", walletServiceRemote.getByUserId(user.getId()));
		     }else{
		    	webResultInfoWrapper.setState(ERROR);
		 		webResultInfoWrapper.setMessage("该大腿的福利已被抢光啦！");
		     }
		     
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/canhug", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper canhug(String token,Long thighId) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			Thigh thigh = thighServiceRemote.get(thighId);
			HugThighActivity hugThighActivity = hugThighActivityServiceRemote.getByActivityId(thigh.getActivityId());
		    Integer count = hugThighServiceRemote.getHugThighCount(thighId,System.currentTimeMillis()-10*60*1000l);
		     if(hugThighActivity.getHugThighLimit()>count){
		    	 webResultInfoWrapper.addResult("canhug", true);
		     }else{
		    	 webResultInfoWrapper.addResult("canhug", false);
		     }
		     webResultInfoWrapper.addResult("count", count);
		     
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/cancel", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper cancel(String token,Long id) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		    	 HugThigh hugThigh = hugThighServiceRemote.get(id);
		    	 if(hugThigh.getPayStatus().intValue()==1){
		    		 webResultInfoWrapper.setState(ERROR);
		    	     webResultInfoWrapper.setMessage("已经支付的不能取消了哦");
		    		return webResultInfoWrapper;
		    	 }
		    	 hugThigh.setStatus(-1);
		    	 hugThighServiceRemote.update(hugThigh);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	private Long getExpiryTime(Coupon coupon) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, coupon.getExpiryDay());
		return calendar.getTime().getTime();
	}

	@ResponseBody
	@RequestMapping(value = "/pay", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper pay(String token,Long id) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		    	 HugThigh hugThigh = hugThighServiceRemote.get(id);
		    	 User user = getUserByToken(token);
		    	 if(hugThigh.getUserId().longValue()!=user.getId().longValue()){
		    		 webResultInfoWrapper.setState(ERROR);
		    		 webResultInfoWrapper.setMessage("数据错误!");
		    		 return webResultInfoWrapper; 
		    	 }
		    	 if(hugThigh.getStatus()!=null&&hugThigh.getStatus().intValue()==1){
		    		 webResultInfoWrapper.setState(ERROR);
		    		 webResultInfoWrapper.setMessage("已经支付过了!");
		    		 return webResultInfoWrapper;
		    	 }
		    	 if(System.currentTimeMillis()-hugThigh.getCreateTime()<10*60*1000l){
		    		 int result = hugThighServiceRemote.pay(hugThigh);
		    		 if(result==-1){
		    			 webResultInfoWrapper.setState(ERROR);
			    		 webResultInfoWrapper.setMessage("余额不足!");
		    		 }
		    	 }else{
		    		 webResultInfoWrapper.setState(ERROR);
		    		 webResultInfoWrapper.setMessage("申请已经过期!");
		    	 }
		     
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(String token,Long thighId) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
		    List<HugThighWithUser> hugThighs = hugThighServiceRemote.getListByThighId(thighId);
		    webResultInfoWrapper.addResult("hugThighs", hugThighs);
		    webResultInfoWrapper.addResult("hugThighMoney", walletDetailServiceRemote.getSumThighByDate(DateUtil.currentDateForDay(),user.getId()));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
}

