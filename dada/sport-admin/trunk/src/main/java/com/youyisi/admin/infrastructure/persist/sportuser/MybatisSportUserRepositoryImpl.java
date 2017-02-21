package com.youyisi.admin.infrastructure.persist.sportuser;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.sportuser.SportUser;
import com.youyisi.admin.domain.sportuser.SportUserRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
@Repository
public class MybatisSportUserRepositoryImpl extends MybatisOperations<Long, SportUser> implements SportUserRepository {

    @Override
    public SportUser getByUsername(String userName) {
        
        return getSqlSession().selectOne(getNamespace().concat(".getByUserName"), userName);
    }
}

