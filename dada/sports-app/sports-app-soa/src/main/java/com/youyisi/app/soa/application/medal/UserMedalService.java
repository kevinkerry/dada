package com.youyisi.app.soa.application.medal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.medal.UserMedalRepository;
import com.youyisi.app.soa.remote.medal.UserMedalServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.medal.UserMedal;
import com.youyisi.sports.domain.medal.UserMedalWithMedal;

/**
 * @author shuye
 * @time 2016-09-05
 */
@Service
public class UserMedalService implements UserMedalServiceRemote {

	@Autowired
	private UserMedalRepository repository;

	@Override
	public UserMedal get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public UserMedal save(UserMedal entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(UserMedal entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(UserMedal entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<UserMedal> queryPage(Page<UserMedal> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public List<UserMedalWithMedal> getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return repository.getByUserId(userId);
	}
}

