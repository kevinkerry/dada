package com.youyisi.app.soa.application.wallet;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.app.soa.application.annual.AnnualYieldService;
import com.youyisi.app.soa.application.experience.ExperienceAccountService;
import com.youyisi.app.soa.application.user.UserService;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.annual.AnnualYieldDetailRepository;
import com.youyisi.app.soa.infrastructure.persist.wallet.WalletRepository;
import com.youyisi.app.soa.remote.wallet.WalletServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.annual.AnnualYield;
import com.youyisi.sports.domain.annual.AnnualYieldDetail;
import com.youyisi.sports.domain.experience.ExperienceAccount;
import com.youyisi.sports.domain.orders.Orders;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.wallet.Wallet;
import com.youyisi.sports.domain.wallet.WalletDetail;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Service
@Transactional
public class WalletService implements WalletServiceRemote {

	@Autowired
	private WalletRepository repository;
	@Autowired
	private WalletDetailService walletDetailService;
	@Autowired
	private ExperienceAccountService experienceAccountService;
	@Autowired
	private AnnualYieldDetailRepository annualYieldDetailRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private AnnualYieldService annualYieldService;

	@Override
	public Wallet get(Long id) throws SoaException {
		return repository.get(id);
	}

	@Override
	public Wallet save(Wallet entity) throws SoaException {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Wallet entity) throws SoaException {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Wallet entity) throws SoaException {
		return repository.update(entity);
	}

	@Override
	public Page<Wallet> queryPage(Page<Wallet> page) throws SoaException {
		return repository.queryPage(page);
	}

	@Override
	public Wallet getByUserId(Long userId) throws SoaException {
		return repository.getByUserId(userId);
	}

	@Override
	public void recharge(Orders order) throws SoaException {
		// TODO Auto-generated method stub
		WalletDetail walletDetail = new WalletDetail();
		walletDetail.setCreateTime(System.currentTimeMillis());
		walletDetail.setDate(DateUtil.currentDateForDay());
		walletDetail.setMoney(order.getPayAmount());
		walletDetail.setType(1);
		walletDetail.setUserId(order.getUserId());
		
		Wallet wallet = repository.getByUserId(order.getUserId());
		wallet.setTotalAsset(ArithHelper.add(wallet.getTotalAsset(),order.getPayAmount()));
		wallet.setPrincipal(ArithHelper.add(wallet.getPrincipal(),order.getPayAmount()));
		
		walletDetail.setResult(wallet.getTotalAsset());
		walletDetailService.save(walletDetail);
		
		repository.update(wallet);
		
		ExperienceAccount experienceAccount = experienceAccountService.getByUserId(order.getUserId());
		
		User user = userService.get(order.getUserId());
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, -5);
		if(experienceAccount.getLevel()!=null&&experienceAccount.getLevel().intValue()==0&&user.getRegisterTime()!=null&&user.getRegisterTime().longValue()>calendar.getTime().getTime()){
			lengthenExperience(experienceAccount);
		}
		
		if(wallet.getPrincipal()>=100){
			changeAnnualYield(order);
		}
	}

	private void lengthenExperience(ExperienceAccount experienceAccount)
			throws SoaException {
		Calendar calendar = new GregorianCalendar();
		if(experienceAccount.getExpiryTime()>System.currentTimeMillis()){
			calendar.setTime(new Date(experienceAccount.getExpiryTime()));
		}else{
			calendar.setTime(new Date());
		}
		calendar.add(Calendar.DATE, 5);
		experienceAccount.setExpiryTime(calendar.getTime().getTime());
		experienceAccount.setLevel(1);
		experienceAccountService.update(experienceAccount);
	}

	private void changeAnnualYield(Orders order) throws SoaException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", DateUtil.currentDateForDay());
		map.put("userId", order.getUserId());
		map.put("type", 9);
		
		AnnualYieldDetail annualYieldDetail = annualYieldDetailRepository.getCurrentDay(map);
		if(annualYieldDetail==null){
			AnnualYield annualYield = annualYieldService.getByUserIdAndDate(order.getUserId());
			if(annualYield.getBaseAnnualYield()==4.0){
				
				annualYield.setBaseAnnualYield(6.0);
				annualYield.setAnnualYield(ArithHelper.add(annualYield.getAnnualYield(), 2.0));
				annualYieldService.update(annualYield);
				
				annualYieldDetail = new AnnualYieldDetail();
				annualYieldDetail.setUserId(order.getUserId());
				annualYieldDetail.setType(9);
				annualYieldDetail.setDate(DateUtil.currentDateForDay());
				annualYieldDetail.setAnnualYieldIncrease(2.0);
				annualYieldDetail.setCreateTime(System.currentTimeMillis());
				annualYieldDetailRepository.save(annualYieldDetail);
			}
			
		}
	}

	public int updateByVersion(Wallet wallet, Double totalAsset) {
		// TODO Auto-generated method stub
		return repository.updateByVersion(wallet, totalAsset);
	}
}
