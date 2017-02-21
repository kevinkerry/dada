package com.youyisi.admin.application.sportsvenues.sportvenueimage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.sportsvenues.sportvenueimage.SportVenueImageService;
import com.youyisi.admin.domain.sportsvenues.sportvenueimage.SportVenueImage;
import com.youyisi.admin.domain.sportsvenues.sportvenueimage.SportVenueImageRepository;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
@Service
public class SportVenueImageServiceImpl implements SportVenueImageService {

	@Autowired
	private SportVenueImageRepository repository;

	@Override
	public SportVenueImage get(Long id) {
		return repository.get(id);
	}

	@Override
	public SportVenueImage save(SportVenueImage entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(SportVenueImage entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(SportVenueImage entity) {
		return repository.update(entity);
	}
	@Override
	public Page<SportVenueImage> queryPage(Page<SportVenueImage> page) {
		return repository.queryPage(page);
	}
}

