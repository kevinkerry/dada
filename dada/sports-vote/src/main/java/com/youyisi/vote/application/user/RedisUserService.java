package com.youyisi.vote.application.user;

import java.util.Map;

import com.youyisi.vote.domain.user.User;

public interface RedisUserService {

	void save(String key, User value);

	User get(String key);
	
	Map<Object,Object> hget(String key);
	
	User getById(Long userId);
	
	void removeByUserId(Long userId);
	
	void remove(String key);
	
	String getTokenById(Long userId);

	void lpush(Long userId);

	String rightPop();

}
