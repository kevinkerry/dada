package com.youyisi.app.soa.infrastructure.persist.run;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.run.Run;
import com.youyisi.sports.domain.run.RunWithTrack;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface RunRepository extends MybatisRepository<Long, Run> {

	RunWithTrack getDetail(Long id);

	Run getMaxRun(Map<String, Object> map);
}

