package com.youyisi.app.soa.application.annual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.annual.AnnualYieldDetailRepository;
import com.youyisi.app.soa.remote.annual.AnnualYieldDetailServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.annual.AnnualYieldDetail;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Service
@Transactional
public class AnnualYieldDetailService implements AnnualYieldDetailServiceRemote {

	@Autowired
	private AnnualYieldDetailRepository repository;

	@Override
	public AnnualYieldDetail get(Long id) throws SoaException {
		return repository.get(id);
	}

	@Override
	public AnnualYieldDetail save(AnnualYieldDetail entity) throws SoaException {
		return repository.save(entity);
	}

	@Override
	public Integer delete(AnnualYieldDetail entity) throws SoaException {
		return repository.delete(entity);
	}

	@Override
	public Integer update(AnnualYieldDetail entity) throws SoaException {
		return repository.update(entity);
	}

	@Override
	public Page<AnnualYieldDetail> queryPage(Page<AnnualYieldDetail> page) throws SoaException {
		return repository.queryPage(page);
	}

	@Override
	public AnnualYieldDetail getDetailByUserIdAndType(Long userId, Integer type) {
		AnnualYieldDetail detail = new AnnualYieldDetail();
		detail.setUserId(userId);
		detail.setType(type);
		detail.setDate(DateUtil.currentDateForDay());
		List<AnnualYieldDetail> query = repository.query(detail);
		if (query != null && query.size() > 0) {
			detail = query.get(0);
		} else {
			detail = null;
		}
		return detail;
	}

	@Override
	public Double getSumCurrentDayDistance(Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("date", DateUtil.currentDateForDay());
		return repository.getSumCurrentDayDistance(map);
	}
}
