package com.youyisi.app.soa.application.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.lottery.LotteryWinServiceRemote;
import com.youyisi.sports.domain.lottery.LotteryWin;
import com.youyisi.app.soa.infrastructure.persist.lottery.LotteryWinRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class LotteryWinService implements LotteryWinServiceRemote {

	@Autowired
	private LotteryWinRepository repository;

	@Override
	public LotteryWin get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public LotteryWin save(LotteryWin entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(LotteryWin entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(LotteryWin entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<LotteryWin> queryPage(Page<LotteryWin> page) throws SoaException{
		return repository.queryPage(page);
	}
}

