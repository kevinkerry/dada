package com.youyisi.admin.application.medal;

import java.util.List;

import com.youyisi.admin.domain.medal.Medal;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-07
 */
public interface MedalService {

	Medal save(Medal entity);

	Medal get(Long id);

	Integer delete(Medal entity);

	Integer update(Medal entity);

	Page<Medal> queryPage(Page<Medal> page);

	List<Medal> getMedalList(Integer category, Integer type);

	Integer addMedal(Medal medal);

	Integer delMedal(Long id);

	Integer updateMedal(Medal medal);

}
