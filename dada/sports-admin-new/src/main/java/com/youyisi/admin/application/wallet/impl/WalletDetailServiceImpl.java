package com.youyisi.admin.application.wallet.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.activity.impl.ActivityServiceImpl;
import com.youyisi.admin.application.annual.impl.AnnualYieldServiceImpl;
import com.youyisi.admin.application.wallet.WalletDetailService;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.annual.AnnualYield;
import com.youyisi.admin.domain.wallet.WalletDetail;
import com.youyisi.admin.domain.wallet.WalletDetailRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Service
public class WalletDetailServiceImpl implements WalletDetailService {

	@Autowired
	private WalletDetailRepository repository;

	@Autowired
	private AnnualYieldServiceImpl annualYieldServiceImpl;

	@Autowired
	private ActivityServiceImpl activityServiceImpl;

	@Override
	public WalletDetail get(Long id) {
		return repository.get(id);
	}

	@Override
	public WalletDetail save(WalletDetail entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(WalletDetail entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(WalletDetail entity) {
		return repository.update(entity);
	}

	@Override
	public Page<WalletDetail> queryPage(Page<WalletDetail> page) {
		return repository.queryPage(page);
	}

	@Override
	public Double countWallet(Long beginTime, Long endTime, Integer walletType, Integer clientType, Integer result) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (beginTime != null && endTime != null) {
			map.put("beginTime", beginTime);
			map.put("endTime", endTime);
		}
		if (clientType != null) {
			map.put("clientType", clientType);
		}
		if (walletType != null) {
			map.put("walletType", walletType);
		}
		if (result != null) {
			map.put("result", result);
		}
		return repository.countWallet(map);
	}

	@Override
	public Page<WalletDetail> queryPageByUserId(Page<WalletDetail> page) {
		Page<WalletDetail> queryPageByUserId = repository.queryPageByUserId(page);
		List<WalletDetail> result = queryPageByUserId.getResult();
		for (WalletDetail wd : result) {
			if (wd.getType() == 3) {
				AnnualYield annualYield = annualYieldServiceImpl.getByUserAndDate(wd.getUserId(), wd.getDate());
				if (annualYield != null) {
					wd.setAnnualYield(annualYield.getAnnualYield());
				}
			}
		}
		return queryPageByUserId;
	}

	@Override
	public Integer haveSettlement(Map<String, Object> map) {

		return repository.haveSettlement(map);
	}

	@Override
	public Double getHugThighCommissionSum(Long activityId) {
		Activity activity = activityServiceImpl.get(activityId);
		if (activity != null) {
			return repository.getHugThighCommissionSum(activity.getDate());
		} else {
			return 0.0;
		}
	}

	@Override
	public List<WalletDetail> query(WalletDetail entity) {

		return repository.query(entity);
	}

	@Override
	public Double sumWalletByDateAndType(Long date, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (date != null) {
			map.put("date", date);
		}
		if (type != null) {
			map.put("type", type);
		}
		return repository.sumWalletByDateAndType(map);
	}

	@Override
	public Double sumWallet(Long userId, Long date, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId != null) {
			map.put("userId", userId);
		}
		if (date != null) {
			map.put("date", date);
		}
		if (type != null) {
			map.put("type", type);
		}
		return repository.sumWallet(map);
	}

	@Override
	public Double sumWalletByUserId(Long userId, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId != null) {
			map.put("userId", userId);
		}

		if (type != null) {
			map.put("type", type);
		}
		return repository.sumWalletByUserId(map);
	}

	@Override
	public Double getWithdrawBalance(Long userId, Double money, Long date, Long createTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId != null) {
			map.put("userId", userId);
		}
		if (date != null) {
			map.put("date", date);
		}
		if (money != null) {
			map.put("money", money);
		}
		if (createTime != null) {
			map.put("createTime", createTime);
		}
		return repository.getWithdrawBalance(map);
	}
}
