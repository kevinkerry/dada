package com.youyisi.admin.application.lottery.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.bet.impl.BetCountBaseServiceImpl;
import com.youyisi.admin.application.lottery.LotteryBaseService;
import com.youyisi.admin.domain.bet.BetCountBase;
import com.youyisi.admin.domain.lottery.DelayLotteryBase;
import com.youyisi.admin.domain.lottery.LotteryBase;
import com.youyisi.admin.domain.lottery.LotteryBaseRepository;
import com.youyisi.admin.domain.lottery.LotteryWinBase;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class LotteryBaseServiceImpl implements LotteryBaseService {

	@Autowired
	private LotteryBaseRepository repository;

	@Autowired
	private DelayLotteryBaseServiceImpl delayLotteryBaseServiceImpl;

	@Autowired
	private LotteryWinBaseServiceImpl lotteryWinBaseServiceImpl;

	@Autowired
	private BetCountBaseServiceImpl betCountBaseServiceImpl;

	@Override
	public LotteryBase get(Long id) {
		return repository.get(id);
	}

	@Override
	public LotteryBase save(LotteryBase entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(LotteryBase entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(LotteryBase entity) {
		return repository.update(entity);
	}

	@Override
	public Page<LotteryBase> queryPage(Page<LotteryBase> page) {
		return repository.queryPage(page);
	}

	@Override
	public LotteryBase getLotteryBase() {

		return repository.getLotteryBase();
	}

	@Override
	public Integer addLotteryBase(LotteryBase lotteryBase) {
		int row = 0;
		if (lotteryBase.getId() != null) {
			delayLotteryBaseServiceImpl.deleteAll();
			lotteryWinBaseServiceImpl.deleteByLotteryBaseId(lotteryBase.getId());
			betCountBaseServiceImpl.deleteByLotteryBaseId(lotteryBase.getId());
			LotteryBase lb = get(lotteryBase.getId());
			lb.setBeginTime(lotteryBase.getBeginTime());
			lb.setBonusExpiryDay(lotteryBase.getBonusExpiryDay());
			lb.setCronExpression(DateUtil.dateTimeToCron(lotteryBase.getCronExpression()));
			lb.setEndTime(lotteryBase.getEndTime());
			lb.setGetExpiryDay(lotteryBase.getGetExpiryDay());
			lb.setGoldBean(lotteryBase.getGoldBean());
			lb.setLogo(lotteryBase.getLogo());
			lb.setMessage(lotteryBase.getMessage());
			lb.setRule(lotteryBase.getRule());
			lb.setUpdateTime(System.currentTimeMillis());
			row = update(lb);
			lotteryBase.setCronExpression(lb.getCronExpression());
		} else {
			lotteryBase.setCreateTime(System.currentTimeMillis());
			lotteryBase.setUpdateTime(System.currentTimeMillis());
			lotteryBase.setCronExpression(DateUtil.dateTimeToCron(lotteryBase.getCronExpression()));
			lotteryBase.setName("活动出彩");
			save(lotteryBase);
			row = 1;
		}

		if (lotteryBase.getId() != null) {
			List<BetCountBase> betCountBaseList = lotteryBase.getBetCountBaseList();
			for (BetCountBase betCountBase : betCountBaseList) {
				if (betCountBase.getCount() != null) {
					betCountBase.setLotteryBaseId(lotteryBase.getId());
					betCountBaseServiceImpl.save(betCountBase);
					row += 1;
				}
			}

			List<DelayLotteryBase> delayLotteryBaseList = lotteryBase.getDelayLotteryBaseList();
			for (DelayLotteryBase delayLotteryBase : delayLotteryBaseList) {
				if (delayLotteryBase.getLotteryNum() != null && delayLotteryBase.getLotteryTimeStr() != null) {
					delayLotteryBase.setLotteryTime(
							DateUtil.strToDate(delayLotteryBase.getLotteryTimeStr() + " 00:00:00").getTime());
					delayLotteryBaseServiceImpl.save(delayLotteryBase);
					row += 1;
				}
			}
			List<LotteryWinBase> lotteryWinBaseList = lotteryBase.getLotteryWinBaseList();
			for (LotteryWinBase lotteryWinBase : lotteryWinBaseList) {
				lotteryWinBase.setLotteryBaseId(lotteryBase.getId());
				lotteryWinBaseServiceImpl.save(lotteryWinBase);
				row += 1;
			}
		}
		return row;
	}

}
