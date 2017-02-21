package com.youyisi.app.soa.infrastructure.persist.snatch;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.snatch.SnatchWin;
import com.youyisi.sports.domain.snatch.SnatchWinWithMore;

/**
 * @author shuye
 * @time 2016-09-21
 */
public interface SnatchWinRepository extends MybatisRepository<Long, SnatchWin> {

	List<SnatchWinWithMore> winResult(Long activityId);

	SnatchWin getByUserId(Long userId);
	
}

