package com.youyisi.sports.interfaces.controller.withdraw;

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
import com.youyisi.app.soa.remote.wallet.WalletServiceRemote;
import com.youyisi.app.soa.remote.withdraw.WithdrawServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.Constants;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.sms.SmsMessage;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.wallet.Wallet;
import com.youyisi.sports.domain.withdraw.Withdraw;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.interfaces.helper.RedisClient;
import com.youyisi.sports.utils.DateUtil;
import com.youyisi.sports.utils.SmsMessageHelper;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Controller
@RequestMapping("/withdraw")
public class WithdrawController extends BaseController{

	@Autowired
	private WithdrawServiceRemote withdrawServiceRemote;
	
	@Autowired
	private WalletServiceRemote walletServiceRemote;
	
	private Logger log = LoggerFactory.getLogger(WithdrawController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Integer currentPage, Integer pageSize) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		Page<Withdraw> page = new Page<Withdraw>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		webResultInfoWrapper.addResult("page", withdrawServiceRemote.queryPage(page));
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
		webResultInfoWrapper.addResult("withdraw", withdrawServiceRemote.get(id));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper update(Withdraw withdraw) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		withdrawServiceRemote.update(withdraw);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper add(Withdraw withdraw) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(withdraw.getToken());
			Wallet wallet = walletServiceRemote.getByUserId(user.getId());
			if(wallet.getTotalAsset()<withdraw.getMoney()){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("余额不足");
				return webResultInfoWrapper;
			}
			if(withdraw.getMoney()>=500.0){
				sendSmsMessage(user,withdraw);
			}
			if(withdraw.getMoney()<1.0){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("提现最低1元哦");
				return webResultInfoWrapper;
			}
			withdraw.setCreateTime(System.currentTimeMillis());
			withdraw.setUpdateTime(System.currentTimeMillis());
			withdraw.setStatus(0);
			withdraw.setUserId(user.getId());
			withdraw.setWithdrawNumber(getNumber());
			Withdraw w = withdrawServiceRemote.add(withdraw,wallet);
			if(w==null){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("提现异常！");
				return webResultInfoWrapper;
			}
			webResultInfoWrapper.addResult("withdraw", w);
			webResultInfoWrapper.setState(SUCCEED);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	private String getNumber() {
		Long id = RedisClient.increment(Constants.WITHDRAW_KEY, 1l);
		return "DD-" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + new DecimalFormat("00000000").format(id);

	}
	
	private void sendSmsMessage(User user,Withdraw withdraw) {
		if(user.getBigDealsRemind()==null||user.getBigDealsRemind()!=-1){
			SmsMessage message = new SmsMessage();
			message.setTelephone(user.getMobile());
			message.setBody("您的哒哒账号于"+DateUtil.getTimeStr()+" 进行了一次提现申请"+withdraw.getMoney()+"元，将在两个工作日内完成，如不是您本人操作或有疑问请拨打客服电话020-62326560，回复TD退订");
			SmsMessageHelper.send(message);
		}
	}

}

