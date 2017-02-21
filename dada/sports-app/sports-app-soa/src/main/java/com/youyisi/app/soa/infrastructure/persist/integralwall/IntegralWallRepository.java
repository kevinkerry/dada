package com.youyisi.app.soa.infrastructure.persist.integralwall;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.integralwall.IntegralWall;

/**
 * @author shuye
 * @time 2016-08-09
 */
public interface IntegralWallRepository extends MybatisRepository<Long, IntegralWall> {

	IntegralWall getByUserIdAndPK(Map<String, Object> map);
}

