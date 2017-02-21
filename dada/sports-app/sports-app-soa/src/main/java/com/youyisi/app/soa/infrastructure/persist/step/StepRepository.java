package com.youyisi.app.soa.infrastructure.persist.step;

import java.util.Map;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.step.Step;
import com.youyisi.sports.domain.step.StepWithUser;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface StepRepository extends MybatisRepository<Long, Step> {

	Step getByUserIdAndDate(Long userId);

	Page<StepWithUser> queryPageRanklist(Page<StepWithUser> page);

	Long getMyRanking(Map<String, Object> map);

	Step getByUserIdAndAnyDate(Map<String, Object> map);

	Integer getTeamStep(Map<String, Object> map);

	Integer getUserStep(Map<String, Object> map);
}

