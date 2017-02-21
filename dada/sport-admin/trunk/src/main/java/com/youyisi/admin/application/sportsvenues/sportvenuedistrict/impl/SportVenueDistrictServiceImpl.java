package com.youyisi.admin.application.sportsvenues.sportvenuedistrict.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.sportsvenues.sportvenuedistrict.SportVenueDistrictService;
import com.youyisi.admin.domain.sportsvenues.SportsVenues;
import com.youyisi.admin.domain.sportsvenues.SportsVenuesRepository;
import com.youyisi.admin.domain.sportsvenues.sportsvenueschild.SportsVenuesChild;
import com.youyisi.admin.domain.sportsvenues.sportsvenueschild.SportsVenuesChildRepository;
import com.youyisi.admin.domain.sportsvenues.sportvenuedistrict.SportVenueDistrict;
import com.youyisi.admin.domain.sportsvenues.sportvenuedistrict.SportVenueDistrictRepository;
import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.infrastructure.helper.CurrentUserHelper;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
@Service
public class SportVenueDistrictServiceImpl implements SportVenueDistrictService {

	@Autowired
	private SportVenueDistrictRepository repository;
	
	@Autowired
    private SportsVenuesRepository sRepository;
	
	@Autowired
    private SportsVenuesChildRepository sChildRepository;

	@Override
	public SportVenueDistrict get(Long id) {
		return repository.get(id);
	}

	@Override
	public SportVenueDistrict save(SportVenueDistrict venueDistrict) {
	    User currentUser = CurrentUserHelper.getCurrentUser();
        Long userId = Long.valueOf(currentUser.getId().toString());
        Date curDate = new Date();
        
        SportsVenuesChild sportsVenuesChild = sChildRepository.get(venueDistrict.getChildVenueId());
        SportsVenues sportsVenues = sRepository.get(sportsVenuesChild.getVenueId());
	    venueDistrict.setCreator(userId);
	    venueDistrict.setCreatedTime(curDate);
	    venueDistrict.setVenueId(sportsVenues.getVenueId());
	    venueDistrict.setStatus("A");
	    venueDistrict.setDistrictStatus(1);
		return repository.save(venueDistrict);
}

	@Override
	public Integer delete(SportVenueDistrict venueDistrict) {
		return repository.delete(venueDistrict);
	}

	@Override
	public Integer update(SportVenueDistrict venueDistrict) {
	    User currentUser = CurrentUserHelper.getCurrentUser();
        Long userId = Long.valueOf(currentUser.getId().toString());
        Date curDate = new Date();
        
        SportVenueDistrict existedVenueDistrict = repository.get(venueDistrict.getDistrictId());
        venueDistrict.setCreator(existedVenueDistrict.getCreator());
        venueDistrict.setCreatedTime(existedVenueDistrict.getCreatedTime());
        venueDistrict.setStatus(existedVenueDistrict.getStatus());
        if(null == venueDistrict.getDistrictStatus()) {
            venueDistrict.setDistrictStatus(existedVenueDistrict.getDistrictStatus()); 
        }
        
        venueDistrict.setModifier(userId);
        venueDistrict.setUpdatedTime(curDate);
        
		return repository.update(venueDistrict);
	}
	@Override
	public Page<SportVenueDistrict> queryPage(Page<SportVenueDistrict> page) {
		return repository.queryPage(page);
	}

    @Override
    public boolean isExistDistrict(SportVenueDistrict sportVenueDistrict) {
        return repository.isExistDistrict(sportVenueDistrict);
    }
}

