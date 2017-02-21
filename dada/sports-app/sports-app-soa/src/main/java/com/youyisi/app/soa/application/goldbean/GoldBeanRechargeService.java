package com.youyisi.app.soa.application.goldbean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanRechargeRepository;
import com.youyisi.app.soa.remote.goldbean.GoldBeanRechargeServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.goldbean.GoldBeanRecharge;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class GoldBeanRechargeService implements GoldBeanRechargeServiceRemote {

	@Autowired
	private GoldBeanRechargeRepository repository;

	@Override
	public GoldBeanRecharge get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public GoldBeanRecharge save(GoldBeanRecharge entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(GoldBeanRecharge entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(GoldBeanRecharge entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<GoldBeanRecharge> queryPage(Page<GoldBeanRecharge> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public List<GoldBeanRecharge> list() throws SoaException {
		// TODO Auto-generated method stub
		return repository.list();
	}
}

