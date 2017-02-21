package com.youyisi.app.soa.application.lottery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.distance.DistanceRepository;
import com.youyisi.app.soa.infrastructure.persist.lottery.LotteryRepository;
import com.youyisi.app.soa.infrastructure.persist.lottery.UserLotteryRepository;
import com.youyisi.app.soa.infrastructure.persist.lottery.UserLotteryWinRepository;
import com.youyisi.app.soa.infrastructure.persist.step.StepRepository;
import com.youyisi.app.soa.remote.lottery.LotteryServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.distance.Distance;
import com.youyisi.sports.domain.lottery.Lottery;
import com.youyisi.sports.domain.lottery.LotteryWithMore;
import com.youyisi.sports.domain.lottery.LotteryWithUserWinMore;
import com.youyisi.sports.domain.step.Step;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Service
public class LotteryService implements LotteryServiceRemote {

	@Autowired
	private LotteryRepository repository;
	@Autowired
	private StepRepository stepRepository;
	@Autowired
	private DistanceRepository distanceRepository;
	
	@Autowired
	private UserLotteryWinRepository userLotteryWinRepository;
	
	@Autowired
	private UserLotteryRepository userLotteryRepository;
	@Override
	public Lottery get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public Lottery save(Lottery entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(Lottery entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(Lottery entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<Lottery> queryPage(Page<Lottery> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public LotteryWithMore getByDate(Long dateForDay, User user)throws SoaException {
		LotteryWithMore lotteryWithMore = repository.getByDate(dateForDay);
		if(lotteryWithMore!=null){
			lotteryWithMore.setMyNumber(myNumber(user.getId(),dateForDay));
			lotteryWithMore.setWin(userLotteryWinRepository.getWinLevel(lotteryWithMore.getId(),user.getId()));
			lotteryWithMore.setUserLottery(userLotteryRepository.getUserLotteryCount(lotteryWithMore.getId(),user.getId()));
		}
		return lotteryWithMore;
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
	public Page<LotteryWithUserWinMore> queryPageForHistory(
			Page<LotteryWithUserWinMore> page) throws SoaException {
		Long userId = (Long) page.getParams().get("userId");
		page = repository.queryPageForHistory(page);
		List<LotteryWithUserWinMore> list = new ArrayList<LotteryWithUserWinMore>();
		for(LotteryWithUserWinMore l : page.getResult()){
			l.setMyNumber(myNumber(userId,l.getDate()));
			l.setWin(userLotteryWinRepository.getWinLevel(l.getId(),userId));
			l.setUserLottery(userLotteryRepository.getUserLotteryCount(l.getId(),userId));
			list.add(l);
		}
		return page.setResult(list);
	}
	
	
}

