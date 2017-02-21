package com.youyisi.app.soa.application.lottery;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.application.coupon.UserCouponService;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.coupon.CouponRepository;
import com.youyisi.app.soa.infrastructure.persist.lottery.LotteryRepository;
import com.youyisi.app.soa.infrastructure.persist.lottery.UserLotteryRepository;
import com.youyisi.app.soa.infrastructure.persist.lottery.UserLotteryWinRepository;
import com.youyisi.app.soa.remote.lottery.UserLotteryWinServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.coupon.Coupon;
import com.youyisi.sports.domain.coupon.UserCoupon;
import com.youyisi.sports.domain.lottery.Lottery;
import com.youyisi.sports.domain.lottery.UserLotteryWin;
import com.youyisi.sports.domain.lottery.UserLotteryWinMore;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class UserLotteryWinService implements UserLotteryWinServiceRemote {

	@Autowired
	private UserLotteryWinRepository repository;
	@Autowired
	private LotteryRepository lotteryRepository;
	@Autowired
	private UserLotteryRepository userLotteryRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private UserCouponService userCouponService;
	@Override
	public UserLotteryWin get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public UserLotteryWin save(UserLotteryWin entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(UserLotteryWin entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(UserLotteryWin entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<UserLotteryWin> queryPage(Page<UserLotteryWin> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public UserLotteryWinMore getWin(Long userId) throws SoaException {
		return repository.getWin(System.currentTimeMillis(), userId);
	}

	@Override
	public void getLotteryWinCoupon(UserLotteryWinMore userLotteryWinMore)
			throws SoaException {
		Lottery lottery = lotteryRepository.get(userLotteryWinMore.getLotteryId());
		Integer count  = userLotteryRepository.getUserLotteryCount(lottery.getId(), userLotteryWinMore.getUserId());
		Coupon c = new Coupon();
		c.setBonus(ArithHelper.mul(userLotteryWinMore.getLotteryWin().getGoldBean(), count.doubleValue()));
		c.setExpiryDay(lottery.getBonusExpiryDay());
		c.setType(14);
		c.setActivityId(lottery.getId());
		c = couponRepository.save(c);
		UserCoupon entity = new UserCoupon();
		entity.setCouponId(c.getId());
		entity.setCreateTime(System.currentTimeMillis());
		entity.setDate(DateUtil.currentDateForDay());
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, c.getExpiryDay());
		entity.setExpiryTime(calendar.getTime().getTime());
		entity.setStatus(0);
		entity.setUpdateTime(System.currentTimeMillis());
		entity.setUserId(userLotteryWinMore.getUserId());
		entity.setCategory(6);
		userCouponService.save(entity);
		userLotteryWinMore.setStatus(1);
		repository.updateUserLotteryWinMore(userLotteryWinMore);
	}

	@Override
	public UserLotteryWinMore getUserLotteryWinMore(Long id)
			throws SoaException {
		
		return repository.getUserLotteryWinMore(id);
	}
}

