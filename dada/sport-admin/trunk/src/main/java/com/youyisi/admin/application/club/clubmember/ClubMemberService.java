package com.youyisi.admin.application.club.clubmember;

import com.youyisi.admin.domain.club.clubmember.ClubMember;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
public interface ClubMemberService {

	ClubMember save(ClubMember clubMember);

	ClubMember get(Long id);

	Integer delete(ClubMember clubMember);

	Integer update(ClubMember clubMember);

	Page<ClubMember> queryPage(Page<ClubMember> page);

}

