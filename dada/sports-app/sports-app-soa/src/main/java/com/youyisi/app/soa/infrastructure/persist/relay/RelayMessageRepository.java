package com.youyisi.app.soa.infrastructure.persist.relay;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.relay.RelayMessage;
import com.youyisi.sports.domain.user.UserLessInfo;

/**
 * @author shuye
 * @time 2016-10-08
 */
public interface RelayMessageRepository extends MybatisRepository<Long, RelayMessage> {

	List<UserLessInfo> newUser(Long teamId);

	Integer totalCount(Long teamId);

	RelayMessage newMessage(Long teamId);
}

