package com.youyisi.app.soa.infrastructure.persist.relay.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.relay.RelayMessageRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.relay.RelayMessage;
import com.youyisi.sports.domain.user.UserLessInfo;

/**
 * @author shuye
 * @time 2016-10-08
 */
@Repository
public class MybatisRelayMessageRepositoryImpl extends MybatisOperations<Long, RelayMessage> implements RelayMessageRepository {

	@Override
	public List<UserLessInfo> newUser(Long teamId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".newUser"), teamId);
	}

	@Override
	public Integer totalCount(Long teamId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".totalCount"), teamId);
	}

	@Override
	public RelayMessage newMessage(Long teamId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".newMessage"), teamId);
	}
}

