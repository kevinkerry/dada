package com.youyisi.admin.application.relay.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.relay.RelayMemberFavourService;
import com.youyisi.admin.domain.relay.RelayMemberFavour;
import com.youyisi.admin.domain.relay.RelayMemberFavourRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-10
 */
@Service
public class RelayMemberFavourServiceImpl implements RelayMemberFavourService {

	@Autowired
	private RelayMemberFavourRepository repository;

	@Override
	public RelayMemberFavour get(Long id) {
		return repository.get(id);
	}

	@Override
	public RelayMemberFavour save(RelayMemberFavour entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(RelayMemberFavour entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(RelayMemberFavour entity) {
		return repository.update(entity);
	}

	@Override
	public Page<RelayMemberFavour> queryPage(Page<RelayMemberFavour> page) {
		return repository.queryPage(page);
	}

	@Override
	public Integer countNum(Long relayMemberId, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("relayMemberId", relayMemberId);
		map.put("type", type);
		return repository.countNum(map);
	}
}
