package com.youyisi.app.soa.application.relay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.relay.RelayMessageRepository;
import com.youyisi.app.soa.remote.relay.RelayMessageServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.relay.RelayMessage;
import com.youyisi.sports.domain.user.UserLessInfo;

/**
 * @author shuye
 * @time 2016-10-08
 */
@Service
public class RelayMessageService implements RelayMessageServiceRemote {

	@Autowired
	private RelayMessageRepository repository;

	@Override
	public RelayMessage get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public RelayMessage save(RelayMessage entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(RelayMessage entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(RelayMessage entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<RelayMessage> queryPage(Page<RelayMessage> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public List<UserLessInfo> newUser(Long teamId) {
		// TODO Auto-generated method stub
		return repository.newUser(teamId);
	}

	@Override
	public Integer totalCount(Long teamId) {
		// TODO Auto-generated method stub
		return repository.totalCount(teamId);
	}

	@Override
	public RelayMessage newMessage(Long teamId) {
		// TODO Auto-generated method stub
		return repository.newMessage(teamId);
	}
}

