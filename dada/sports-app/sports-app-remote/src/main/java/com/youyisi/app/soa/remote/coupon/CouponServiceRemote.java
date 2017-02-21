package com.youyisi.app.soa.remote.coupon;

import java.util.List;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.coupon.Coupon;

/**
 * @author shuye
 * @time 2016-07-11
 */
public interface CouponServiceRemote extends BaseServiceInterface<Long, Coupon> {

	List<Coupon> getListByActivityId(Long activityId) throws SoaException;

	Coupon getByActivityIdAndType(Long activityId, Integer type);

}

