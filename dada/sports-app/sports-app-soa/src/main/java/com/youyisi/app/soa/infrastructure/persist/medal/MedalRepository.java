package com.youyisi.app.soa.infrastructure.persist.medal;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.medal.Medal;

/**
 * @author shuye
 * @time 2016-09-05
 */
public interface MedalRepository extends MybatisRepository<Long, Medal> {

	List<Medal> getNoHaveByUserId(Long userId);
}

