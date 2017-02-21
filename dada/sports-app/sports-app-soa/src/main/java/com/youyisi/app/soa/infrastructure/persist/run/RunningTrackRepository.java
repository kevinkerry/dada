package com.youyisi.app.soa.infrastructure.persist.run;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.run.RunningTrack;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface RunningTrackRepository extends MybatisRepository<Long, RunningTrack> {

	void deleteByRunId(Long runId);
}

