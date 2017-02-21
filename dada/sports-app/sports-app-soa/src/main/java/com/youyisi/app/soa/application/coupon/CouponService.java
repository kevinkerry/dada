package com.youyisi.app.soa.application.coupon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.coupon.CouponRepository;
import com.youyisi.app.soa.remote.coupon.CouponServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.coupon.Coupon;

/**
 * @author shuye
 * @time 2016-07-11
 */
@Service
public class CouponService implements CouponServiceRemote {

	@Autowired
	private CouponRepository repository;

	@Override
	public Coupon get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public Coupon save(Coupon entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(Coupon entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(Coupon entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<Coupon> queryPage(Page<Coupon> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public List<Coupon> getListByActivityId(Long activityId)throws SoaException {
		return repository.getListByActivityId(activityId);
	}

	@Override
	public Coupon getByActivityIdAndType(Long activityId, Integer type) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activityId", activityId);
		map.put("type", type);
		return repository.getByActivityIdAndType(map);
	}
}

