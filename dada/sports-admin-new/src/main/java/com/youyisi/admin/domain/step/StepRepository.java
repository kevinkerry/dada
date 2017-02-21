package com.youyisi.admin.domain.step;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-06-02
 */
public interface StepRepository extends MybatisRepository<Long, Step> {

	Integer countStep(Map<String, Object> map);

	Integer countStepByUserId(Map<String, Object> map);

	Integer countStepNum(Map<String, Object> map);

	Integer getTeamStep(Map<String, Object> map);

	Integer getUserStep(Map<String, Object> map);

	Step sumStep(Map<String, Object> map);
}
