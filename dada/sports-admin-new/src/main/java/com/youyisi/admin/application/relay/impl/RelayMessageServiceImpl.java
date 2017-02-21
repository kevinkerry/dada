package com.youyisi.admin.application.relay.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.relay.RelayMessageService;
import com.youyisi.admin.domain.relay.RelayMessage;
import com.youyisi.admin.domain.relay.RelayMessageRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-10
 */
@Service
public class RelayMessageServiceImpl implements RelayMessageService {

	@Autowired
	private RelayMessageRepository repository;

	@Override
	public RelayMessage get(Long id) {
		return repository.get(id);
	}

	@Override
	public RelayMessage save(RelayMessage entity) {
		return repository.save(entity);
}

	@Override
	public Integer delete(RelayMessage entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(RelayMessage entity) {
		return repository.update(entity);
	}
	@Override
	public Page<RelayMessage> queryPage(Page<RelayMessage> page) {
		return repository.queryPage(page);
	}
}

