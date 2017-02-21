package com.youyisi.app.soa.application.goldbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.goldbean.GoldBeanDetailServiceRemote;
import com.youyisi.sports.domain.goldbean.GoldBeanDetail;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanDetailRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class GoldBeanDetailService implements GoldBeanDetailServiceRemote {

	@Autowired
	private GoldBeanDetailRepository repository;

	@Override
	public GoldBeanDetail get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public GoldBeanDetail save(GoldBeanDetail entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(GoldBeanDetail entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(GoldBeanDetail entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<GoldBeanDetail> queryPage(Page<GoldBeanDetail> page) throws SoaException{
		return repository.queryPage(page);
	}
}

