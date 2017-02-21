package com.youyisi.app.soa.application.goldbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanRepository;
import com.youyisi.app.soa.remote.goldbean.GoldBeanServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.goldbean.GoldBean;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class GoldBeanService implements GoldBeanServiceRemote {

	@Autowired
	private GoldBeanRepository repository;

	@Override
	public GoldBean get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public GoldBean save(GoldBean entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(GoldBean entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(GoldBean entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<GoldBean> queryPage(Page<GoldBean> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public GoldBean getByUserId(Long userId) throws SoaException {
		// TODO Auto-generated method stub
		return repository.getByUserId(userId);
	}
}

