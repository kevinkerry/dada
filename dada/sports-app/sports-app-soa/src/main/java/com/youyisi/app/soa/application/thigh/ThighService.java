package com.youyisi.app.soa.application.thigh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.thigh.ThighRepository;
import com.youyisi.app.soa.remote.thigh.ThighServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.thigh.MyThigh;
import com.youyisi.sports.domain.thigh.Thigh;
import com.youyisi.sports.domain.thigh.ThighMoreInfo;
import com.youyisi.sports.domain.thigh.ThighRank;

/**
 * @author shuye
 * @time 2016-07-11
 */
@Service
public class ThighService implements ThighServiceRemote {

	@Autowired
	private ThighRepository repository;

	@Override
	public Thigh get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public Thigh save(Thigh entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(Thigh entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(Thigh entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<Thigh> queryPage(Page<Thigh> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public Thigh getThigh(Long activityId, Long userId) throws SoaException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activityId", activityId);
		map.put("userId", userId);
		map.put("status", 1);
		return repository.getByActivityIdAndUserId(map);
	}
	
	@Override
	public Thigh getByActivityIdAndUserId(Long activityId, Long userId,Long createTime) throws SoaException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activityId", activityId);
		map.put("userId", userId);
		map.put("createTime",createTime);
		return repository.getByActivityIdAndUserId(map);
	}

	@Override
	public List<ThighMoreInfo> getList(Long activityId) throws SoaException {
		return repository.getList(activityId);
	}

	@Override
	public Long thighCount(Long activityId,long time) throws SoaException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activityId", activityId);
		map.put("createTime",time);
		return repository.thighCount(map);
	}

	@Override
	public Page<MyThigh> queryPageMyThigh(Page<MyThigh> page)
			throws SoaException {
		// TODO Auto-generated method stub
		return repository.queryPageMyThigh(page);
	}

	@Override
	public List<ThighRank> getListThighRank(Page<ThighRank> page) throws SoaException{
		// TODO Auto-generated method stub
		return repository.getListThighRank(page);
	}
}

