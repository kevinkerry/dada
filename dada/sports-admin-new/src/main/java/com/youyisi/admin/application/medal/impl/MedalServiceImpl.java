package com.youyisi.admin.application.medal.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.activity.impl.ActivityServiceImpl;
import com.youyisi.admin.application.medal.MedalService;
import com.youyisi.admin.domain.medal.Medal;
import com.youyisi.admin.domain.medal.MedalRepository;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-07
 */
@Service
public class MedalServiceImpl implements MedalService {

	@Autowired
	private MedalRepository repository;

	@Autowired
	private ActivityServiceImpl activityServiceImpl;

	@Override
	public Medal get(Long id) {
		return repository.get(id);
	}

	@Override
	public Medal save(Medal entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Medal entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Medal entity) {
		return repository.update(entity);
	}

	@Override
	public Page<Medal> queryPage(Page<Medal> page) {
		Page<Medal> queryPage = repository.queryPage(page);
		List<Medal> result = queryPage.getResult();
		for (Medal medal : result) {
			medal.setCorrelationActivity(activityServiceImpl.getCorrelationActivityName(medal.getId()));
		}
		return queryPage;
	}

	@Override
	public List<Medal> getMedalList(Integer category, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (category != null) {
			map.put("category", category);
		}
		if (type != null) {
			map.put("type", type);
		}
		return repository.getMedalList(map);
	}

	@Override
	public Integer addMedal(Medal medal) {
		Integer row = 0;
		medal.setType(0);
		medal.setCategory(2);
		Medal save = repository.save(medal);
		if (save.getId() != null) {
			row = 1;
		}
		return row;
	}

	@Override
	public Integer delMedal(Long id) {
		Integer row = 0;
		row = repository.delMedalById(id);
		return row;
	}

	@Override
	public Integer updateMedal(Medal medal) {
		Integer row = 0;
		Medal ml = repository.get(medal.getId());
		ml.setLogo(medal.getLogo());
		ml.setNotLightLogo(medal.getNotLightLogo());
		ml.setName(medal.getName());
		ml.setNote(medal.getNote());
		ml.setType(medal.getType());
		ml.setCategory(medal.getCategory());
		row = repository.update(ml);
		return row;
	}
}
