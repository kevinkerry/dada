package com.youyisi.admin.interfaces.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.youyisi.admin.application.bet.BetCountBaseService;
import com.youyisi.admin.application.bet.BetCountService;
import com.youyisi.admin.application.lottery.DelayLotteryBaseService;
import com.youyisi.admin.application.lottery.LotteryBaseService;
import com.youyisi.admin.application.lottery.LotteryService;
import com.youyisi.admin.application.lottery.LotteryWinBaseService;
import com.youyisi.admin.application.lottery.LotteryWinService;
import com.youyisi.admin.domain.bet.BetCount;
import com.youyisi.admin.domain.bet.BetCountBase;
import com.youyisi.admin.domain.lottery.DelayLotteryBase;
import com.youyisi.admin.domain.lottery.Lottery;
import com.youyisi.admin.domain.lottery.LotteryBase;
import com.youyisi.admin.domain.lottery.LotteryWin;
import com.youyisi.admin.domain.lottery.LotteryWinBase;
import com.youyisi.admin.infrastructure.helper.quartz.ActivityQuartzJobLottery;
import com.youyisi.admin.infrastructure.helper.quartz.MyJob;
import com.youyisi.admin.infrastructure.helper.quartz.QuartzManager;
import com.youyisi.admin.infrastructure.utils.DateUtil;

/**
 * @author shuye
 * @time 2013-5-24
 */
public class LotteryBaseTask {

	@Autowired
	private LotteryBaseService LotteryBaseService;

	@Autowired
	private LotteryService LotteryService;

	@Autowired
	private LotteryWinBaseService lotteryWinBaseService;

	@Autowired
	private LotteryWinService lotteryWinService;

	@Autowired
	private BetCountBaseService betCountBaseService;

	@Autowired
	private BetCountService betCountService;

	@Autowired
	private DelayLotteryBaseService delayLotteryBaseService;

	public void lottery() {
		LotteryBase lotteryBase = LotteryBaseService.getLotteryBase();
		if (lotteryBase != null) {
			Lottery lottery = new Lottery();
			lottery.setBeginTime(DateUtil.todayDateAddTime(lotteryBase.getBeginTime()));
			lottery.setEndTime(DateUtil.todayDateAddTime(lotteryBase.getEndTime()));
			lottery.setLotteryNum(DateUtil.dateTimeToDate(lottery.getBeginTime()));
			lottery.setDate(DateUtil.currentDateForDay());
			lottery.setLogo(lotteryBase.getLogo());
			lottery.setGoldBean(lotteryBase.getGoldBean());
			lottery.setGetExpiryDay(lotteryBase.getGetExpiryDay());
			lottery.setBonusExpiryDay(lotteryBase.getBonusExpiryDay());
			lottery.setRule(lotteryBase.getRule());
			lottery.setMessage(lotteryBase.getMessage());
			lottery.setCronExpression(lotteryBase.getCronExpression());
			lottery.setCreateTime(System.currentTimeMillis());
			lottery.setUpdateTime(System.currentTimeMillis());
			lottery.setSettle(0);
			lottery.setName("运动出彩" + lottery.getLotteryNum());

			DelayLotteryBase delayLotteryBase = delayLotteryBaseService
					.getDelayLotteryBaseByLotteryNum(lottery.getLotteryNum());
			if (delayLotteryBase != null) {
				lottery.setLotteryTime(delayLotteryBase.getLotteryTime());
			} else {
				lottery.setLotteryTime(DateUtil.getDateForDay(1) * 1000);
			}
			LotteryService.save(lottery);

			List<LotteryWinBase> lotteryWinBaseList = lotteryWinBaseService.getLotteryWinBaseList(lotteryBase.getId());
			LotteryWin lotteryWin;
			for (LotteryWinBase lotteryWinBase : lotteryWinBaseList) {
				lotteryWin = new LotteryWin();
				lotteryWin.setGoldBean(lotteryWinBase.getGoldBean());
				lotteryWin.setLevel(lotteryWinBase.getLevel());
				lotteryWin.setLotteryId(lottery.getId());
				lotteryWinService.save(lotteryWin);
			}

			List<BetCountBase> betCountBaseList = betCountBaseService.getBetCountBaseList(lotteryBase.getId());
			BetCount betCount;
			for (BetCountBase betCountBase : betCountBaseList) {
				betCount = new BetCount();
				betCount.setCount(betCountBase.getCount());
				betCount.setLotteryId(lottery.getId());
				betCountService.save(betCount);
			}

			addPushJob(LotteryService.get(46l));
		}

	}

	public void addPushJob(Lottery lottery) {
		MyJob job = new MyJob();
		Long timestamp = DateUtil.cronToTimestamp(lottery.getCronExpression());
		if (timestamp < System.currentTimeMillis()) {
			String dateTimeToCron = DateUtil.dateTimeToCron(DateUtil.timestampToStrDateTime(lottery.getBeginTime()));
			lottery.setCronExpression(dateTimeToCron);
		}
		job.setCronExpression(lottery.getCronExpression());
		job.setJobGroup("activity");
		job.setJobName("lottery" + lottery.getId() + "activitywillbegin");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", lottery.getMessage());
		params.put("content", lottery.getMessage());
		job.setParams(params);
		QuartzManager.addJob(job, ActivityQuartzJobLottery.class);
	}

}
