package com.youyisi.sports.interfaces.controller.thigh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.annual.AnnualYieldServiceRemote;
import com.youyisi.app.soa.remote.coupon.CouponServiceRemote;
import com.youyisi.app.soa.remote.run.RunServiceRemote;
import com.youyisi.app.soa.remote.thigh.HugThighActivityServiceRemote;
import com.youyisi.app.soa.remote.thigh.HugThighServiceRemote;
import com.youyisi.app.soa.remote.thigh.ThighServiceRemote;
import com.youyisi.app.soa.remote.wallet.WalletDetailServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.annual.AnnualYield;
import com.youyisi.sports.domain.coupon.Coupon;
import com.youyisi.sports.domain.run.Run;
import com.youyisi.sports.domain.thigh.HugThighActivity;
import com.youyisi.sports.domain.thigh.MyThigh;
import com.youyisi.sports.domain.thigh.Thigh;
import com.youyisi.sports.domain.thigh.ThighMoreInfo;
import com.youyisi.sports.domain.thigh.ThighRank;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-07-11
 */
@Controller
@RequestMapping("/thigh")
public class ThighController extends BaseController{

	@Autowired
	private ThighServiceRemote thighServiceRemote;
	@Autowired
	private AnnualYieldServiceRemote annualYieldServiceRemote;
	@Autowired
	private HugThighActivityServiceRemote hugThighActivityServiceRemote;
	@Autowired
	private RunServiceRemote runServiceRemote;
	@Autowired
	private CouponServiceRemote couponServiceRemote;
	@Autowired
	private WalletDetailServiceRemote walletDetailServiceRemote;
	
	@Autowired
	private HugThighServiceRemote hugThighServiceRemote;

	private Logger log = LoggerFactory.getLogger(ThighController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Long activityId,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			List<ThighMoreInfo> list = thighServiceRemote.getList(activityId);
			List<ThighMoreInfo> result = new ArrayList<ThighMoreInfo>();
			for(ThighMoreInfo t:list){
				t.setHugThighCount(hugThighServiceRemote.getHugThighCount(t.getId(),System.currentTimeMillis()-10*60*1000l));
				t.setIshug(hugThighServiceRemote.getByUserIdAndThighId(user.getId(), t.getId()));
				result.add(t);
			}
		webResultInfoWrapper.addResult("list", list);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/rank", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper rank(Long activityId) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			Page<ThighRank> page = new Page<ThighRank>();
			page.addParam("status", 1);
			page.addParam("activityId", activityId);
			List<ThighRank> list = thighServiceRemote.getListThighRank(page);
			Collections.sort(list);
			if((!list.isEmpty())&&list.size()>10){
				webResultInfoWrapper.addResult("list", list.subList(0, 10));
			}else{
				
				webResultInfoWrapper.addResult("list", list);
			}
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/mylist", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper mylist(Integer currentPage, Integer pageSize,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		Page<MyThigh> page = new Page<MyThigh>();
		User user = getUserByToken(token);
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		page.addParam("userId", user.getId());
		page.addParam("status", 1);
		webResultInfoWrapper.addResult("page", thighServiceRemote.queryPageMyThigh(page));
		webResultInfoWrapper.addResult("sum", walletDetailServiceRemote.getSumByType(5,user.getId()));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper add(Long activityId,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		User user = getUserByToken(token);
		try {
			Integer count = hugThighServiceRemote.getCount(user.getId(),activityId);
			if(count!=null&&count.intValue()>0){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("你已经抱过大腿了，不能当大腿");
				return webResultInfoWrapper;
			}
			
			HugThighActivity hugThighActivity = hugThighActivityServiceRemote.getByActivityId(activityId);
			Long thighcount = thighServiceRemote.thighCount(activityId,System.currentTimeMillis()-5*60*1000l);
			if(thighcount!=null&&thighcount.intValue()>=hugThighActivity.getThighLimit().intValue()){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("今天的大腿名额已被抢光啦！");
				return webResultInfoWrapper;
			}
			
			Thigh thigh = thighServiceRemote.getByActivityIdAndUserId(activityId,user.getId(),System.currentTimeMillis()-5*60*1000l);
			AnnualYield annualYield = annualYieldServiceRemote.getByUserIdDate(user.getId(),DateUtil.getDateForDay(-1));
			if(annualYield!=null){
				webResultInfoWrapper.addResult("annualYield", annualYield.getAnnualYield());
			}else{
				webResultInfoWrapper.addResult("annualYield",0.0);
			}
			Run r = runServiceRemote.getMaxRun(user.getId(),DateUtil.getDateForDay(-1),hugThighActivity.getAvspeed(),hugThighActivity.getMaxspeed());
			List<Coupon> list = couponServiceRemote.getListByActivityId(activityId);
			Integer type = -1;
			if(r!=null&&annualYield!=null){
				webResultInfoWrapper.addResult("run",r.getDistance());
				Coupon coupon = null;
				for(Coupon c:list){
					if(c.getDistance()<=r.getDistance()&&c.getAnnualYield()<=annualYield.getAnnualYield()){
						coupon = c;
					}
				}
				if(coupon!=null){
					type = coupon.getType();
				}
			}
			if(type!=-1){
				if(thigh==null){
					thigh = new Thigh();
					thigh.setActivityId(activityId);
					thigh.setCreateTime(System.currentTimeMillis());
					thigh.setDate(DateUtil.currentDateForDay());
					thigh.setType(type);
					thigh.setStatus(0);
					thigh.setUserId(user.getId());
					thigh = thighServiceRemote.save(thigh);
					webResultInfoWrapper.addResult("news", true);
				}else{
					webResultInfoWrapper.addResult("news", false);
				}
				webResultInfoWrapper.addResult("thigh", thigh);
				 webResultInfoWrapper.addResult("currentTime", System.currentTimeMillis());
			}
				webResultInfoWrapper.addResult("type", type);
		
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/canbethigh", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper canbethigh(Long activityId,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		User user = getUserByToken(token);
		try {
			Integer count = hugThighServiceRemote.getCount(user.getId(),activityId);
			if(count!=null&&count.intValue()>0){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("你已经抱过大腿了，不能当大腿");
				webResultInfoWrapper.addResult("canbethigh", false);
				return webResultInfoWrapper;
			}
			
			HugThighActivity hugThighActivity = hugThighActivityServiceRemote.getByActivityId(activityId);
			Long thighcount = thighServiceRemote.thighCount(activityId,System.currentTimeMillis()-5*60*1000l);
			if(thighcount!=null&&thighcount.intValue()>=hugThighActivity.getThighLimit().intValue()){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("今天的大腿名额已被抢光啦！");
				webResultInfoWrapper.addResult("canbethigh", false);
				return webResultInfoWrapper;
			}
			webResultInfoWrapper.addResult("canbethigh", true);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/sure", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper sure(Long id,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			Thigh thigh = thighServiceRemote.get(id);
			if(System.currentTimeMillis()-thigh.getCreateTime()<5*60*1000){
				thigh.setStatus(1);
				thighServiceRemote.update(thigh);
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
	@RequestMapping(value = "/cancel", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper cancel(Long id,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			Thigh thigh = thighServiceRemote.get(id);
			if(thigh.getStatus().intValue()==1){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("不能取消了哦");
				return webResultInfoWrapper;
			}
			thigh.setStatus(-1);
			thighServiceRemote.update(thigh);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

