package com.youyisi.app.soa.infrastructure.persist.integralwall;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.integralwall.DoumIntegralWall;

/**
 * @author shuye
 * @time 2016-08-15
 */
public interface DoumIntegralWallRepository extends MybatisRepository<Long, DoumIntegralWall> {

	DoumIntegralWall getByUserIdAndOrderId(Map<String, Object> map);
}

