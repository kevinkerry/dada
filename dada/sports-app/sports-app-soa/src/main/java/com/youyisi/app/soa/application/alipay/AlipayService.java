package com.youyisi.app.soa.application.alipay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.alipay.AlipayRepository;
import com.youyisi.app.soa.remote.alipay.AlipayServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.alipay.Alipay;

/**
 * @author shuye
 * @time 2016-05-19
 */
@Service
@Transactional
public class AlipayService implements AlipayServiceRemote {

	@Autowired
	private AlipayRepository repository;

	@Override
	public Alipay get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public Alipay save(Alipay entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(Alipay entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(Alipay entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<Alipay> queryPage(Page<Alipay> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public Alipay getByUserId(Long userId) throws SoaException {
		// TODO Auto-generated method stub
		return  repository.getByUserId(userId);
	}

	@Override
	public Alipay getByAlipay(String alipay) throws SoaException {
		// TODO Auto-generated method stub
		return repository.getByAlipay(alipay);
	}
}

