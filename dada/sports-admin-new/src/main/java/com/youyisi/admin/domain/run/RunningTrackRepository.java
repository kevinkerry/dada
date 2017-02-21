package com.youyisi.admin.domain.run;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-06-27
 */
public interface RunningTrackRepository extends MybatisRepository<Long, RunningTrack> {

	List<RunningTrack> getRunningTrackByRunId(Long runId);
}
