package com.youyisi.admin.infrastructure.persist.member;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.member.Member;
import com.youyisi.admin.domain.member.MemberRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
@Repository
public class MybatisMemberRepositoryImpl extends MybatisOperations<Long, Member> implements MemberRepository {

    @Override
    public Member getByUserId(Long userId) {
        
        return getSqlSession().selectOne(getNamespace().concat(".getByUserId"), userId);
    }
}

