package com.youyisi.app.soa.infrastructure.persist.snatch;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.snatch.UserSnatch;

/**
 * @author shuye
 * @time 2016-09-21
 */
public interface UserSnatchRepository extends MybatisRepository<Long, UserSnatch> {

	Integer getCountByActivityIdAndUserId(Map<String, Object> map);
}

