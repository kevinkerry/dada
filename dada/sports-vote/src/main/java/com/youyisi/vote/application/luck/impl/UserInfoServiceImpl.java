package com.youyisi.vote.application.luck.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.lang.Page;
import com.youyisi.vote.application.luck.UserInfoService;
import com.youyisi.vote.domain.luck.UserInfo;
import com.youyisi.vote.domain.luck.UserInfoRepository;
/**
 * 
 * @author shuye
 *
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public void save(UserInfo user) {
		// TODO Auto-generated method stub
		userInfoRepository.save(user);
	}

	@Override
	public UserInfo get(Long id) {
		// TODO Auto-generated method stub
		return userInfoRepository.get(id);
	}

	@Override
	public void delete(UserInfo user) {
		// TODO Auto-generated method stub
		userInfoRepository.delete(user);
	}

	@Override
	public void update(UserInfo user) {
		// TODO Auto-generated method stub
		userInfoRepository.update(user);
	}

	@Override
	public Page<UserInfo> queryPage(Page<UserInfo> page) {
		// TODO Auto-generated method stub
		return userInfoRepository.queryPage(page);
	}

	@Override
	public UserInfo getByOpenId(String openid) {
		// TODO Auto-generated method stub
		return userInfoRepository.getByOpenId(openid);
	}
	
}
