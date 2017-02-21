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
import com.youyisi.app.soa.infrastructure.persist.integralwall.DoumIntegralWallRepository;
import com.youyisi.app.soa.remote.integralwall.DoumIntegralWallServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.coupon.Coupon;
import com.youyisi.sports.domain.coupon.UserCoupon;
import com.youyisi.sports.domain.integralwall.DoumIntegralWall;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-08-15
 */
@Service
public class DoumIntegralWallService implements DoumIntegralWallServiceRemote {

	@Autowired
	private DoumIntegralWallRepository repository;
	@Autowired
	private UserCouponService userCouponService;
	@Autowired
	private CouponRepository couponRepository;

	@Override
	public DoumIntegralWall get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public DoumIntegralWall save(DoumIntegralWall entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(DoumIntegralWall entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(DoumIntegralWall entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<DoumIntegralWall> queryPage(Page<DoumIntegralWall> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public void domobNote(DoumIntegralWall integralWall) throws SoaException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", integralWall.getUserId());
		map.put("orderid", integralWall.getOrderid());
		DoumIntegralWall iw = repository.getByUserIdAndOrderId(map);
		if(iw==null){
			repository.save(integralWall);
			getCoupon(integralWall);
		}
	}

	private void getCoupon(DoumIntegralWall integralWall) throws SoaException {
		Map<String,Object> cmap = new HashMap<String,Object>();
		cmap.put("category",2);
		cmap.put("userId", integralWall.getUserId());
		cmap.put("time",System.currentTimeMillis());
		UserCoupon uc = userCouponService.getByCategoryAndUser(cmap);
		Coupon c = null;
		if(uc!=null){
			c = couponRepository.get(uc.getCouponId());
			c.setBonus(ArithHelper.add(c.getBonus(),integralWall.getPoint().doubleValue()));
			couponRepository.update(c);
		}else{
			c = new Coupon();
			c.setBonus(integralWall.getPoint().doubleValue());
			c.setExpiryDay(60);
			c.setType(10);
			c = couponRepository.save(c);
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
}

