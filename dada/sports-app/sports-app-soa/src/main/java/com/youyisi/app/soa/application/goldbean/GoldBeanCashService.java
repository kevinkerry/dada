package com.youyisi.app.soa.application.goldbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanCashRepository;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanDetailRepository;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanRepository;
import com.youyisi.app.soa.remote.goldbean.GoldBeanCashServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.goldbean.GoldBean;
import com.youyisi.sports.domain.goldbean.GoldBeanCash;
import com.youyisi.sports.domain.goldbean.GoldBeanDetail;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class GoldBeanCashService implements GoldBeanCashServiceRemote {

	@Autowired
	private GoldBeanCashRepository repository;
	@Autowired
	private GoldBeanRepository goldBeanRepository;
	@Autowired
	private GoldBeanDetailRepository goldBeanDetailRepository;
	@Override
	public GoldBeanCash get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public GoldBeanCash save(GoldBeanCash entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(GoldBeanCash entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(GoldBeanCash entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<GoldBeanCash> queryPage(Page<GoldBeanCash> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public void add(GoldBeanCash goldBeanCash, GoldBean goldBean)throws SoaException {
		
		goldBeanCash = repository.save(goldBeanCash);
		GoldBeanDetail entity = new GoldBeanDetail();
		entity.setCategory(3);
		entity.setCreateTime(System.currentTimeMillis());
		entity.setDate(DateUtil.currentDateForDay());
		entity.setGoldBean(goldBeanCash.getGoldBean());
		
		entity.setNote("兑现");
		entity.setProductId(goldBeanCash.getId());
		
		goldBean.setGoldBean(ArithHelper.sub(goldBean.getGoldBean(), goldBeanCash.getGoldBean()));
		entity.setResult(goldBean.getGoldBean());
		entity.setType(-1);
		entity.setUserId(goldBeanCash.getUserId());
		
		goldBeanDetailRepository.save(entity);
		goldBeanRepository.update(goldBean);
	}
}

