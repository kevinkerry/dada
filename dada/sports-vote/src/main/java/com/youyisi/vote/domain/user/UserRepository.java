package com.youyisi.vote.domain.user;

import com.youyisi.mybatis.MybatisRepository;

public interface UserRepository extends MybatisRepository<Long,User>{

	void updateVoteNum(User user);
}
