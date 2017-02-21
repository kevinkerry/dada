package com.youyisi.admin.domain.activity;

import java.util.List;
import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-07-18
 */
public interface ActivityRepository extends MybatisRepository<Long, Activity> {

	Integer validateDate(Map<String, Object> map);

	Activity validateActivity(Map<String, Object> map);

	List<Activity> getCorrelationActivityName(Long medalId);

}
