package com.youyisi.vote.infrastructure.persist.user;

import org.springframework.stereotype.Repository;

import com.youyisi.vote.domain.user.User;
import com.youyisi.vote.infrastructure.cache.redis.RedisOperations;

/**
 * A data access object (DAO) providing persistence and search support for
 * TUserInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.chuanli.zhuizhui.model.user.TUserInfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class RedisUserRepository extends RedisOperations<String,User>{
	
}