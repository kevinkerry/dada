package com.youyisi.app.soa.application.experience;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.app.soa.application.annual.AnnualYieldService;
import com.youyisi.app.soa.application.wallet.WalletService;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.distance.DistanceRepository;
import com.youyisi.app.soa.infrastructure.persist.experience.ExperienceAccountRepository;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanCashRepository;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanRepository;
import com.youyisi.app.soa.infrastructure.persist.step.StepRepository;
import com.youyisi.app.soa.infrastructure.persist.user.UserRepository;
import com.youyisi.app.soa.remote.experience.ExperienceAccountServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.annual.AnnualYield;
import com.youyisi.sports.domain.distance.Distance;
import com.youyisi.sports.domain.experience.ExperienceAccount;
import com.youyisi.sports.domain.goldbean.GoldBean;
import com.youyisi.sports.domain.step.Step;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.user.UserMoreInfo;
import com.youyisi.sports.domain.wallet.Wallet;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Service
@Transactional
public class ExperienceAccountService implements ExperienceAccountServiceRemote {

	@Autowired
	private ExperienceAccountRepository repository;
	@Autowired
	private AnnualYieldService annualYieldService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GoldBeanRepository goldBeanRepository;
	
	@Autowired
	private GoldBeanCashRepository goldBeanCashRepository;
	@Autowired
	private StepRepository stepRepository;
	@Autowired
	private DistanceRepository distanceRepository;
	@Override
	public ExperienceAccount get(Long id) throws SoaException {
		return repository.get(id);
	}

	@Override
	public ExperienceAccount save(ExperienceAccount entity) throws SoaException {
		return repository.save(entity);
	}

	@Override
	public Integer delete(ExperienceAccount entity) throws SoaException {
		return repository.delete(entity);
	}

	@Override
	public Integer update(ExperienceAccount entity) throws SoaException {
		return repository.update(entity);
	}

	@Override
	public Page<ExperienceAccount> queryPage(Page<ExperienceAccount> page) throws SoaException {
		return repository.queryPage(page);
	}

	@Override
	public ExperienceAccount getByUserId(Long userId) throws SoaException {
		return repository.getByUserId(userId);
	}

	@Override
	public UserMoreInfo addExperienceAccount(User user) throws SoaException {
		ExperienceAccount experienceAccount = repository.getByUserId(user.getId());
		if (experienceAccount == null) {
			experienceAccount = new ExperienceAccount();
			experienceAccount.setUserId(user.getId());
			experienceAccount.setCreateTime(DateUtil.getTime());
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new Date(experienceAccount.getCreateTime()));
			calendar.add(Calendar.DATE, 5);
			experienceAccount.setExpiryTime(calendar.getTime().getTime());
			experienceAccount.setMoney(3000.0);
			experienceAccount.setLevel(0);
			repository.save(experienceAccount);
		}

		AnnualYield annualYield = annualYieldService.getByUserIdAndDate(user.getId());
		if (annualYield == null) {
			annualYield = new AnnualYield();
			annualYield.setUserId(user.getId());
			annualYield.setDate(DateUtil.currentDateForDay());
			annualYield.setAnnualYield(4.0);
			annualYield.setBaseAnnualYield(4.0);
			annualYield.setCreateTime(System.currentTimeMillis());
			annualYield.setUpdateTime(System.currentTimeMillis());
			annualYieldService.save(annualYield);
		}

		Wallet wallet = walletService.getByUserId(user.getId());
		if (wallet == null) {
			wallet = new Wallet();
			wallet.setUserId(user.getId());
			wallet.setTotalAsset(0.0);
			wallet.setPrincipal(0.0);
			wallet.setIncome(0.0);
			walletService.save(wallet);
		}
		GoldBean goldBean = goldBeanRepository.getByUserId(user.getId());
		if(goldBean==null){
			goldBean = new GoldBean();
			goldBean.setGoldBean(0.0);
			goldBean.setUserId(user.getId());
			goldBeanRepository.save(goldBean);
		}

		UserMoreInfo us = userRepository.login(user.getUsername());
		// 计算本金
		ExperienceAccount experience = us.getExperienceAccount();
		wallet = us.getWallet();
		annualYield = us.getAnnualYield();
		if (experience != null) {
			if (experience.getExpiryTime() > DateUtil.getTime()) {
				if (wallet != null) {
					wallet.setPrincipal(ArithHelper.add(wallet.getPrincipal(), experience.getMoney()));
				}
			}else{
				experience.setMoney(0.0);
				us.setExperienceAccount(experience);
			}
		} else {
			us.setIsExperienceAccount(true);
		}
		Double goldBeanCash = goldBeanCashRepository.getSumCash(user.getId());
		if(goldBeanCash==null){
			goldBeanCash=0.0;
		}
		us = setYestodayStepAndDistance(us);
		wallet.setPrincipal(ArithHelper.add(wallet.getPrincipal(), goldBeanCash));
		if (wallet != null && annualYield != null) {
			us.setPredictFortune(
					ArithHelper.div(ArithHelper.mul(wallet.getPrincipal(), annualYield.getAnnualYield()), 36500.0, 2));
		}
		us.setBasisWealth(wallet.getPrincipal());
		us.setWallet(wallet);
		return us;
	}
	private UserMoreInfo setYestodayStepAndDistance(UserMoreInfo us) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", us.getId());
		map.put("date", DateUtil.getDateForDay(-1));
		Distance yestodayDistance = distanceRepository.getByUserIdAndEnyDate(map);
		if(yestodayDistance==null){
			us.setYestodayDistance(0.0);
		}else{
			us.setYestodayDistance(yestodayDistance.getDistance());
		}
		
		Step s = stepRepository.getByUserIdAndAnyDate(map);
		if(s==null){
			us.setYestodayStep(0);
		}else{
			
			us.setYestodayStep(s.getStep());
		}
		return us;
	}
}
