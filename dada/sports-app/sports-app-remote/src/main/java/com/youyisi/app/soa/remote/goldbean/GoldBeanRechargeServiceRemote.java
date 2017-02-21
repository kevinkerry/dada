package com.youyisi.app.soa.remote.goldbean;

import java.util.List;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.goldbean.GoldBeanRecharge;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface GoldBeanRechargeServiceRemote extends BaseServiceInterface<Long, GoldBeanRecharge> {

	List<GoldBeanRecharge> list()throws SoaException;

}

