package com.youyisi.admin.infrastructure.persist.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.domain.user.UserInvite;
import com.youyisi.admin.domain.user.UserRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-05-16
 */
@Repository
public class MybatisUserRepositoryImpl extends MybatisOperations<Long, User> implements UserRepository {

	@Override
	public Page<User> queryPageUserList(Page<User> page) {
		List<User> selectList = getSqlSession().selectList(getNamespace().concat(".queryPageUserList"), page);
		page.setResult(selectList);
		return page;
	}

	@Override
	public Integer countUser(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".countUser"), map);
	}

	@Override
	public Page<User> queryPagePushUser(Page<User> page) {
		List<User> selectList = getSqlSession().selectList(getNamespace().concat(".queryPagePushUser"), page);
		page.setResult(selectList);
		return page;
	}

	@Override
	public Page<User> queryPageForPush(Page<User> page) {
		List<User> selectList = getSqlSession().selectList(getNamespace().concat(".queryPageForPush"), page);
		page.setResult(selectList);
		return page;
	}

	@Override
	public Integer countClientIdNum(String clientId) {

		return getSqlSession().selectOne(getNamespace().concat(".countClientIdNum"), clientId);
	}

	@Override
	public List<User> getClientIdAndClientIdNum() {

		return getSqlSession().selectList(getNamespace().concat(".getClientIdAndClientIdNum"));
	}

	@Override
	public Page<UserInvite> queryPageUserInvite(Page<UserInvite> page) {
		List<UserInvite> selectList = getSqlSession().selectList(getNamespace().concat(".queryPageUserInvite"), page);
		page.setResult(selectList);
		return page;
	}

	@Override
	public Integer countInviteNum(Long activityId) {

		return getSqlSession().selectOne(getNamespace().concat(".countInviteNum"), activityId);
	}

	@Override
	public Page<UserInvite> queryPageUserInviteDetail(Page<UserInvite> page) {
		List<UserInvite> selectList = getSqlSession().selectList(getNamespace().concat(".queryPageUserInviteDetail"),
				page);
		page.setResult(selectList);
		return page;
	}
}
