package com.youyisi.app.soa.application.integralwall;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.application.coupon.UserCouponService;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.coupon.CouponRepository;
import com.youyisi.app.soa.infrastructure.persist.integralwall.IntegralWallRepository;
import com.youyisi.app.soa.remote.integralwall.IntegralWallServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.coupon.Coupon;
import com.youyisi.sports.domain.coupon.UserCoupon;
import com.youyisi.sports.domain.integralwall.IntegralWall;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-08-09
 */
@Service
public class IntegralWallService implements IntegralWallServiceRemote {

	@Autowired
	private IntegralWallRepository repository;
	@Autowired
	private UserCouponService userCouponService;
	@Autowired
	private CouponRepository couponRepository;

	@Override
	public IntegralWall get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public IntegralWall save(IntegralWall entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(IntegralWall entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(IntegralWall entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<IntegralWall> queryPage(Page<IntegralWall> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public void note(IntegralWall integralWall) throws SoaException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", integralWall.getUserId());
		map.put("pk", integralWall.getPk());
		IntegralWall iw = repository.getByUserIdAndPK(map);
		if(iw==null){
			repository.save(integralWall);
			getCoupon(integralWall);
		}
	}

	private void getCoupon(IntegralWall integralWall) throws SoaException {
		Map<String,Object> cmap = new HashMap<String,Object>();
		cmap.put("type", 10);
		cmap.put("bonus", integralWall.getScore().doubleValue());
		Coupon c = couponRepository.getByActivityIdAndType(cmap);
		if(c==null){
			c = new Coupon();
			c.setBonus(integralWall.getScore().doubleValue());
			c.setExpiryDay(60);
			c.setType(10);
			c = couponRepository.save(c);
		}
		UserCoupon entity = new UserCoupon();
		entity.setCouponId(c.getId());
		entity.setCreateTime(System.currentTimeMillis());
		entity.setDate(DateUtil.currentDateForDay());
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, c.getExpiryDay());
		entity.setExpiryTime(calendar.getTime().getTime());
		entity.setStatus(0);
		entity.setUpdateTime(System.currentTimeMillis());
		entity.setUserId(integralWall.getUserId());
		entity.setCategory(2);
		userCouponService.save(entity);
	}

}

