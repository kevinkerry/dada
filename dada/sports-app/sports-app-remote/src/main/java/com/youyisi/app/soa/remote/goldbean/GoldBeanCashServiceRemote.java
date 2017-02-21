package com.youyisi.app.soa.remote.goldbean;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.goldbean.GoldBean;
import com.youyisi.sports.domain.goldbean.GoldBeanCash;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface GoldBeanCashServiceRemote extends BaseServiceInterface<Long, GoldBeanCash> {

	void add(GoldBeanCash goldBeanCash, GoldBean goldBean)throws SoaException;

}

