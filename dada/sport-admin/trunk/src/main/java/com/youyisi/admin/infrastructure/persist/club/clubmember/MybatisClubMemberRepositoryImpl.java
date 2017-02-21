package com.youyisi.admin.infrastructure.persist.club.clubmember;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.club.clubmember.ClubMember;
import com.youyisi.admin.domain.club.clubmember.ClubMemberRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Repository
public class MybatisClubMemberRepositoryImpl extends MybatisOperations<Long, ClubMember> implements ClubMemberRepository {
}

