package com.youyisi.admin.application.indiana.indianadetail.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.indiana.indianadetail.IndianaDetailService;
import com.youyisi.admin.domain.indiana.indianadetail.IndianaDetail;
import com.youyisi.admin.domain.indiana.indianadetail.IndianaDetailRepository;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
@Service
public class IndianaDetailServiceImpl implements IndianaDetailService {

	@Autowired
	private IndianaDetailRepository repository;

	@Override
	public IndianaDetail get(Long id) {
		return repository.get(id);
	}

	@Override
	public IndianaDetail save(IndianaDetail indianaDetail) {
		return repository.save(indianaDetail);
}

	@Override
	public Integer delete(IndianaDetail indianaDetail) {
		return repository.delete(indianaDetail);
	}

	@Override
	public Integer update(IndianaDetail indianaDetail) {
		return repository.update(indianaDetail);
	}
	@Override
	public Page<IndianaDetail> queryPage(Page<IndianaDetail> page) {
		return repository.queryPage(page);
	}
}

