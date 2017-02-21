package com.youyisi.app.soa.infrastructure.persist.user.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.user.UserRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.user.UserLessInfo;
import com.youyisi.sports.domain.user.UserMoreInfo;

/**
 * @author shuye
 * @time 2016-05-10
 */
@Repository
public class MybatisUserRepositoryImpl extends MybatisOperations<Long, User> implements UserRepository {

	@Override
	public User getByUserName(String userName) {
		return getSqlSession().selectOne(getNamespace().concat(".getByUserName"), userName);
	}

	@Override
	public UserMoreInfo login(String userName) {
		return getSqlSession().selectOne(getNamespace().concat(".login"), userName);
	}

	@Override
	public UserMoreInfo saveUserMoreInfo(UserMoreInfo userMoreInfo) {
		getSqlSession().insert(getNamespace().concat(".saveUserMoreInfo"), userMoreInfo);
		return userMoreInfo;
	}

	@Override
	public UserMoreInfo updateUserMoreInfo(UserMoreInfo userMoreInfo) {
		getSqlSession().insert(getNamespace().concat(".updateUserMoreInfo"), userMoreInfo);
		return userMoreInfo;
	}

	@Override
	public User getByUserNameOrMobile(String userName) {
		return getSqlSession().selectOne(getNamespace().concat(".getByUserNameOrMobile"), userName);
	}

	@Override
	public User getByClientId(String clientId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByClientId"), clientId);
	}

	@Override
	public User getByUsercode(String usercode) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUsercode"), usercode);
	}

	@Override
	public Page<UserLessInfo> queryPageForPush(Page<UserLessInfo> page) {
		// TODO Auto-generated method stub
		List<UserLessInfo> result = getSqlSession().selectList(getNamespace().concat(".queryPageForPush"), page);
		page.setResult(result);
		return page;
	}
}
