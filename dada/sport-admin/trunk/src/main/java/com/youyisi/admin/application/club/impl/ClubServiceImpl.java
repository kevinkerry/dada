package com.youyisi.admin.application.club.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.club.ClubService;
import com.youyisi.admin.domain.club.Club;
import com.youyisi.admin.domain.club.ClubRepository;
import com.youyisi.admin.infrastructure.constant.Constant;
import com.youyisi.admin.infrastructure.message.ActiveMqSender;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Service
public class ClubServiceImpl implements ClubService {

	@Autowired
	private ClubRepository repository;
	
	@Resource
	private ActiveMqSender activeMqSender;

	@Override
	public Club get(Long id) {
		return repository.get(id);
	}

	@Override
	public Club save(Club club) {
	    //club.setCreator(-1000l);
	    club.setStatus("A");
	    club.setCreatedTime(new Date());
	    Club savedClub = repository.save(club);
	    activeMqSender.send(savedClub.getClubId().toString(),Constant.JMS_QUEUE_CLUB);
		return savedClub;
	}

	@Override
	public Integer delete(Club club) {
		return repository.delete(club);
	}

	@Override
	public Integer update(Club club) {
	    Club existedClub = repository.get(club.getClubId());
	    if(null != existedClub) {
	        club.setActiveIndex(existedClub.getActiveIndex());
	        club.setClubCode(existedClub.getClubCode());
	        club.setClubLevel(existedClub.getClubLevel());
	        club.setClubOwner(existedClub.getClubOwner());
	        club.setStatus(existedClub.getStatus());
	        club.setCreator(existedClub.getCreator());
	        club.setModifier(-1000l);
	        club.setUpdatedTime(new Date());
	        club.setRecommendFlag(existedClub.getRecommendFlag());
	    }
	    int result = repository.update(club);
	    activeMqSender.send(club.getClubId().toString(),Constant.JMS_QUEUE_CLUB);
		return result;
	}
	
	
	@Override
	public Page<Club> queryPage(Page<Club> page) {
		return repository.queryPage(page);
	}

    @Override
    public Integer modify(Club club) {
        int result = repository.update(club);
        activeMqSender.send(club.getClubId().toString(),Constant.JMS_QUEUE_CLUB);
        return result;
    }
}

