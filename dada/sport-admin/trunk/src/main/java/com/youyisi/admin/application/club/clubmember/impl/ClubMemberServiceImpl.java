package com.youyisi.admin.application.club.clubmember.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.club.clubmember.ClubMemberService;
import com.youyisi.admin.domain.club.clubmember.ClubMember;
import com.youyisi.admin.domain.club.clubmember.ClubMemberRepository;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Service
public class ClubMemberServiceImpl implements ClubMemberService {

	@Autowired
	private ClubMemberRepository repository;

	@Override
	public ClubMember get(Long id) {
		return repository.get(id);
	}

	@Override
	public ClubMember save(ClubMember clubMember) {
		return repository.save(clubMember);
}

	@Override
	public Integer delete(ClubMember clubMember) {
		return repository.delete(clubMember);
	}

	@Override
	public Integer update(ClubMember clubMember) {
		return repository.update(clubMember);
	}
	@Override
	public Page<ClubMember> queryPage(Page<ClubMember> page) {
		return repository.queryPage(page);
	}
}

