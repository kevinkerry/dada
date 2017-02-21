package com.youyisi.app.soa.application.wallet;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.wallet.WalletDetailRepository;
import com.youyisi.app.soa.remote.wallet.WalletDetailServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.wallet.WalletDetail;
import com.youyisi.sports.domain.wallet.WalletDetailWithUser;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Service
@Transactional
public class WalletDetailService implements WalletDetailServiceRemote {

	@Autowired
	private WalletDetailRepository repository;

	@Override
	public WalletDetail get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public WalletDetail save(WalletDetail entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(WalletDetail entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(WalletDetail entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<WalletDetail> queryPage(Page<WalletDetail> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public Page<WalletDetailWithUser> queryPageRanklist(Page<WalletDetailWithUser> page) throws SoaException{
		return repository.queryPageRanklist(page);
	}

	@Override
	public WalletDetail getByUserIdAndDateAndType(Long userId, Long date) throws SoaException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date", date);
		map.put("userId", userId);
		return repository.getByUserIdAndDateAndType(map);
	}

	@Override
	public Long getMyRanking(Double money, Long date,Long id) throws SoaException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date", date);
		map.put("money", money);
		map.put("id", id);
		return repository.getMyRanking(map);
	}
	
	@Override
	public Double getSumByType(Integer type,Long userId) throws SoaException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		map.put("userId", userId);
		return repository.getSumByType(map);
	}

	@Override
	public Double getSumThighByDate(Long date,Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date", date);
		map.put("userId", userId);
		return repository.getSumThighByDate(map);
	}
}

