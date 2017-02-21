package com.youyisi.app.soa.application.snatch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.activity.ActivityRepository;
import com.youyisi.app.soa.infrastructure.persist.medal.UserMedalRepository;
import com.youyisi.app.soa.infrastructure.persist.snatch.SnatchFeeRepository;
import com.youyisi.app.soa.infrastructure.persist.snatch.UserSnatchRepository;
import com.youyisi.app.soa.infrastructure.persist.wallet.WalletDetailRepository;
import com.youyisi.app.soa.infrastructure.persist.wallet.WalletRepository;
import com.youyisi.app.soa.remote.snatch.UserSnatchServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.medal.UserMedal;
import com.youyisi.sports.domain.orders.Orders;
import com.youyisi.sports.domain.snatch.ActivityWithSnatchActivity;
import com.youyisi.sports.domain.snatch.SnatchFee;
import com.youyisi.sports.domain.snatch.UserSnatch;
import com.youyisi.sports.domain.wallet.Wallet;
import com.youyisi.sports.domain.wallet.WalletDetail;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-09-21
 */
@Service
public class UserSnatchService implements UserSnatchServiceRemote {

	@Autowired
	private UserSnatchRepository repository;
	@Autowired
	private WalletDetailRepository walletDetailRepository;
	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private SnatchFeeRepository snatchFeeRepository;
	@Autowired
	private UserMedalRepository userMedalRepository;
	@Autowired
	private ActivityRepository activityRepository;
	@Override
	public UserSnatch get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public UserSnatch save(UserSnatch entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(UserSnatch entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(UserSnatch entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<UserSnatch> queryPage(Page<UserSnatch> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public Integer getCountByActivityId(Long activityId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activityId", activityId);
		return repository.getCountByActivityIdAndUserId(map);
	}

	@Override
	public Integer getCountByActivityIdAndUserId(Long activityId, Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activityId", activityId);
		map.put("userId", userId);
		return repository.getCountByActivityIdAndUserId(map);
	}

	@Override
	public int pay(UserSnatch us) {
		SnatchFee snatchFee = snatchFeeRepository.get(us.getSnatchFeeId());
		WalletDetail walletDetail = new WalletDetail();
		walletDetail.setCreateTime(System.currentTimeMillis());
		walletDetail.setDate(DateUtil.currentDateForDay());
		walletDetail.setMoney(snatchFee.getMoney());
		
		walletDetail.setType(9);
		walletDetail.setUserId(us.getUserId());
		
		
		Wallet wallet = walletRepository.getByUserId(us.getUserId());
		Double totalAsset = wallet.getTotalAsset();
		if(wallet.getTotalAsset()<walletDetail.getMoney()){
			return -1;
		}
		wallet.setTotalAsset(ArithHelper.sub(wallet.getTotalAsset(),walletDetail.getMoney()));
		if(wallet.getTotalAsset()<wallet.getPrincipal()){
			wallet.setPrincipal(wallet.getTotalAsset());
		}
		walletDetail.setResult(wallet.getTotalAsset());
		walletDetailRepository.save(walletDetail);
		walletRepository.updateByVersion(wallet, totalAsset);
		
		us.setPayStatus(1);
		us.setStatus(1);
		
		getUserMedal(us);
		
		repository.update(us);
		return 1;
	}

	public void getUserMedal(UserSnatch us) {
		ActivityWithSnatchActivity activity = activityRepository
				.getActivityWithSnatchActivityById(us.getActivityId());
		UserMedal um = new UserMedal();
		um.setCreateTime(System.currentTimeMillis());
		um.setUserId(us.getUserId());
		um.setMedalId(activity.getSnatchActivity().getActivityMedal());
		userMedalRepository.save(um);
	}
	@Override
	public void otherpay(Orders order) {
		UserSnatch userSnatch = repository.get(order.getProductId());
		getUserMedal(userSnatch);
		userSnatch.setPayStatus(1);
		userSnatch.setStatus(1);
		repository.update(userSnatch);
	}
}

