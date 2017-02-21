package com.youyisi.vote.application.user.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.vote.application.user.RedisUserService;
import com.youyisi.vote.domain.user.User;
import com.youyisi.vote.infrastructure.persist.user.RedisUserRepository;

/**
 * @author shuye
 * @time 2014-7-9
 */
@Service
public class RedisUserServiceImpl implements RedisUserService {
	@Autowired
	private RedisUserRepository redisUserRepository;
	public static final String USER_KEY = "vote:user:";

	@Override
	public void save(String key, User value) {
		// TODO Auto-generated method stub
		redisUserRepository.save(USER_KEY + key, value);
	}

	@Override
	public User get(String key) {
		// TODO Auto-generated method stub
		return redisUserRepository.get(USER_KEY + key);
	}
	@Override
	public Map<Object,Object> hget(String key) {
		Map<Object,Object> map = redisUserRepository.hget(USER_KEY + key);
		map.remove("Password");
		return map;
	}

	@Override
	public void remove(String key) {
		// TODO Auto-generated method stub
		redisUserRepository.remove(USER_KEY + key);
	}

	@Override
	public User getById(Long userId) {
		List<User> users = redisUserRepository.getList(USER_KEY + userId + "_*");
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public void removeByUserId(Long userId) {
		// TODO Auto-generated method stub
		redisUserRepository.removeByPattern(USER_KEY + userId+ "_*");
	}

	@Override
	public String getTokenById(Long userId) {
		// TODO Auto-generated method stub
		Set<String> keys = redisUserRepository.getKeyByPattern(USER_KEY + userId+ "_*");
		if(keys!=null&&keys.size()>0){
			return keys.iterator().next();
		}
		return null;
	}

	@Override
	public void lpush(Long userId) {
		// TODO Auto-generated method stub
		redisUserRepository.lpush(USER_KEY+"list",userId+"");
	}
	
	@Override
	public String rightPop() {
		// TODO Auto-generated method stub
		return redisUserRepository.rightPop(USER_KEY+"list");
	}

}
