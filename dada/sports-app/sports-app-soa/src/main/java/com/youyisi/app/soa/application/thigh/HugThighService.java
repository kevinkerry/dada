package com.youyisi.app.soa.application.thigh;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.coupon.CouponRepository;
import com.youyisi.app.soa.infrastructure.persist.coupon.UserCouponRepository;
import com.youyisi.app.soa.infrastructure.persist.thigh.HugThighRepository;
import com.youyisi.app.soa.infrastructure.persist.thigh.ThighRepository;
import com.youyisi.app.soa.infrastructure.persist.wallet.WalletDetailRepository;
import com.youyisi.app.soa.infrastructure.persist.wallet.WalletRepository;
import com.youyisi.app.soa.remote.thigh.HugThighServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.coupon.Coupon;
import com.youyisi.sports.domain.coupon.UserCoupon;
import com.youyisi.sports.domain.orders.Orders;
import com.youyisi.sports.domain.thigh.HugThigh;
import com.youyisi.sports.domain.thigh.HugThighWithUser;
import com.youyisi.sports.domain.thigh.Thigh;
import com.youyisi.sports.domain.wallet.Wallet;
import com.youyisi.sports.domain.wallet.WalletDetail;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-07-11
 */
@Service
public class HugThighService implements HugThighServiceRemote {

	@Autowired
	private HugThighRepository repository;
	@Autowired
	private WalletDetailRepository walletDetailRepository;
	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private ThighRepository thighRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private UserCouponRepository userCouponRepository;
	@Override
	public HugThigh get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public HugThigh save(HugThigh entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(HugThigh entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(HugThigh entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<HugThigh> queryPage(Page<HugThigh> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public Integer getHugThighCount(Long thighId, long createTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("thighId", thighId);
		map.put("createTime", createTime);
		return repository.getHugThighCount(map);
	}

	@Override
	public HugThigh getByUserAndThighId(Long userId, Long thighId,
			long createTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("thighId", thighId);
		map.put("userId", userId);
		map.put("createTime", createTime);
		return repository.getByUserAndThighId(map);
	}

	@Override
	public int pay(HugThigh hugThigh) throws SoaException {
		Thigh thigh = thighRepository.get(hugThigh.getThighId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activityId", hugThigh.getActivityId());
		map.put("type", thigh.getType());
		Coupon coupon = couponRepository.getByActivityIdAndType(map);
		
		
		WalletDetail hugThighWalletDetail = new WalletDetail();
		hugThighWalletDetail.setCreateTime(System.currentTimeMillis());
		hugThighWalletDetail.setDate(DateUtil.currentDateForDay());
		hugThighWalletDetail.setMoney(coupon.getCommission());
		hugThighWalletDetail.setType(6);
		hugThighWalletDetail.setUserId(hugThigh.getUserId());
		
		
		Wallet hugThighWallet = walletRepository.getByUserId(hugThigh.getUserId());
		if(hugThighWallet.getTotalAsset()<hugThighWalletDetail.getMoney()){
			return -1;
		}
		hugThighWallet.setTotalAsset(ArithHelper.sub(hugThighWallet.getTotalAsset(),hugThighWalletDetail.getMoney()));
		if(hugThighWallet.getTotalAsset()<hugThighWallet.getPrincipal()){
			hugThighWallet.setPrincipal(hugThighWallet.getTotalAsset());
		}
		hugThighWalletDetail.setResult(hugThighWallet.getTotalAsset());
		walletDetailRepository.save(hugThighWalletDetail);
		walletRepository.update(hugThighWallet);
		
		
		
		thighGetMoney(thigh, coupon);
		
		createUserCoupon(hugThigh, coupon);
		
		hugThigh.setStatus(1);
		hugThigh.setPayStatus(1);
		repository.update(hugThigh);
		
		return 1;
	}

	private void thighGetMoney(Thigh thigh, Coupon coupon) {
		WalletDetail walletDetail = new WalletDetail();
		walletDetail.setCreateTime(System.currentTimeMillis());
		walletDetail.setDate(DateUtil.currentDateForDay());
		walletDetail.setMoney(ArithHelper.mul(coupon.getCommission(), coupon.getCommissionRate()));
		walletDetail.setType(5);
		walletDetail.setUserId(thigh.getUserId());
		
		
		Wallet wallet = walletRepository.getByUserId(thigh.getUserId());
		wallet.setTotalAsset(ArithHelper.add(wallet.getTotalAsset(),walletDetail.getMoney()));
		wallet.setIncome(ArithHelper.add(wallet.getIncome(),walletDetail.getMoney()));
		
		walletDetail.setResult(wallet.getTotalAsset());
		walletDetailRepository.save(walletDetail);
		walletRepository.update(wallet);
	}

	private void createUserCoupon(HugThigh hugThigh, Coupon coupon) {
		UserCoupon entity = new UserCoupon();
		entity.setCreateTime(System.currentTimeMillis());
		entity.setDate(DateUtil.currentDateForDay());
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, coupon.getExpiryDay());
		entity.setExpiryTime(calendar.getTime().getTime());
		entity.setStatus(0);
		entity.setCategory(0);
		entity.setUserId(hugThigh.getUserId());
		entity.setCouponId(coupon.getId());
		userCouponRepository.save(entity);
	}

	@Override
	public List<HugThighWithUser> getListByThighId(Long thighId)
			throws SoaException {
		// TODO Auto-generated method stub
		return repository.getListByThighId(thighId);
	}

	@Override
	public void otherPay(Orders order) throws SoaException {
		
		HugThigh hugThigh = repository.get(order.getProductId());
		Thigh thigh = thighRepository.get(hugThigh.getThighId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activityId", hugThigh.getActivityId());
		map.put("type", thigh.getType());
		Coupon coupon = couponRepository.getByActivityIdAndType(map);
		
		thighGetMoney(thigh, coupon);
		
		createUserCoupon(hugThigh, coupon);
		
		hugThigh.setStatus(1);
		hugThigh.setPayStatus(1);
		repository.update(hugThigh);
	}

	@Override
	public Integer getByUserIdAndThighId(Long userId, Long thighId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("thighId", thighId);
		return repository.getByUserIdAndThighId(map);
	}

	@Override
	public Integer getCount(Long userId, Long activityId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activityId", activityId);
		map.put("userId", userId);
		return repository.getCount(map);
	}
}

