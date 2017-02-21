package com.youyisi.app.soa.application.step;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.app.soa.application.annual.AnnualYieldService;
import com.youyisi.app.soa.application.user.UserService;
import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.step.StepRepository;
import com.youyisi.app.soa.remote.step.StepServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.step.Step;
import com.youyisi.sports.domain.step.StepWithUser;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.user.UserMoreInfo;
import com.youyisi.sports.utils.DateUtil;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Service
@Transactional
public class StepService implements StepServiceRemote {

	@Autowired
	private StepRepository repository;
	@Autowired
	private UserService userService;

	@Autowired
	private AnnualYieldService annualYieldService;

	@Override
	public Step get(Long id) throws SoaException {
		return repository.get(id);
	}

	@Override
	public Step save(Step entity) throws SoaException {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Step entity) throws SoaException {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Step entity) throws SoaException {
		return repository.update(entity);
	}

	@Override
	public UserMoreInfo updateStep(Step entity, User user) throws SoaException {
		Long date = DateUtil.currentDateForDay();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("date", date);
		map.put("userId", entity.getUserId());
		Step s = repository.getByUserIdAndAnyDate(map);
		if (s==null) {
			entity.setCreateTime(System.currentTimeMillis());
			entity.setUpdateTime(System.currentTimeMillis());
			entity.setDate(date);
			entity.setStatus(0);
			repository.save(entity);
			annualYieldService.updateByStep(entity);
			return userService.getMyUserInfo(user);
		}
		if(entity.getStep()>s.getStep()&&s.getStatus()!=null&&s.getStatus()!=-1){
			if(s.getStatus()==null){
				s.setStatus(0);
			}
			s.setStep(entity.getStep());
			s.setUpdateTime(System.currentTimeMillis());
			if(s.getStatus().intValue()!=-1){
				repository.update(s);
				annualYieldService.updateByStep(entity);
			}
			
		}
		return userService.getMyUserInfo(user);
	}
	@Override
	public Page<Step> queryPage(Page<Step> page) throws SoaException {
		return repository.queryPage(page);
	}

	@Override
	public Page<StepWithUser> queryPageRanklist(Page<StepWithUser> page) throws SoaException {
		// TODO Auto-generated method stub
		return repository.queryPageRanklist(page);
	}

	@Override
	public Long getMyRanking(Integer step,Long id) throws SoaException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("step", step);
		return repository.getMyRanking(map);
	}

	@Override
	public Step getByUserIdAndDate(Long userId) throws SoaException {
		return repository.getByUserIdAndDate(userId);
	}
}
