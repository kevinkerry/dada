package com.youyisi.app.soa.application.coupon;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.application.user.UserService;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.annual.AnnualYieldRepository;
import com.youyisi.app.soa.infrastructure.persist.coupon.CouponRepository;
import com.youyisi.app.soa.infrastructure.persist.coupon.UserCouponRepository;
import com.youyisi.app.soa.remote.coupon.UserCouponServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.activity.Activity;
import com.youyisi.sports.domain.annual.AnnualYield;
import com.youyisi.sports.domain.coupon.Coupon;
import com.youyisi.sports.domain.coupon.UserCoupon;
import com.youyisi.sports.domain.coupon.UserCouponWithCoupon;
import com.youyisi.sports.domain.coupon.UserCouponWithCouponMore;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-07-11
 */
@Service
public class UserCouponService implements UserCouponServiceRemote {

	@Autowired
	private UserCouponRepository repository;
	@Autowired
	private AnnualYieldRepository annualYieldRepository;

	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private UserService userService;

	@Override
	public UserCoupon get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public UserCoupon save(UserCoupon entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(UserCoupon entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(UserCoupon entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<UserCoupon> queryPage(Page<UserCoupon> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public AnnualYield use(UserCoupon userCoupon)throws SoaException  {
		userCoupon.setStatus(1);
		userCoupon.setDate(DateUtil.currentDateForDay());
		userCoupon.setUpdateTime(System.currentTimeMillis());
		
		repository.update(userCoupon);
		AnnualYield annualYield = annualYieldRepository.getByUserIdAndDate(userCoupon.getUserId());
		Coupon coupon = couponRepository.get(userCoupon.getCouponId());
		annualYield.setActivityAnnualYield(coupon.getAnnualYield());
		annualYieldRepository.update(annualYield);
		
		
		
		return annualYield;
	}

	@Override
	public UserCoupon getUsing(Long userId, Long date) throws SoaException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("date", date);
		return repository.getUsing(map);
	}
	
	public UserCouponWithCoupon getUsingWithCoupon(Long userId, Long date) throws SoaException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("date", date);
		return repository.getUsingWithCoupon(map);
	}

	@Override
	public Page<UserCouponWithCoupon> queryPageUserCouponWithCoupon(
			Page<UserCouponWithCoupon> page) throws SoaException {
		// TODO Auto-generated method stub
		return repository.queryPageUserCouponWithCoupon(page);
	}

	@Override
	public Page<UserCouponWithCoupon> queryPageForMyList(
			Page<UserCouponWithCoupon> page) throws SoaException {
		// TODO Auto-generated method stub
		return repository.queryPageForMyList(page);
	}

	@Override
	public Integer getCount(Map<String, Object> map) throws SoaException {
		// TODO Auto-generated method stub
		return repository.getCount(map);
	}

	@Override
	public void issue(Activity a, User user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activityId", a.getId());
		map.put("type", 9);
		Coupon c = couponRepository.getByActivityIdAndType(map);
		UserCoupon entity = new UserCoupon();
		entity.setCouponId(c.getId());
		entity.setCreateTime(System.currentTimeMillis());
		entity.setDate(DateUtil.currentDateForDay());
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, c.getExpiryDay());
		entity.setExpiryTime(calendar.getTime().getTime());
		entity.setStatus(0);
		entity.setUpdateTime(System.currentTimeMillis());
		entity.setUserId(user.getId());
		entity.setCategory(1);
		repository.save(entity);
		
		recommendedGetCoupon(a,user);
	}

	private void recommendedGetCoupon(Activity a, User user) {
		// TODO Auto-generated method stub
		User u = userService.getByUsercode(user.getRecommendCode());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activityId", a.getId());
		map.put("type", 8);
		Coupon c = couponRepository.getByActivityIdAndType(map);
		UserCoupon entity = new UserCoupon();
		entity.setCouponId(c.getId());
		entity.setCreateTime(System.currentTimeMillis());
		entity.setDate(DateUtil.currentDateForDay());
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, c.getExpiryDay());
		entity.setExpiryTime(calendar.getTime().getTime());
		entity.setStatus(0);
		entity.setUpdateTime(System.currentTimeMillis());
		entity.setUserId(u.getId());
		entity.setCategory(1);
		repository.save(entity);
	}

	@Override
	public Page<UserCouponWithCouponMore> queryPageForInviteFriend(
			Page<UserCouponWithCouponMore> page) throws SoaException {
		// TODO Auto-generated method stub
		return repository.queryPageForInviteFriend(page);
	}

	public UserCoupon getByCategoryAndUser(Map<String, Object> cmap) {
		// TODO Auto-generated method stub
		return  repository.getByCategoryAndUser(cmap);
	}
}

