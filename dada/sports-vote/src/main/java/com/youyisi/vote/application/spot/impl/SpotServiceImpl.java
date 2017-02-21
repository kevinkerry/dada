package com.youyisi.vote.application.spot.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.vote.application.spot.SpotService;
import com.youyisi.vote.domain.spot.Spot;
import com.youyisi.vote.domain.spot.SpotRepository;
import com.youyisi.vote.infrastructure.cache.redis.RedisClient;
import com.youyisi.vote.infrastructure.constant.Constant;
import com.youyisi.vote.infrastructure.util.DateUtil;
import com.youyisi.vote.infrastructure.util.SpotHelper;
/**
 * 
 * @author shuye
 *
 */
@Service
@Transactional
public class SpotServiceImpl implements SpotService {
	@Autowired
	private SpotRepository spotRepository;
	int probabilityA = Integer.parseInt(Constant.probabilityA);
	int probabilityB = Integer.parseInt(Constant.probabilityB);
	 Long startTimeA = DateUtil.getLongDate(Constant.startTimeA, "yyyy-MM-dd HH:mm:ss");
	 Long endTimeA = DateUtil.getLongDate(Constant.endTimeA, "yyyy-MM-dd HH:mm:ss");
	 Long startTimeB = DateUtil.getLongDate(Constant.startTimeB, "yyyy-MM-dd HH:mm:ss");
	 Long endTimeB = DateUtil.getLongDate(Constant.endTimeB, "yyyy-MM-dd HH:mm:ss");
	

	@Override
	public String lucky(String name, String mobile) {
		Spot s = new Spot();
		s.setName(name);
		s.setMobile(mobile);
		Long currentTime = System.currentTimeMillis();
		s.setCreateTime(currentTime);
		Spot spot = spotRepository.getByMobile(mobile);
		
		if(currentTime<startTimeA){
			return "anostart";
		}else if(currentTime<endTimeA){
			return spotA(s, spot);
			
		}else if(currentTime<startTimeB){
			return "aend_bnostart";
		}else if(currentTime<endTimeB){
			return spotB(s, spot);
			
		}else{
			return "aend_bend";
		}
		
	}


	private String spotB(Spot s, Spot spot) {
		Integer numb = Integer.parseInt((String) RedisClient.get("spot:spotB:Num")) ;
		if(numb>0){
		if(SpotHelper.spot(probabilityB)){
			RedisClient.increment("spot:spotB:Num", -1l);
			s.setSpotB("B");
		}else{
			s.setSpotB("noneB");
		}
		}else{
			s.setSpotB("noneB");
		}
		if(spot==null){
			spotRepository.save(s);
			return s.getSpotB();
		}else if(StringUtils.isBlank(spot.getSpotB())) {
			spot.setSpotB(s.getSpotB());
			spotRepository.save(spot);
			return spot.getSpotB();
		}else{
			return "havespotB";
		}
	}


	private String spotA(Spot s, Spot spot) {
		Integer numa = Integer.parseInt((String) RedisClient.get("spot:spotA:Num")) ;
		if(numa>0){
			if(SpotHelper.spot(probabilityA)){
				RedisClient.increment("spot:spotA:Num", -1l);
				s.setSpotA("A");
			}else{
				s.setSpotA("noneA");
			}
		}else{
			s.setSpotA("noneA");
		}
		
		if(spot==null){
			spotRepository.save(s);
			return s.getSpotA();
		}else {
			return "havespotA";
		}
	}


	@Override
	public List<Spot> queryAllSpot() {
		// TODO Auto-generated method stub
		return spotRepository.queryAllSpot();
	}

}
