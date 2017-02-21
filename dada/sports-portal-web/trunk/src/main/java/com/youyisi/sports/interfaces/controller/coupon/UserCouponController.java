package com.youyisi.sports.interfaces.controller.coupon;

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
import com.youyisi.app.soa.remote.coupon.UserCouponServiceRemote;
import com.youyisi.app.soa.remote.integralwall.DoumIntegralWallServiceRemote;
import com.youyisi.app.soa.remote.integralwall.IntegralWallServiceRemote;
import com.youyisi.app.soa.remote.wallet.WalletDetailServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.coupon.UserCoupon;
import com.youyisi.sports.domain.coupon.UserCouponWithCoupon;
import com.youyisi.sports.domain.coupon.UserCouponWithCouponMore;
import com.youyisi.sports.domain.integralwall.DoumIntegralWall;
import com.youyisi.sports.domain.integralwall.IntegralWall;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.interfaces.helper.pay.tenpay.util.MD5;
import com.youyisi.sports.utils.DateUtil;
import com.youyisi.sports.utils.StrUtil;

/**
 * @author shuye
 * @time 2016-07-11
 */
@Controller
@RequestMapping("/usercoupon")
public class UserCouponController extends BaseController{

	@Autowired
	private UserCouponServiceRemote userCouponServiceRemote;
	@Autowired
	private WalletDetailServiceRemote walletDetailServiceRemote;
	@Autowired
	private IntegralWallServiceRemote integralWallServiceRemote;
	@Autowired
	private DoumIntegralWallServiceRemote doumIntegralWallServiceRemote;
	
	private Logger log = LoggerFactory.getLogger(UserCouponController.class);

	@ResponseBody
	@RequestMapping(value = "/use", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper use( Long id) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			UserCoupon userCoupon = userCouponServiceRemote.get(id);
			if(userCoupon.getStatus().intValue()!=0){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("此券已使用过了");
			}else if(System.currentTimeMillis()>userCoupon.getExpiryTime()){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("此券已过期了");
			}else{
				UserCoupon uc = userCouponServiceRemote.getUsing(userCoupon.getUserId(),DateUtil.currentDateForDay());
				if(uc!=null){
					webResultInfoWrapper.setState(ERROR);
					webResultInfoWrapper.setMessage("今天已经使用过券了");
				}else{
					webResultInfoWrapper.addResult("annualYield",userCouponServiceRemote.use(userCoupon));
				}
			}
		//webResultInfoWrapper.addResult("userCoupon", userCoupon);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/using", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper using(String token) {
		User user = getUserByToken(token);
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			UserCoupon userCoupon = userCouponServiceRemote.getUsing(user.getId(),DateUtil.currentDateForDay());
			webResultInfoWrapper.addResult("using", userCoupon);
		
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Integer currentPage, Integer pageSize,Integer status,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
		Page<UserCouponWithCoupon> page = new Page<UserCouponWithCoupon>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		page.addParam("status", status);
		page.addParam("userId", user.getId());
		page.addParam("currentTime",System.currentTimeMillis());
		webResultInfoWrapper.addResult("page", userCouponServiceRemote.queryPageUserCouponWithCoupon(page));
		webResultInfoWrapper.addResult("currentTime", System.currentTimeMillis());
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getCount", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper getCount(String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("status", 0);
			map.put("userId", user.getId());
			map.put("currentTime",System.currentTimeMillis());
		    webResultInfoWrapper.addResult("count", userCouponServiceRemote.getCount(map));
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
			User user = getUserByToken(token);
		Page<UserCouponWithCoupon> page = new Page<UserCouponWithCoupon>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		page.addParam("userId", user.getId());
		page.addParam("category", 0);
		webResultInfoWrapper.addResult("page", userCouponServiceRemote.queryPageForMyList(page));
		webResultInfoWrapper.addResult("sum", walletDetailServiceRemote.getSumByType(7,user.getId()));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper add(IntegralWall integralWall,String user) {
		integralWall.setUserId(Long.parseLong(user));
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			if(integralWall.getSg().equals(getMd5(integralWall))){
				integralWallServiceRemote.note(integralWall);
			}
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/domobadd", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper domobadd(DoumIntegralWall integralWall,String user,String action_name) {
		integralWall.setUserId(Long.parseLong(user));
		integralWall.setActionName(action_name);
		System.out.println("----------------"+gson.toJson(integralWall));
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			String md5 = getDomobMd5(integralWall);
			System.out.println("recive----------------"+integralWall.getSign());
			System.out.println("ours----------------"+md5);
			if(integralWall.getSign().equals(md5)){
				System.out.println("----------------2");
				doumIntegralWallServiceRemote.domobNote(integralWall);
			}
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	/*public static void main(String[] args) {
		DoumIntegralWall d = new DoumIntegralWall();
		d.setAction(4);
		d.setAd("菜鸟裹裹");
		d.setAdid(1023493);
		d.setChannel(0);
		d.setDevice("864895020173942");
		d.setOrderid("525650334");
		d.setPkg("com.cainiao.wireless");
		d.setPoint(84);
		d.setPrice(0.04);
		d.setPubid("96ZJ3guQzfjcXwTPN0");
		d.setSign("2a7674ad71fd6ba3b126a9cbc26c50d8");
		d.setTs(1471838398);
		d.setUserId(886l);
		d.setActionName("签到-4");
		System.out.println(getDomobMd5(d));
	}*/
	
	/**{"userId":886,"orderid":"525650334","pubid":"96ZJ3guQzfjcXwTPN0","ad":"菜鸟裹裹","adid":1023493,"device":"864895020173942","channel":0,"price":0.04,"point":84,"ts":1471838398,"sign":"2a7674ad71fd6ba3b126a9cbc26c50d8","pkg":"com.cainiao.wireless","action":4}
	recive----------------2a7674ad71fd6ba3b126a9cbc26c50d8
	ours------------------066db2c0cfcdeb4a6f06574faee602bf
	
**/
	private static String getDomobMd5(DoumIntegralWall integralWall) {
		String key = "fd2665e8";
	return MD5.getMessageDigest(("action="+integralWall.getAction()+"action_name="+integralWall.getActionName()+"ad="+integralWall.getAd()+"adid="+integralWall.getAdid()+"channel="+integralWall.getChannel()+"device="+integralWall.getDevice()+"orderid="+integralWall.getOrderid()+"pkg="+integralWall.getPkg()+"point="+integralWall.getPoint()+"price="+integralWall.getPrice()+"pubid="+integralWall.getPubid()+"ts="+integralWall.getTs()+"user="+integralWall.getUserId()+key).getBytes());
}

	private String getMd5(IntegralWall integralWall) {
		String key = "dadasportsiloveyou1234";
		return StrUtil.getMD532Str(integralWall.getUserId()+","+integralWall.getSn()+","+integralWall.getPk()+","+integralWall.getScore()+","+key);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/queryPageForInviteFriend", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper queryPageForInviteFriend(Integer currentPage, Integer pageSize,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		Page<UserCouponWithCouponMore> page = new Page<UserCouponWithCouponMore>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		page.addParam("category", 1);
		webResultInfoWrapper.addResult("page", userCouponServiceRemote.queryPageForInviteFriend(page));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
}

