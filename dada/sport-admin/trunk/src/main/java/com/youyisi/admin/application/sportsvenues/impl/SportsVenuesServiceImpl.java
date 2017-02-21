package com.youyisi.admin.application.sportsvenues.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.sportsvenues.SportsVenuesService;
import com.youyisi.admin.domain.sportsvenues.SportsVenues;
import com.youyisi.admin.domain.sportsvenues.SportsVenuesRepository;
import com.youyisi.admin.domain.sportsvenues.sportvenueimage.SportVenueImage;
import com.youyisi.admin.domain.sportsvenues.sportvenueimage.SportVenueImageRepository;
import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.infrastructure.constant.Constant;
import com.youyisi.admin.infrastructure.helper.CurrentUserHelper;
import com.youyisi.admin.infrastructure.message.ActiveMqSender;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
@Service
public class SportsVenuesServiceImpl implements SportsVenuesService {

	@Autowired
	private SportsVenuesRepository repository;
	
	@Autowired
    private SportVenueImageRepository imageRepository;
	
	@Autowired
	private ActiveMqSender activeMqSender;

	@Override
	public SportsVenues get(Long id) {
		return repository.get(id);
	}

	@Override
	public SportsVenues save(SportsVenues sportsVenue) {
	    User currentUser = CurrentUserHelper.getCurrentUser();
	    Long userId = Long.valueOf(currentUser.getId().toString());
	    Date curDate = new Date();
	    sportsVenue.setCreator(userId);
	    sportsVenue.setCreatedTime(curDate);
	    sportsVenue.setStatus("A");
	    sportsVenue.setVenueChannel(3);
	    SportsVenues savedVenue = repository.save(sportsVenue);
	    
	    List<SportVenueImage> venueImages = sportsVenue.getVenueImages();
	    if(null != venueImages) {
	        for(SportVenueImage venueImage : venueImages) {
	            venueImage.setCreator(userId);
	            venueImage.setCreatedTime(curDate);
	            venueImage.setStatus("A");
	            venueImage.setVenueId(sportsVenue.getVenueId());
	            imageRepository.save(venueImage);
	        }
	    }
	    activeMqSender.send(savedVenue.getVenueId().toString(), Constant.JMS_QUEUE_VENUES);
		return savedVenue;
}

	@Override
	public Integer delete(SportsVenues sportsVenue) {
	    //只做逻辑删除
	    sportsVenue.setStatus("I");
		return repository.update(sportsVenue);
	}

	@Override
	public Integer update(SportsVenues sportsVenue) {
	    SportsVenues existesportsVenue = repository.get(sportsVenue.getVenueId());
	    User currentUser = CurrentUserHelper.getCurrentUser();
        Long userId = Long.valueOf(currentUser.getId().toString());
        Date curDate = new Date();
        
	    if(null != existesportsVenue) {
	        sportsVenue.setCreator(existesportsVenue.getCreator());
	        sportsVenue.setCreatedTime(existesportsVenue.getCreatedTime());
	        sportsVenue.setStatus(existesportsVenue.getStatus());
	        sportsVenue.setVenueChannel(existesportsVenue.getVenueChannel());
	    }
	    sportsVenue.setModifier(userId);
	    sportsVenue.setUpdatedTime(curDate);
	    
	    List<SportVenueImage> venueImages = sportsVenue.getVenueImages();
        if(null != venueImages) {
            for(SportVenueImage venueImage : venueImages) {
                if(null == venueImage.getImgId()) {
                    venueImage.setCreator(userId);
                    venueImage.setCreatedTime(curDate);
                    venueImage.setStatus("A");
                    venueImage.setVenueId(sportsVenue.getVenueId());
                    imageRepository.save(venueImage);
                }else {
                    SportVenueImage existVenueImage = imageRepository.get(venueImage.getImgId());
                    venueImage.setCreator(existVenueImage.getCreator());
                    venueImage.setCreatedTime(existVenueImage.getCreatedTime());
                    venueImage.setStatus(existVenueImage.getStatus());
                    venueImage.setVenueId(existVenueImage.getVenueId());
                    imageRepository.save(venueImage);
                }
            }
        }
        int result = repository.update(sportsVenue);
        activeMqSender.send(sportsVenue.getVenueId().toString(), Constant.JMS_QUEUE_VENUES);
		return result;
	}
	@Override
	public Page<SportsVenues> queryPage(Page<SportsVenues> page) {
		return repository.queryPage(page);
	}
}

