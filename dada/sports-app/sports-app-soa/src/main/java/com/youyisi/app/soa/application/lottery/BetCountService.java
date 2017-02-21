package com.youyisi.app.soa.application.lottery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.lottery.BetCountRepository;
import com.youyisi.app.soa.remote.lottery.BetCountServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.lottery.BetCount;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class BetCountService implements BetCountServiceRemote {

	@Autowired
	private BetCountRepository repository;

	@Override
	public BetCount get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public BetCount save(BetCount entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(BetCount entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(BetCount entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<BetCount> queryPage(Page<BetCount> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public List<BetCount> getByLotteryId(Long lotteryId) throws SoaException {
		// TODO Auto-generated method stub
		return repository.getByLotteryId(lotteryId);
	}
}

