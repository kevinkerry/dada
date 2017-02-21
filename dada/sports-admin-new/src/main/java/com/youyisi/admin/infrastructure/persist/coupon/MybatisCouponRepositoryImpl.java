package com.youyisi.admin.infrastructure.persist.coupon;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.admin.domain.coupon.CouponRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-07-18
 */
@Repository
public class MybatisCouponRepositoryImpl extends MybatisOperations<Long, Coupon> implements CouponRepository {

	@Override
	public Integer delByActivityId(Long activityId) {
		return getSqlSession().delete(getNamespace().concat(".delByActivityId"), activityId);
	}

	@Override
	public Integer countCouponNum(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".countCouponNum"), map);
	}

	@Override
	public Coupon getCouponByActivityIdAndType(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".getCouponByActivityIdAndType"), map);
	}

	@Override
	public Double sumCouponMoney(Long activityId) {
		return getSqlSession().selectOne(getNamespace().concat(".sumCouponMoney"), activityId);
	}

	@Override
	public List<Coupon> sumSlackerMoney(Long activityId) {
		return getSqlSession().selectList(getNamespace().concat(".sumSlackerMoney"), activityId);
	}

	@Override
	public List<Coupon> getCouponByActivityId(Long activityId) {
		return getSqlSession().selectList(getNamespace().concat(".getCouponByActivityId"), activityId);
	}

	@Override
	public Double getBonusByFirst(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".getBonusByFirst"), map);
	}

	@Override
	public Double sumCouponMoneyByActivityId(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".sumCouponMoneyByActivityId"), map);
	}

	@Override
	public Double sumUnusedMoney(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".sumUnusedMoney"), map);
	}

	@Override
	public Double sumRelayRaceMoney(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".sumRelayRaceMoney"), map);
	}

	@Override
	public Integer getSnatchBonusStatus(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".getSnatchBonusStatus"), map);
	}

	@Override
	public Integer getInviteRegister(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".getInviteRegister"), map);
	}

	@Override
	public Double sumInviteMoney(Long activityId) {

		return getSqlSession().selectOne(getNamespace().concat(".sumInviteMoney"), activityId);
	}

}
