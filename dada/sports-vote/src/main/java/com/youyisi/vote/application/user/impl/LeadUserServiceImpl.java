package com.youyisi.vote.application.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.lang.Page;
import com.youyisi.vote.application.user.LeadUserService;
import com.youyisi.vote.domain.user.LeadUser;
import com.youyisi.vote.domain.user.LeadUserRepository;
/**
 * 
 * @author shuye
 *
 */
@Service
@Transactional
public class LeadUserServiceImpl implements LeadUserService {

	@Autowired
	private LeadUserRepository leadUserRepository;
	@Override
	public Page<LeadUser> queryPage(Page<LeadUser> page) {
		return leadUserRepository.queryPage(page);
	}
	@Override
	public void save(LeadUser user) {
		leadUserRepository.save(user);
	}
	@Override
	public LeadUser get(Long id) {
		return leadUserRepository.get(id);
	}
	@Override
	public void delete(LeadUser user) {
		leadUserRepository.delete(user);
	}
	@Override
	public void update(LeadUser user) {
		leadUserRepository.update(user);
	}
	
}
