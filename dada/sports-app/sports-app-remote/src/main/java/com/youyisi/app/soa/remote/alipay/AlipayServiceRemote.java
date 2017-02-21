package com.youyisi.app.soa.remote.alipay;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.alipay.Alipay;

/**
 * @author shuye
 * @time 2016-05-19
 */
public interface AlipayServiceRemote extends BaseServiceInterface<Long, Alipay> {
	
	Alipay getByUserId(Long userId) throws SoaException;

	Alipay getByAlipay(String alipay)throws SoaException;

}

