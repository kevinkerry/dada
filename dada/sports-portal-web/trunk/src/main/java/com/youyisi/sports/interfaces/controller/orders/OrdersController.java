package com.youyisi.sports.interfaces.controller.orders;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.youyisi.app.soa.remote.coupon.CouponServiceRemote;
import com.youyisi.app.soa.remote.goldbean.UserGoldBeanServiceRemote;
import com.youyisi.app.soa.remote.orders.OrdersServiceRemote;
import com.youyisi.app.soa.remote.relay.RelayMemberServiceRemote;
import com.youyisi.app.soa.remote.snatch.SnatchFeeServiceRemote;
import com.youyisi.app.soa.remote.snatch.UserSnatchServiceRemote;
import com.youyisi.app.soa.remote.thigh.HugThighServiceRemote;
import com.youyisi.app.soa.remote.thigh.ThighServiceRemote;
import com.youyisi.app.soa.remote.wallet.WalletServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.Constants;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.activity.RelayRaceActivity;
import com.youyisi.sports.domain.coupon.Coupon;
import com.youyisi.sports.domain.goldbean.UserGoldBean;
import com.youyisi.sports.domain.orders.Orders;
import com.youyisi.sports.domain.relay.RelayMember;
import com.youyisi.sports.domain.snatch.SnatchFee;
import com.youyisi.sports.domain.snatch.UserSnatch;
import com.youyisi.sports.domain.thigh.HugThigh;
import com.youyisi.sports.domain.thigh.Thigh;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.wallet.Wallet;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.interfaces.helper.RedisClient;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController{

	@Autowired
	private OrdersServiceRemote ordersServiceRemote;
	@Autowired
	private WalletServiceRemote walletServiceRemote;
	@Autowired
	private HugThighServiceRemote hugThighServiceRemote;
	@Autowired
	private ThighServiceRemote thighServiceRemote;
	@Autowired
	private CouponServiceRemote couponServiceRemote;
	@Autowired
	private RelayMemberServiceRemote relayMemberServiceRemote;
	@Autowired
	private RelayRaceActivityServiceRemote relayRaceActivityServiceRemote;
	@Autowired
	private UserSnatchServiceRemote userSnatchServiceRemote;
	@Autowired
	private UserGoldBeanServiceRemote userGoldBeanServiceRemote;
	@Autowired
	private SnatchFeeServiceRemote snatchFeeServiceRemote;
	
	private Logger log = LoggerFactory.getLogger(OrdersController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Integer currentPage, Integer pageSize) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		Page<Orders> page = new Page<Orders>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		webResultInfoWrapper.addResult("page", ordersServiceRemote.queryPage(page));
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
		webResultInfoWrapper.addResult("orders", ordersServiceRemote.get(id));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper update(Orders orders) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		ordersServiceRemote.update(orders);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper save(Orders orders) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			if(orders.getType().intValue()==1&&orders.getPayAmount()>1000.0){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("单次充值不能大于1000");
				return webResultInfoWrapper;
			}
			if(orders.getType().intValue()==1&&orders.getPayAmount()<100.0){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("最低充值100元");
				return webResultInfoWrapper;
			}
			User user = getUserByToken(orders.getToken());
			if(orders.getType().intValue()==1){
				Wallet wallet = walletServiceRemote.getByUserId(user.getId());
				if(wallet.getPrincipal().doubleValue()>10000.0){
					webResultInfoWrapper.setState(ERROR);
					webResultInfoWrapper.setMessage("您已经达到充值上限了！");
					return webResultInfoWrapper;
				}
			}
			
			if (orders.getType().intValue() == 2) {
				HugThigh hugThigh = hugThighServiceRemote.get(orders.getProductId());
				Thigh thigh = thighServiceRemote.get(hugThigh.getThighId());
				Coupon coupon = couponServiceRemote.getByActivityIdAndType(hugThigh.getActivityId(), thigh.getType());
				orders.setPayAmount(coupon.getCommission());
				orders.setOrderAmount(coupon.getCommission());
			}
			if (orders.getType().intValue() == 3) {
				RelayMember relayMember = relayMemberServiceRemote.get(orders.getProductId());
				RelayRaceActivity relayRaceActivity = relayRaceActivityServiceRemote.getByActivityId(relayMember.getActivityId());
				if(relayMember.getLevel().intValue()==1&&relayMember.getParentId().intValue()==0){
					orders.setPayAmount(relayRaceActivity.getFirstFee());
					orders.setOrderAmount(relayRaceActivity.getFirstFee());
				}else{
					orders.setPayAmount(relayRaceActivity.getOtherFee());
					orders.setOrderAmount(relayRaceActivity.getOtherFee());
				}
			}
			
			if (orders.getType().intValue() == 4) {
				UserSnatch userSnatch = userSnatchServiceRemote.get(orders.getProductId());
				SnatchFee snatchFee = snatchFeeServiceRemote.get(userSnatch.getSnatchFeeId());
				orders.setPayAmount(snatchFee.getMoney());
				orders.setOrderAmount(snatchFee.getMoney());
			}
			
			if (orders.getType().intValue() == 5) {
				UserGoldBean userGoldBean = userGoldBeanServiceRemote.get(orders.getProductId());
				orders.setPayAmount(userGoldBean.getGoldBeanRecharge().getMoney());
				orders.setOrderAmount(userGoldBean.getGoldBeanRecharge().getMoney());
			}
			
			orders.setPayStatus(0);
			orders.setOrderStatus(0);
			orders.setCreateTime(System.currentTimeMillis());
			orders.setUpdateTime(System.currentTimeMillis());
			orders.setUserId(user.getId());
			orders.setOrderNumber(getOrderNumber());
			webResultInfoWrapper.addResult("order",ordersServiceRemote.save(orders));
			webResultInfoWrapper.setState(SUCCEED);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	private String getOrderNumber() {
		Long id = RedisClient.increment(Constants.ORDER_KEY, 1l);
		return "DD-" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + new DecimalFormat("00000000").format(id);

	}
}

