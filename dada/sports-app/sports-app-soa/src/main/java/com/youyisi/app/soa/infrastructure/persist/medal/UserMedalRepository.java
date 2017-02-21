package com.youyisi.app.soa.infrastructure.persist.medal;

import java.util.List;
import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.medal.UserMedal;
import com.youyisi.sports.domain.medal.UserMedalWithMedal;

/**
 * @author shuye
 * @time 2016-09-05
 */
public interface UserMedalRepository extends MybatisRepository<Long, UserMedal> {

	List<UserMedalWithMedal> getByUserId(Long userId);

	UserMedal getByUserIdAndTypeCategory(Map<String, Object> map);
}

