package com.youyisi.app.soa.infrastructure.persist.snatch;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.snatch.SnatchActivity;
import com.youyisi.sports.domain.snatch.SnatchActivityWithSnatchWinWithMore;

/**
 * @author shuye
 * @time 2016-09-21
 */
public interface SnatchActivityRepository extends MybatisRepository<Long, SnatchActivity> {

	Page<SnatchActivityWithSnatchWinWithMore> queryPageForHistory(
			Page<SnatchActivityWithSnatchWinWithMore> page);
}

