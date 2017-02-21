package com.youyisi.vote.infrastructure.persist.luck;

import org.springframework.stereotype.Repository;

import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.vote.domain.luck.UserInfo;
import com.youyisi.vote.domain.luck.UserInfoRepository;
/**
 * 
 * @author shuye
 *
 */
@Repository
public class MybatisUserInfoRepository extends MybatisOperations<Long,UserInfo> implements UserInfoRepository {

	@Override
	public UserInfo getByOpenId(String openid) {
			// TODO Auto-generated method stub
			 return getSqlSession().selectOne(getNamespace().concat(".getByOpenId"),openid);
		}

}
