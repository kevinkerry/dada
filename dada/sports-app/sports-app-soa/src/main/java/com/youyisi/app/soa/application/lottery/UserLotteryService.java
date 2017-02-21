package com.youyisi.app.soa.application.lottery;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.distance.DistanceRepository;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanDetailRepository;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanRepository;
import com.youyisi.app.soa.infrastructure.persist.lottery.BetCountRepository;
import com.youyisi.app.soa.infrastructure.persist.lottery.LotteryRepository;
import com.youyisi.app.soa.infrastructure.persist.lottery.UserLotteryRepository;
import com.youyisi.app.soa.infrastructure.persist.step.StepRepository;
import com.youyisi.app.soa.remote.lottery.UserLotteryServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.distance.Distance;
import com.youyisi.sports.domain.goldbean.GoldBean;
import com.youyisi.sports.domain.goldbean.GoldBeanDetail;
import com.youyisi.sports.domain.lottery.BetCount;
import com.youyisi.sports.domain.lottery.Lottery;
import com.youyisi.sports.domain.lottery.UserLottery;
import com.youyisi.sports.domain.step.Step;
import com.youyisi.sports.utils.ArithHelper;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class UserLotteryService implements UserLotteryServiceRemote {

	@Autowired
	private UserLotteryRepository repository;
	@Autowired
	private GoldBeanRepository goldBeanRepository;
	@Autowired
	private BetCountRepository betCountRepository;
	@Autowired
	private LotteryRepository lotteryRepository;
	@Autowired
	private GoldBeanDetailRepository goldBeanDetailRepository;
	@Autowired
	private StepRepository stepRepository;
	@Autowired
	private DistanceRepository distanceRepository;
	@Override
	public UserLottery get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public UserLottery save(UserLottery entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(UserLottery entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(UserLottery entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<UserLottery> queryPage(Page<UserLottery> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public int add(UserLottery userLottery) throws SoaException {
		Lottery lottery = lotteryRepository.get(userLottery.getLotteryId());
        if(lottery.getEndTime()<System.currentTimeMillis()){
        	return -2;
		}
        GoldBean goldBean = goldBeanRepository.getByUserId(userLottery.getUserId());
		BetCount betCount = betCountRepository.get(userLottery.getBetCountId());
		Double totalNeed = ArithHelper.mul(lottery.getGoldBean(), betCount.getCount().doubleValue());
		if(totalNeed>goldBean.getGoldBean()){
			return -1;
		}
		
		
		goldBean.setGoldBean(ArithHelper.sub(goldBean.getGoldBean(), totalNeed));
		
		GoldBeanDetail entity = new GoldBeanDetail();
		entity.setCategory(2);
		entity.setCreateTime(System.currentTimeMillis());
		entity.setDate(DateUtil.currentDateForDay());
		entity.setGoldBean(totalNeed);
		entity.setNote("运动就出彩投注");
		
		entity.setResult(goldBean.getGoldBean());
		entity.setType(-1);
		entity.setUserId(userLottery.getUserId());
		
		goldBeanRepository.update(goldBean);
		userLottery.setMyNumber(myNumber(userLottery.getUserId(),DateUtil.currentDateForDay()));
		userLottery.setStatus(1);
		userLottery.setPayStatus(1);
		userLottery = repository.save(userLottery);
		
		entity.setProductId(userLottery.getId());
		goldBeanDetailRepository.save(entity);
		
		return 1;
	}
	
	
	private String myNumber(Long userId,Long date) {
		int first = 0;
		 Double yestodayDistance = getYestodayDistance(userId,DateUtil.getDateForDay(date*1000l,-1));
		if(yestodayDistance>0.0){
			first = yestodayDistance.intValue();
		}
		String _first = first+"";
		_first = _first.substring(_first.length()-1);
		Integer yestodayStep = getYestodayStep(userId,DateUtil.getDateForDay(date*1000l,-1));
		String other = yestodayStep.toString();
		if(other.length()>=4){
			other = other.substring(other.length()-4);
		}else{
			other = String.format("%04d", yestodayStep); 
		}
		return _first+other;
	}
	
	private Integer getYestodayStep(Long userId,Long date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("date", date);
		
		Step s = stepRepository.getByUserIdAndAnyDate(map);
		if(s==null){
			return 0;
		}else{
			return s.getStep();
		}
	}
	
	private Double getYestodayDistance(Long userId,Long date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("date", date);
		Distance yestodayDistance = distanceRepository.getByUserIdAndEnyDate(map);
		if(yestodayDistance==null){
			return 0.0;
		}else{
			return yestodayDistance.getDistance();
		}
	}

	@Override
	public Integer getUserLotteryCount(Long lotteryId, Long userId) {
		// TODO Auto-generated method stub
		return repository.getUserLotteryCount(lotteryId,userId) ;
	}
}

