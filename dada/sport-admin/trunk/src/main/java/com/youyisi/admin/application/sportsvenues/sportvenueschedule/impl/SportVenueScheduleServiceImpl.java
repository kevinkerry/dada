package com.youyisi.admin.application.sportsvenues.sportvenueschedule.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.sportsvenues.sportvenueschedule.SportVenueScheduleService;
import com.youyisi.admin.domain.sportsvenues.sportvenueschedule.SportVenueSchedule;
import com.youyisi.admin.domain.sportsvenues.sportvenueschedule.SportVenueScheduleRepository;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-07-24
 */
@Service
public class SportVenueScheduleServiceImpl implements SportVenueScheduleService {

	@Autowired
	private SportVenueScheduleRepository repository;

	@Override
	public SportVenueSchedule get(Long id) {
		return repository.get(id);
	}

	@Override
	public SportVenueSchedule save(SportVenueSchedule entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(SportVenueSchedule entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(SportVenueSchedule entity) {
		return repository.update(entity);
	}
	@Override
	public Page<SportVenueSchedule> queryPage(Page<SportVenueSchedule> page) {
		return repository.queryPage(page);
	}
}

