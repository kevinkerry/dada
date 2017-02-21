package com.youyisi.app.soa.remote.goldbean;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.goldbean.GoldBean;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface GoldBeanServiceRemote extends BaseServiceInterface<Long, GoldBean> {

	GoldBean getByUserId(Long userId)throws SoaException;

}

