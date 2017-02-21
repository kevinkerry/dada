package com.youyisi.admin.domain.run;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-06-27
 */
public interface RunRepository extends MybatisRepository<Long, Run> {

	Integer countRunByTotalTime(Map<String, Object> map);

	Integer countRunByType(Map<String, Object> map);

	Run getMaxRun(Map<String, Object> map);

	Run sumDistance(Map<String, Object> map);
}
