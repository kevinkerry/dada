package com.youyisi.app.soa.application.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.app.soa.application.annual.AnnualYieldDetailService;
import com.youyisi.app.soa.application.annual.AnnualYieldService;
import com.youyisi.app.soa.application.coupon.UserCouponService;
import com.youyisi.app.soa.application.wallet.WalletService;
import com.youyisi.app.soa.application.withdraw.WithdrawService;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.coupon.UserCouponRepository;
import com.youyisi.app.soa.infrastructure.persist.distance.DistanceRepository;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanCashRepository;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanRepository;
import com.youyisi.app.soa.infrastructure.persist.step.StepRepository;
import com.youyisi.app.soa.infrastructure.persist.user.UserRepository;
import com.youyisi.app.soa.infrastructure.redis.RedisClient;
import com.youyisi.app.soa.remote.user.UserServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.constant.Constants;
import com.youyisi.sports.domain.annual.AnnualYield;
import com.youyisi.sports.domain.coupon.UserCouponWithCoupon;
import com.youyisi.sports.domain.distance.Distance;
import com.youyisi.sports.domain.experience.ExperienceAccount;
import com.youyisi.sports.domain.goldbean.GoldBean;
import com.youyisi.sports.domain.goldbean.GoldBeanMore;
import com.youyisi.sports.domain.step.Step;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.user.UserLessInfo;
import com.youyisi.sports.domain.user.UserMoreInfo;
import com.youyisi.sports.domain.wallet.Wallet;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;
import com.youyisi.sports.utils.StrUtil;

/**
 * @author shuye
 * @time 2016-05-10
 */
@Service
@Transactional
public class UserService implements UserServiceRemote {

	@Autowired
	private UserRepository repository;

	@Autowired
	private AnnualYieldDetailService yieldDetailService;
	
	@Autowired
	private AnnualYieldService annualYieldService;
	@Autowired
	private WithdrawService withdrawService;
	
	@Autowired
	private UserCouponService userCouponService;
	@Autowired
	private WalletService walletService;
	@Autowired
	private GoldBeanCashRepository goldBeanCashRepository;
	@Autowired
	private StepRepository stepRepository;
	@Autowired
	private DistanceRepository distanceRepository;
	
	@Autowired
	private GoldBeanRepository goldBeanRepository;
	@Autowired
	private UserCouponRepository userCouponRepository;
	@Override
	public User get(Long id) throws SoaException {
		return repository.get(id);
	}

	@Override
	public User save(User entity) throws SoaException {
		Long time = System.currentTimeMillis();
		entity.setRegisterTime(time);
		entity.setCreateTime(time);
		entity.setUpdateTime(time);
		entity.setStatus(0);
		return repository.save(entity);
	}

	@Override
	public Integer delete(User entity) throws SoaException {
		return repository.delete(entity);
	}

	@Override
	public Integer update(User entity) throws SoaException {
		entity.setUpdateTime(System.currentTimeMillis());
		return repository.update(entity);
	}

	@Override
	public Page<User> queryPage(Page<User> page) throws SoaException {
		return repository.queryPage(page);
	}

	@Override
	public User getByUserName(String userName) throws SoaException {

		return repository.getByUserName(userName);
	}

	@Override
	public UserMoreInfo login(UserMoreInfo user) throws SoaException {
		UserMoreInfo us = repository.login(user.getUsername());
		boolean istrue = true;
		// 登陆状态 false 失败 true 成功
		boolean loginState = false;
		if (user.getUserType() == 1) {
			if (us != null && us.getPassword().equals(StrUtil.getMD532Str(user.getPassword()))) {
				loginState = true;
			} else {
				us = user;
				us.setStatus(-1);
			}
		} else {
			// 第三方登陆 第一次登陆 走注册流程
			if (us == null) {
				user.setRegisterTime(System.currentTimeMillis());
				user.setCreateTime(System.currentTimeMillis());
				user.setStatus(0);
				user.setUpdateTime(System.currentTimeMillis());
				if(StringUtils.isBlank(user.getUsercode())){
					Long usercode = RedisClient.increment(Constants.USER_CODE_KEY, 1l);
					user.setUsercode(usercode+"");
				}
				us = repository.saveUserMoreInfo(user);
				if (us.getId() != null) {
					istrue = false;
					loginState = true;
				}
			} else {
				loginState = true;
			}
		}
		// 登陆成功
		if (loginState) {
			if (istrue) {
				// 更新设备号 设备类型
				us.setClientId(user.getClientId());
				us.setClientType(user.getClientType());
				us.setUpdateTime(System.currentTimeMillis());
				if(StringUtils.isNotBlank(user.getPhoneModel())){
					us.setPhoneModel(user.getPhoneModel());
				}
				repository.updateUserMoreInfo(us);
			}
			// 判断是否达到最大步数
			if (yieldDetailService.getDetailByUserIdAndType(us.getId(), 4) != null) {
				us.setStepMax(true);
			}
			// 判断是否达到最大公里数
			Double distance = yieldDetailService.getSumCurrentDayDistance(us.getId());
			if (distance != null && distance == 3.0) {
				us.setDistanceMax(true);
			}
			// 计算本金
			us = getPredictFortune(us,us.getAnnualYield());
			
			us = setYestodayStepAndDistance(us);
			
			us = setGoldBean(us);
			
			us.setCurrentWithdraw(withdrawService.currentWithdraw(us.getId()));
			// 清除之前的token
			RedisClient.deletes(Constants.USER_KEY + us.getId() + "_*");
			// 获得token
			String uuid = us.getId() + "_" + StrUtil.getUUID();
			// 用户信息保存Redis
			RedisClient.set(Constants.USER_KEY + uuid, us);
			us.setToken(uuid);
		}
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

	private UserMoreInfo getPredictFortune(UserMoreInfo us,AnnualYield annualYield) throws SoaException {
		ExperienceAccount experience = us.getExperienceAccount();
		Wallet wallet = us.getWallet();
		double principal = 0.0;
		if(wallet!=null&&wallet.getPrincipal()!=null){
			principal = wallet.getPrincipal();
		}
		if (experience != null) {
			if (experience.getExpiryTime() > DateUtil.getTime()) {
				if (wallet != null) {
					principal = ArithHelper.add(principal, experience.getMoney());
				}
			}else{
				experience.setMoney(0.0);
				us.setExperienceAccount(experience);
			}
		} else {
			us.setIsExperienceAccount(true);
		}
		Double goldBeanCash = goldBeanCashRepository.getSumCash(us.getId());
		if(goldBeanCash==null){
			goldBeanCash=0.0;
		}
		principal = ArithHelper.add(principal, goldBeanCash);
		
		if (wallet != null && annualYield != null) {
			UserCouponWithCoupon userCoupon = userCouponService.getUsingWithCoupon(us.getId(), DateUtil.currentDateForDay());
			if(annualYield.getActivityAnnualYield()!=null&&annualYield.getActivityAnnualYield()>0.0&&userCoupon!=null){
				principal = ArithHelper.add(principal, userCoupon.getCoupon().getBonus());
				us.setPredictFortune(ArithHelper.div(ArithHelper.mul(principal, annualYield.getActivityAnnualYield()), 36500.0, 2));
			}else{
				if(userCoupon!=null&&userCoupon.getCoupon()!=null){
					principal = ArithHelper.add(principal, userCoupon.getCoupon().getBonus());
				}
				us.setPredictFortune(ArithHelper.div(ArithHelper.mul(principal, annualYield.getAnnualYield()), 36500.0, 2));
				
			}
		}
		us.setBasisWealth(principal);
		us.setWallet(wallet);
		return us;
	}

	@Override
	public User getByUserNameOrMobile(String userName) throws SoaException {

		return repository.getByUserNameOrMobile(userName);
	}

	@Override
	public User bindingMobile(User user) throws SoaException {
		User us = repository.get(user.getId());
		us.setMobile(user.getMobile());
		if (user.getPassword() != null) {
			us.setPassword(user.getPassword());
		}
		if(us.getUserType()==1){
			us.setUsername(user.getMobile());
		}
		repository.update(us);
		return us;
	}

	@Override
	public UserMoreInfo getMyUserInfo(User user) throws SoaException {
		UserMoreInfo us = repository.login(user.getUsername());
		// 判断是否达到最大步数
		if (yieldDetailService.getDetailByUserIdAndType(us.getId(), 4) != null) {
			us.setStepMax(true);
		}
		// 判断是否达到最大公里数
		Double distance = yieldDetailService.getSumCurrentDayDistance(us.getId());
		if (distance != null && distance == 3.0) {
			us.setDistanceMax(true);
		}
		// 计算本金
		us = getPredictFortuneAndCreateAnnualYield(us);
		
		us = setYestodayStepAndDistance(us);
		
		us.setCurrentWithdraw(withdrawService.currentWithdraw(us.getId()));
		
		us = setGoldBean(us);
		
		
		
		us.setUpdateTime(System.currentTimeMillis());
		if(StringUtils.isBlank(us.getUsercode())){
			Long usercode = RedisClient.increment(Constants.USER_CODE_KEY, 1l);
			us.setUsercode(usercode+"");
		}
		RedisClient.set(Constants.USER_KEY + user.getToken(), us);
		repository.updateUserMoreInfo(us);
		return us;
	}

	private UserMoreInfo setGoldBean(UserMoreInfo us) {
		GoldBeanMore goldBean = new GoldBeanMore();
		GoldBean g = goldBeanRepository.getByUserId(us.getId());
		if(g==null){
			g = new GoldBean();
			g.setUserId(us.getId());
			g.setGoldBean(0.0);
			g = goldBeanRepository.save(g);
		}
		goldBean.setGoldBean(g.getGoldBean());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", 0);
		map.put("userId", us.getId());
		map.put("currentTime",System.currentTimeMillis());
		goldBean.setUserCouponCount(userCouponRepository.getCount(map));
		goldBean.setGoldBeanCash(goldBeanCashRepository.getSumCash(us.getId()));
		us.setGoldBean(goldBean);
		return us;
	}

	private UserMoreInfo getPredictFortuneAndCreateAnnualYield(UserMoreInfo us) throws SoaException {
		AnnualYield annualYield = us.getAnnualYield();
		if(annualYield==null){
			annualYield = new AnnualYield();
			annualYield.setBaseAnnualYield(getBaseAnnualYield(us.getId()));
			annualYield.setCreateTime(System.currentTimeMillis());
			annualYield.setDate(DateUtil.currentDateForDay());
			annualYield.setUpdateTime(System.currentTimeMillis());
			annualYield.setUserId(us.getId());
			annualYield.setAnnualYield(annualYield.getBaseAnnualYield());
			annualYieldService.save(annualYield);
			us.setAnnualYield(annualYield);
		}
		return getPredictFortune(us,annualYield);
	}
	
	private Double getBaseAnnualYield(Long userId) throws SoaException {
		Wallet wallet = walletService.getByUserId(userId);
		if (wallet == null) {
			wallet = new Wallet();
			wallet.setUserId(userId);
			wallet.setTotalAsset(0.0);
			wallet.setPrincipal(0.0);
			wallet.setIncome(0.0);
			walletService.save(wallet);
		}
		if (wallet.getPrincipal() == null || wallet.getPrincipal() < 100.0) {
			return 4.0;
		}
		return 6.0;
	}

	@Override
	public User getByClientId(String clientId) {
		// TODO Auto-generated method stub
		return repository.getByClientId(clientId);
	}
	@Override
	public User getByUsercode(String recommendCode) {
		// TODO Auto-generated method stub
		return repository.getByUsercode(recommendCode);
	}

	@Override
	public Page<UserLessInfo> queryPageForPush(Page<UserLessInfo> page)
			throws SoaException {
		// TODO Auto-generated method stub
		return repository.queryPageForPush(page);
	}

}
