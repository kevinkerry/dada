package com.youyisi.sports.interfaces.controller.wallet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.wallet.WalletDetailServiceRemote;
import com.youyisi.app.soa.remote.withdraw.WithdrawServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.wallet.WalletDetail;
import com.youyisi.sports.domain.wallet.WalletDetailWithUser;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Controller
@RequestMapping("/walletdetail")
public class WalletDetailController extends BaseController{

	@Autowired
	private WalletDetailServiceRemote walletDetailServiceRemote;
	@Autowired
	private WithdrawServiceRemote withdrawServiceRemote;

	private Logger log = LoggerFactory.getLogger(WalletDetailController.class);

	@ResponseBody
	@RequestMapping(value = "/ranklist", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper ranklist(Integer currentPage, Integer pageSize,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
		Page<WalletDetailWithUser> page = new Page<WalletDetailWithUser>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(20);
		}
		Long date = DateUtil.getDateForDay(-1);
		page.addParam("date", date);
		page.addParam("type", 3);
		webResultInfoWrapper.addResult("page", walletDetailServiceRemote.queryPageRanklist(page));
		WalletDetail walletDetail = walletDetailServiceRemote.getByUserIdAndDateAndType(user.getId(),date);
		webResultInfoWrapper.addResult("myWalletDetail",walletDetail);
		Double money = 0.0;
		Long id = 0l;
		if(walletDetail!=null&&walletDetail.getMoney()!=null){
			money = walletDetail.getMoney();
			id = walletDetail.getId();
		}
		Long myRanking = walletDetailServiceRemote.getMyRanking(money,date,id);
		if(myRanking==null){
			myRanking=1l;
		}else{
			myRanking = myRanking+1l;
		}
		webResultInfoWrapper.addResult("myRanking",myRanking);
		webResultInfoWrapper.setState(SUCCEED);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Integer currentPage, Integer pageSize,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
		Page<WalletDetail> page = new Page<WalletDetail>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		page.addParam("userId", user.getId());
		webResultInfoWrapper.addResult("page", walletDetailServiceRemote.queryPage(page));
		webResultInfoWrapper.addResult("currentWithdraw",withdrawServiceRemote.currentWithdraw(user.getId()));
		webResultInfoWrapper.setState(SUCCEED);
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
		webResultInfoWrapper.addResult("walletDetail", walletDetailServiceRemote.get(id));
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method =  { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper update(WalletDetail walletDetail) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
		walletDetailServiceRemote.update(walletDetail);
		} catch (SoaException e) {
		log.error("----message:"+e.getMessage());
		webResultInfoWrapper.setState(ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
}

