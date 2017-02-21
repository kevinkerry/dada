package com.youyisi.admin.infrastructure.persist.user;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.user.Auth;
import com.youyisi.admin.domain.user.AuthRepository;
import com.youyisi.mybatis.MybatisOperations;


@Repository
public class MybatisAuthRepositoryImpl extends MybatisOperations<Integer, Auth> implements AuthRepository {

    @Override
    public boolean isExistAuth(Auth auth) {
        Long count = getSqlSession().selectOne(getNamespace().concat(".count"), auth);
        if(count>0){
            return true;
        }
        return false;
    }


}
