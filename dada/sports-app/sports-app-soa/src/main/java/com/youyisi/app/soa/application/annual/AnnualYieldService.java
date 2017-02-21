package com.youyisi.app.soa.application.annual;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.app.soa.application.coupon.UserCouponService;
import com.youyisi.app.soa.application.experience.ExperienceAccountService;
import com.youyisi.app.soa.application.wallet.WalletService;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.annual.AnnualYieldDetailRepository;
import com.youyisi.app.soa.infrastructure.persist.annual.AnnualYieldRepository;
import com.youyisi.app.soa.remote.annual.AnnualYieldServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.annual.AnnualYield;
import com.youyisi.sports.domain.annual.AnnualYieldDetail;
import com.youyisi.sports.domain.annual.AnnualYieldWithRun;
import com.youyisi.sports.domain.annual.AnnualYieldWithWalletDetail;
import com.youyisi.sports.domain.coupon.UserCouponWithCoupon;
import com.youyisi.sports.domain.experience.ExperienceAccount;
import com.youyisi.sports.domain.run.Run;
import com.youyisi.sports.domain.step.Step;
import com.youyisi.sports.domain.wallet.Wallet;
import com.youyisi.sports.domain.wallet.WalletDetail;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Service
@Transactional
public class AnnualYieldService implements AnnualYieldServiceRemote {

	@Autowired
	private AnnualYieldRepository repository;
	@Autowired
	private AnnualYieldDetailRepository annualYieldDetailRepository;
	@Autowired
	private WalletService walletService;
	@Autowired
	private ExperienceAccountService experienceAccountService;
	@Autowired
	private UserCouponService userCouponService;
	@Override
	public AnnualYield get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public AnnualYield save(AnnualYield entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(AnnualYield entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(AnnualYield entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<AnnualYield> queryPage(Page<AnnualYield> page) throws SoaException{
		return repository.queryPage(page);
	}
	

	public AnnualYield updateByStep(Step entity) throws SoaException {
		AnnualYield annualYield = repository.getByUserIdAndDate(entity.getUserId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date", DateUtil.currentDateForDay());
		map.put("userId", entity.getUserId());
		if(entity.getStep()>=20000){
			map.put("type", 4);
			AnnualYieldDetail annualYieldDetail = annualYieldDetailRepository.getCurrentDay(map);
			if(annualYieldDetail==null){
				annualYieldDetail = new AnnualYieldDetail();
				annualYieldDetail.setUserId(entity.getUserId());
				annualYieldDetail.setType(4);
				annualYieldDetail.setAnnualYieldIncrease(0.5);
				annualYieldDetail.setCreateTime(System.currentTimeMillis());
				annualYieldDetail.setDate(DateUtil.currentDateForDay());
				annualYieldDetailRepository.save(annualYieldDetail);
				annualYield = updateAnnualYield(entity, annualYield, annualYieldDetail);
			}
		} 
		if(entity.getStep()>=15000){
			map.put("type", 3);
			AnnualYieldDetail annualYieldDetail = annualYieldDetailRepository.getCurrentDay(map);
			if(annualYieldDetail==null){
			annualYieldDetail = new AnnualYieldDetail();
			annualYieldDetail.setUserId(entity.getUserId());
			annualYieldDetail.setType(3);
			annualYieldDetail.setAnnualYieldIncrease(0.5);
			annualYieldDetail.setCreateTime(System.currentTimeMillis());
			annualYieldDetail.setDate(DateUtil.currentDateForDay());
			annualYieldDetailRepository.save(annualYieldDetail);
			annualYield =  updateAnnualYield(entity, annualYield, annualYieldDetail);
			}
		}
		if(entity.getStep()>=10000){
			map.put("type", 2);
			AnnualYieldDetail annualYieldDetail = annualYieldDetailRepository.getCurrentDay(map);
			if(annualYieldDetail==null){
				annualYieldDetail = new AnnualYieldDetail();
				annualYieldDetail.setUserId(entity.getUserId());
				annualYieldDetail.setType(2);
				annualYieldDetail.setAnnualYieldIncrease(0.5);
				annualYieldDetail.setCreateTime(System.currentTimeMillis());
				annualYieldDetail.setDate(DateUtil.currentDateForDay());
				annualYieldDetailRepository.save(annualYieldDetail);
				annualYield = updateAnnualYield(entity, annualYield, annualYieldDetail);
			}
		} 
		if(entity.getStep()>=5000){
			map.put("type", 1);
			AnnualYieldDetail annualYieldDetail = annualYieldDetailRepository.getCurrentDay(map);
			if(annualYieldDetail==null){
				annualYieldDetail = new AnnualYieldDetail();
				annualYieldDetail.setUserId(entity.getUserId());
				annualYieldDetail.setType(1);
				annualYieldDetail.setAnnualYieldIncrease(0.5);
				annualYieldDetail.setDate(DateUtil.currentDateForDay());
				annualYieldDetail.setCreateTime(System.currentTimeMillis());
				annualYieldDetailRepository.save(annualYieldDetail);
				annualYield =  updateAnnualYield(entity, annualYield, annualYieldDetail);
			}
		}
		return annualYield;
	}

	private AnnualYield updateAnnualYield(Step entity, AnnualYield annualYield,
			AnnualYieldDetail annualYieldDetail) throws SoaException {
		if(annualYield==null){
			annualYield = new AnnualYield();
			annualYield.setBaseAnnualYield(getBaseAnnualYield(entity.getUserId()));
			annualYield.setCreateTime(System.currentTimeMillis());
			annualYield.setDate(DateUtil.currentDateForDay());
			annualYield.setUpdateTime(System.currentTimeMillis());
			annualYield.setUserId(annualYieldDetail.getUserId());
			annualYield.setAnnualYield(ArithHelper.add(annualYield.getBaseAnnualYield(), annualYieldDetail.getAnnualYieldIncrease()));
			repository.save(annualYield);
		}else{
			annualYield.setAnnualYield(ArithHelper.add(annualYield.getAnnualYield(), annualYieldDetail.getAnnualYieldIncrease()));
			repository.update(annualYield);
		}
		return annualYield;
	}

	
	private AnnualYield updateAnnualYield(Run entity, AnnualYield annualYield,
			AnnualYieldDetail annualYieldDetail,Double sum) throws SoaException {
		if(annualYield==null){
			annualYield = new AnnualYield();
			annualYield.setBaseAnnualYield(getBaseAnnualYield(entity.getUserId()));
			annualYield.setCreateTime(System.currentTimeMillis());
			annualYield.setDate(DateUtil.currentDateForDay());
			annualYield.setUpdateTime(System.currentTimeMillis());
			annualYield.setUserId(annualYieldDetail.getUserId());
			annualYield.setAnnualYield(ArithHelper.add(annualYield.getBaseAnnualYield(),annualYieldDetail.getAnnualYieldIncrease()));
			repository.save(annualYield);
		}else{
			if(ArithHelper.add(sum,annualYieldDetail.getAnnualYieldIncrease())<=3.0){
				annualYield.setAnnualYield(ArithHelper.add(annualYield.getAnnualYield(),annualYieldDetail.getAnnualYieldIncrease()));
			}else{
				annualYieldDetail.setAnnualYieldIncrease(ArithHelper.sub(3.0, sum));
				annualYield.setAnnualYield(ArithHelper.add(annualYield.getAnnualYield(),annualYieldDetail.getAnnualYieldIncrease()));
			}
			repository.update(annualYield);
		}
		annualYieldDetailRepository.save(annualYieldDetail);
		return annualYield;
	}

	private Double getBaseAnnualYield(Long userId) throws SoaException {
		Wallet wallet = walletService.getByUserId(userId);
		if (wallet == null) {
			wallet = new Wallet();
			wallet.setUserId(userId);
			wallet.setTotalAsset(0.0);
			wallet.setPrincipal(0.0);
			wallet.setIncome(0.0);
			walletService.save(wallet);
		}
		if (wallet.getPrincipal() == null || wallet.getPrincipal() < 100.0) {
			return 4.0;
		}
		return 6.0;
	}

	public AnnualYield updateByRun(Run run) throws SoaException {
		AnnualYield annualYield = repository.getByUserIdAndDate(run.getUserId());
		if(run.getDistance()>=3.0){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", run.getUserId());
		map.put("date", DateUtil.currentDateForDay());
		Double sum = annualYieldDetailRepository.getSumCurrentDayDistance(map);
		if(sum==null||sum<3.0){
			if(sum==null){
				sum=0.0;
			}
			map.put("runId", run.getId());
			AnnualYieldDetail annualYieldDetail = annualYieldDetailRepository.getCurrentDay(map);
			if(annualYieldDetail==null){
				annualYieldDetail = new AnnualYieldDetail();
				annualYieldDetail.setUserId(run.getUserId());
				annualYieldDetail.setRunId(run.getId());
				annualYieldDetail = getAnnualYieldDetail(annualYieldDetail,run);
				annualYieldDetail.setCreateTime(System.currentTimeMillis());
				annualYieldDetail.setDate(DateUtil.currentDateForDay());
				annualYield = updateAnnualYield(run, annualYield, annualYieldDetail, sum);
			}
			return annualYield;
		}else{
			return annualYield;
		}
		}
		return annualYield;
	}

	private AnnualYieldDetail getAnnualYieldDetail(AnnualYieldDetail annualYieldDetail, Run runningTrack) {
		if(runningTrack.getDistance()>=10.0){
			annualYieldDetail.setType(8);
			annualYieldDetail.setAnnualYieldIncrease(3.0);
		}else if(runningTrack.getDistance()>=8.0){
			annualYieldDetail.setType(7);
			annualYieldDetail.setAnnualYieldIncrease(2.0);
		}else if(runningTrack.getDistance()>=5.0){
			annualYieldDetail.setType(6);
			annualYieldDetail.setAnnualYieldIncrease(1.0);
		}else if(runningTrack.getDistance()>=3.0){
			annualYieldDetail.setType(5);
			annualYieldDetail.setAnnualYieldIncrease(0.5);
		}
		return annualYieldDetail;
	}

	@Override
	public AnnualYield getByUserIdAndDate(Long userId) throws SoaException {

		return repository.getByUserIdAndDate(userId);
	}

	@Override
	public Page<AnnualYieldWithWalletDetail> queryPageHistory(Page<AnnualYieldWithWalletDetail> page)
			throws SoaException {
		// TODO Auto-generated method stub
		return repository.queryPageHistory(page);
	}

	@Override
	public WalletDetail predictFortune(Long userId,
			AnnualYieldWithWalletDetail annualYieldWithWalletDetail)
			throws SoaException {
		Wallet wallet = walletService.getByUserId(userId);
		ExperienceAccount experienceAccount = experienceAccountService.getByUserId(userId);
		Double principal = wallet.getPrincipal();
		if (experienceAccount != null) {
			if (experienceAccount.getExpiryTime() > DateUtil.getTime()) {
				if (wallet != null) {
					principal = ArithHelper.add(wallet.getPrincipal(), experienceAccount.getMoney());
				}
			}
		}
		WalletDetail walletDetail = new WalletDetail();
		UserCouponWithCoupon userCoupon = userCouponService.getUsingWithCoupon(userId, DateUtil.currentDateForDay());
		if(annualYieldWithWalletDetail.getActivityAnnualYield()!=null&&annualYieldWithWalletDetail.getActivityAnnualYield()>0.0&&userCoupon!=null){
			principal = ArithHelper.add(principal, userCoupon.getCoupon().getBonus());
			walletDetail.setMoney(ArithHelper.div(ArithHelper.mul(principal, annualYieldWithWalletDetail.getActivityAnnualYield()), 36500.0, 2));
		}else{
			walletDetail.setMoney(ArithHelper.div(ArithHelper.mul(principal, annualYieldWithWalletDetail.getAnnualYield()), 36500.0, 2));
			
		}
		return walletDetail;
	}
	
	public WalletDetail predictFortune(Long userId,
			AnnualYieldWithRun annualYieldWithWalletDetail)
			throws SoaException {
		Wallet wallet = walletService.getByUserId(userId);
		ExperienceAccount experienceAccount = experienceAccountService.getByUserId(userId);
		Double principal = wallet.getPrincipal();
		if (experienceAccount != null) {
			if (experienceAccount.getExpiryTime() > DateUtil.getTime()) {
				if (wallet != null) {
					principal = ArithHelper.add(wallet.getPrincipal(), experienceAccount.getMoney());
				}
			}
		}
		WalletDetail walletDetail = new WalletDetail();
		UserCouponWithCoupon userCoupon = userCouponService.getUsingWithCoupon(userId, DateUtil.currentDateForDay());
		if(annualYieldWithWalletDetail.getActivityAnnualYield()!=null&&annualYieldWithWalletDetail.getActivityAnnualYield()>0.0&&userCoupon!=null){
			principal = ArithHelper.add(principal, userCoupon.getCoupon().getBonus());
			walletDetail.setMoney(ArithHelper.div(ArithHelper.mul(principal, annualYieldWithWalletDetail.getActivityAnnualYield()), 36500.0, 2));
		}else{
			walletDetail.setMoney(ArithHelper.div(ArithHelper.mul(principal, annualYieldWithWalletDetail.getAnnualYield()), 36500.0, 2));
			
		}
		return walletDetail;
	}

	@Override
	public AnnualYieldWithRun getDetailWithRun(Long id) throws SoaException {
		AnnualYieldWithRun annualYieldWithRun =  repository.getAnnualYieldWithRunById(id);
		return annualYieldWithRun;
	}

	@Override
	public Page<AnnualYieldWithRun> queryPageDetailWithRun(Page<AnnualYieldWithRun> page) throws SoaException {
		return repository.queryPageDetailWithRun(page);
	}

	@Override
	public AnnualYield getByUserIdDate(Long userId, Long date) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("date", date);
		return repository.getByUserIdDate(map);
	}

}

