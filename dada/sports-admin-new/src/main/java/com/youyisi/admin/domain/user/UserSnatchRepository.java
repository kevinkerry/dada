package com.youyisi.admin.domain.user;

import java.util.List;
import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-09-22
 */
public interface UserSnatchRepository extends MybatisRepository<Long, UserSnatch> {

	List<UserSnatch> getByActivityId(Long activityId);

	Integer getUserSnatchCount(Map<String, Object> map);
}
