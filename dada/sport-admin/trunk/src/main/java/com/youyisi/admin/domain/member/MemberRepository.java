package com.youyisi.admin.domain.member;

import com.youyisi.mybatis.MybatisRepository;

/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
public interface MemberRepository extends MybatisRepository<Long, Member> {
    
    /**
     * 
     * @param userName
     * @return
     */
    Member getByUserId(Long userId);
}

