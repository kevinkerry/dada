package com.youyisi.app.soa.remote.thigh;

import java.util.List;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.orders.Orders;
import com.youyisi.sports.domain.thigh.HugThigh;
import com.youyisi.sports.domain.thigh.HugThighWithUser;

/**
 * @author shuye
 * @time 2016-07-11
 */
public interface HugThighServiceRemote extends BaseServiceInterface<Long, HugThigh> {

	Integer getHugThighCount(Long thighId, long createTime)throws SoaException;

	HugThigh getByUserAndThighId(Long userId, Long thighId, long createTime)throws SoaException;

	int pay(HugThigh hugThigh)throws SoaException;

	List<HugThighWithUser> getListByThighId(Long thighId)throws SoaException;

	void otherPay(Orders order)throws SoaException;

	Integer getByUserIdAndThighId(Long userId, Long thighId);

	Integer getCount(Long userId, Long activityId);

}

