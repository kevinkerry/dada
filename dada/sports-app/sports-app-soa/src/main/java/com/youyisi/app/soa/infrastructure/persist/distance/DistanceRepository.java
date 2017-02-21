package com.youyisi.app.soa.infrastructure.persist.distance;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.distance.Distance;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface DistanceRepository extends MybatisRepository<Long, Distance> {

	Distance getByUserIdAndDate(Long userId);

	Double getSumDistance(Long userId);

	Double getTeamDistance(Map<String, Object> map);

	Double getUserDistance(Map<String, Object> map);

	Distance getByUserIdAndEnyDate(Map<String, Object> map);
}

