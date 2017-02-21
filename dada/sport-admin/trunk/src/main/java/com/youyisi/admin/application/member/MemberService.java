package com.youyisi.admin.application.member;

import com.youyisi.admin.domain.member.Member;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
public interface MemberService {
    
    Member save(Member entity);
    
    Member get(Long id);
    
    Integer delete(Member entity);
    
    Integer update(Member entity);
    
    Page<Member> queryPage(Page<Member> page);
    
    /**
     * 
     * @param userName
     * @return
     */
    Member getByUserId(Long userId);
    
    /**
     * @param member
     */
    void modify(Member member);
}

