package com.youyisi.admin.domain.distance;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-06-02
 */
public interface DistanceRepository extends MybatisRepository<Long, Distance> {

	Double countDistance(Map<String, Object> map);

	Double countDistanceByUserId(Map<String, Object> map);

	Integer countDistanceNum(Map<String, Object> map);

	Double getTeamDistance(Map<String, Object> map);

	Double getUserDistance(Map<String, Object> map);
}
