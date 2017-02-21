package com.youyisi.app.soa.infrastructure.persist.coupon;

import java.util.List;
import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.coupon.Coupon;

/**
 * @author shuye
 * @time 2016-07-11
 */
public interface CouponRepository extends MybatisRepository<Long, Coupon> {

	List<Coupon> getListByActivityId(Long activityId);

	Coupon getByActivityIdAndType(Map<String, Object> map);
}

