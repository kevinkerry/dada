package com.youyisi.vote.domain.luck;

import com.youyisi.mybatis.MybatisRepository;

public interface UserInfoRepository extends MybatisRepository<Long,UserInfo>{

	UserInfo getByOpenId(String openid);
}
