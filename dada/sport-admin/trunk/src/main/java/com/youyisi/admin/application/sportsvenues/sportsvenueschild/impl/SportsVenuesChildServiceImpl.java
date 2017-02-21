package com.youyisi.admin.application.sportsvenues.sportsvenueschild.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.sportsvenues.sportsvenueschild.SportsVenuesChildService;
import com.youyisi.admin.domain.sportsvenues.sportsvenueschild.SportsVenuesChild;
import com.youyisi.admin.domain.sportsvenues.sportsvenueschild.SportsVenuesChildRepository;
import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.infrastructure.helper.CurrentUserHelper;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
@Service
public class SportsVenuesChildServiceImpl implements SportsVenuesChildService {

	@Autowired
	private SportsVenuesChildRepository repository;

	@Override
	public SportsVenuesChild get(Long id) {
		return repository.get(id);
	}

	@Override
	public SportsVenuesChild save(SportsVenuesChild childVenue) {
	    User currentUser = CurrentUserHelper.getCurrentUser();
	    Long userId = Long.valueOf(currentUser.getId().toString());
	    Date curDate = new Date();
	    childVenue.setCreator(userId);
	    childVenue.setCreatedTime(curDate);
	    childVenue.setStatus("A");
		return repository.save(childVenue);
}

	@Override
	public Integer delete(SportsVenuesChild childVenue) {
		return repository.delete(childVenue);
	}

	@Override
	public Integer update(SportsVenuesChild childVenue) {
	    User currentUser = CurrentUserHelper.getCurrentUser();
        Long userId = Long.valueOf(currentUser.getId().toString());
        Date curDate = new Date();
	    SportsVenuesChild existChildVenue = repository.get(childVenue.getChildVenueId());
	    childVenue.setCreator(existChildVenue.getCreator());
	    childVenue.setCreatedTime(existChildVenue.getCreatedTime());
	    childVenue.setModifier(userId);
	    childVenue.setUpdatedTime(curDate);
	    childVenue.setStatus(existChildVenue.getStatus());

		return repository.update(childVenue);
	}
	@Override
	public Page<SportsVenuesChild> queryPage(Page<SportsVenuesChild> page) {
		return repository.queryPage(page);
	}
}

