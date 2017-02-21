package com.youyisi.admin.interfaces.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.youyisi.admin.application.lottery.LotteryService;
import com.youyisi.admin.application.user.UserLotteryWinService;
import com.youyisi.admin.domain.lottery.Lottery;
import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.domain.user.UserLotteryWin;
import com.youyisi.admin.infrastructure.constant.Constant;
import com.youyisi.admin.infrastructure.helper.gexinpush.PushToSingleHelper;
import com.youyisi.admin.infrastructure.helper.gexinpush.TransmissionContent;
import com.youyisi.admin.infrastructure.utils.DateUtil;

/**
 * @author shuye
 * @time 2013-5-24
 */
public class ExpiryTask {

	@Autowired
	private UserLotteryWinService userLotteryWinService;

	@Autowired
	private LotteryService lotteryService;

	public void expiry() {
		Long dateForDayTime = DateUtil.getDateForDayTime(1);
		List<UserLotteryWin> userLotteryWinList = userLotteryWinService.getUserLotteryWinList(dateForDayTime,
				dateForDayTime + 86399000l);
		int i = 0;
		Lottery lottery = null;
		for (UserLotteryWin userLotteryWin : userLotteryWinList) {
			if (i == 0) {
				lottery = lotteryService.get(userLotteryWin.getLotteryId());
				i = 1;
			}

			if (lottery != null) {
				pushMessage(userLotteryWin.getUser(), lottery);
			}

		}
	}

	private void pushMessage(final User user, final Lottery lottery) {
		TransmissionContent content = new TransmissionContent();
		content.setTitle("领奖提示");
		Map<String, Object> entity = new HashMap<String, Object>();
		entity.put("content", lottery.getLotteryNum() + "期运动就出彩,奖金已经下发到您账号,快去领奖吧!");
		entity.put("sendTime", System.currentTimeMillis());
		content.setType(Constant.PUSH_SYS_OVERDUE);
		content.setEntity(entity);
		content.setToUserId(user.getId());
		PushToSingleHelper.push(user, content);
	}

}
