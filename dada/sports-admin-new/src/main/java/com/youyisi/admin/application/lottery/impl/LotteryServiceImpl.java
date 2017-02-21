package com.youyisi.admin.application.lottery.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.bet.impl.BetCountServiceImpl;
import com.youyisi.admin.application.lottery.LotteryService;
import com.youyisi.admin.application.user.impl.UserLotteryServiceImpl;
import com.youyisi.admin.application.user.impl.UserLotteryWinServiceImpl;
import com.youyisi.admin.application.user.impl.UserServiceImpl;
import com.youyisi.admin.domain.lottery.Lottery;
import com.youyisi.admin.domain.lottery.LotteryRepository;
import com.youyisi.admin.domain.lottery.LotteryTemplate;
import com.youyisi.admin.domain.lottery.LotteryWin;
import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.domain.user.UserLottery;
import com.youyisi.admin.domain.user.UserLotteryWin;
import com.youyisi.admin.infrastructure.constant.Constant;
import com.youyisi.admin.infrastructure.helper.gexinpush.PushToSingleHelper;
import com.youyisi.admin.infrastructure.helper.gexinpush.TransmissionContent;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class LotteryServiceImpl implements LotteryService {

	@Autowired
	private LotteryRepository repository;

	@Autowired
	private UserLotteryServiceImpl userLotteryServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private LotteryWinServiceImpl lotteryWinServiceImpl;

	@Autowired
	private UserLotteryWinServiceImpl userLotteryWinServiceImpl;

	@Autowired
	private BetCountServiceImpl betCountServiceImpl;

	@Override
	public Lottery get(Long id) {
		return repository.get(id);
	}

	@Override
	public Lottery save(Lottery entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Lottery entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Lottery entity) {
		return repository.update(entity);
	}

	@Override
	public Page<Lottery> queryPage(Page<Lottery> page) {
		Page<Lottery> queryPage = repository.queryPage(page);
		List<Lottery> result = queryPage.getResult();
		for (Lottery lottery : result) {
			lottery.setLevel1(userLotteryWinServiceImpl.getLevelNumber(lottery.getId(), 1));
			lottery.setLevel2(userLotteryWinServiceImpl.getLevelNumber(lottery.getId(), 2));
			lottery.setLevel3(userLotteryWinServiceImpl.getLevelNumber(lottery.getId(), 3));
			lottery.setLevel4(userLotteryWinServiceImpl.getLevelNumber(lottery.getId(), 4));
			lottery.setLevel5(userLotteryWinServiceImpl.getLevelNumber(lottery.getId(), 5));

			Integer sumCount = betCountServiceImpl.sumCount(lottery.getId());
			if (sumCount == null) {
				sumCount = 0;
			}
			lottery.setSumCount(sumCount);
			lottery.setCountGoldBean(lottery.getGoldBean() * sumCount);
		}
		return queryPage;
	}

	private void pushLottery(User user, String lotteryNum, String content) {
		TransmissionContent tc = new TransmissionContent();
		Map<String, Object> param = new HashMap<String, Object>();
		tc.setTitle(lotteryNum);
		param.put("content", content);
		param.put("sendTime", System.currentTimeMillis());
		tc.setType(Constant.PUSH_SYS_WINNING);
		tc.setEntity(param);
		tc.setToUserId(user.getId());
		PushToSingleHelper.push(user, tc);
	}

	@Override
	public Integer runLottery(LotteryTemplate lotteryTemplate) {
		int row = 0;
		Lottery lottery = get(lotteryTemplate.getLotteryId());
		if (lottery.getEndTime() < System.currentTimeMillis()) {
			if (lottery.getSettle() == 0) {
				lottery.setSettle(-1);
				lottery.setWinNumber(lotteryTemplate.getWinNumber());

				List<UserLottery> userLotteryList = userLotteryServiceImpl
						.getUserLotteryList(lotteryTemplate.getLotteryId());
				List<LotteryWin> lotteryWinList = lotteryWinServiceImpl
						.getLotteryWinList(lotteryTemplate.getLotteryId());
				Integer winning = 0;
				Long winningId = null;
				UserLotteryWin userLotteryWin = null;
				User user = null;
				String content = null;
				UserLotteryWin ulw = null;
				for (UserLottery userLottery : userLotteryList) {
					ulw = userLotteryWinServiceImpl.getUserLotteryWinByUserIdAndLotteryId(userLottery.getUserId(),
							lottery.getId());
					if (ulw == null) {
						user = userServiceImpl.get(userLottery.getUserId());
						winning = isWinning(lottery.getWinNumber(), userLottery.getMyNumber());
						winningId = getWinningId(winning, lotteryWinList);
						if (winningId != null) {
							userLotteryWin = new UserLotteryWin();
							userLotteryWin.setCreateTime(System.currentTimeMillis());
							userLotteryWin.setExpiryTime(DateUtil.getDateForDay(lottery.getGetExpiryDay()) * 1000l);
							userLotteryWin.setStatus(0);
							userLotteryWin.setUpdateTime(System.currentTimeMillis());
							userLotteryWin.setLotteryId(lotteryTemplate.getLotteryId());
							userLotteryWin.setLotteryWinId(winningId);
							userLotteryWin.setUserId(userLottery.getUserId());
							userLotteryWinServiceImpl.save(userLotteryWin);
						}
						if (winning <= 5) {
							content = "恭喜您," + lottery.getLotteryNum() + "期开奖号码为:" + lottery.getWinNumber() + " 您获得了"
									+ convertNumber(winning) + "等奖,快去领取吧!";
						} else {
							content = "很遗憾," + lottery.getLotteryNum() + "期开奖号码为:" + lottery.getWinNumber() + " 本期您未中奖";
						}
						if (lotteryTemplate.getContent() != null) {
							pushLottery(user, lottery.getLotteryNum() + "开奖提示", lotteryTemplate.getContent());
						}
						pushLottery(user, lottery.getLotteryNum() + "开奖结果", content);
					}
				}
				row = update(lottery);
			}
		}
		return row;
	}

	private String convertNumber(Integer winning) {
		String str = null;
		if (winning == 1) {
			str = "一";
		}
		if (winning == 2) {
			str = "二";
		}
		if (winning == 3) {
			str = "三";
		}
		if (winning == 4) {
			str = "四";
		}
		if (winning == 5) {
			str = "五";
		}
		return str;
	}

	private Long getWinningId(Integer winning, List<LotteryWin> lotteryWinList) {
		if (winning <= 5) {
			for (LotteryWin lotteryWin : lotteryWinList) {
				if (lotteryWin.getLevel() == winning) {
					return lotteryWin.getId();
				}
			}
		}
		return null;
	}

	private Integer isWinning(String winNumber, String myNumber) {
		char[] win = winNumber.toCharArray();
		char[] my = myNumber.toCharArray();
		int winNum = 6;

		if (win.length == 5 && my.length == 5) {
			for (int i = 0; i < my.length; i++) {
				if (Integer.valueOf(my[i]) == Integer.valueOf(win[i])) {
					winNum -= 1;
				}
			}
		}
		return winNum;
	}

	@Override
	public Lottery getLotteryById(Long id) {
		Lottery lottery = get(id);
		lottery.setLevel1(userLotteryWinServiceImpl.getLevelNumber(lottery.getId(), 1));
		lottery.setLevel2(userLotteryWinServiceImpl.getLevelNumber(lottery.getId(), 2));
		lottery.setLevel3(userLotteryWinServiceImpl.getLevelNumber(lottery.getId(), 3));
		lottery.setLevel4(userLotteryWinServiceImpl.getLevelNumber(lottery.getId(), 4));
		lottery.setLevel5(userLotteryWinServiceImpl.getLevelNumber(lottery.getId(), 5));
		Integer sumCount = betCountServiceImpl.sumCount(lottery.getId());
		if (sumCount == null) {
			sumCount = 0;
		}
		lottery.setSumCount(sumCount);
		lottery.setCountGoldBean(lottery.getGoldBean() * sumCount);
		return lottery;
	}

}
