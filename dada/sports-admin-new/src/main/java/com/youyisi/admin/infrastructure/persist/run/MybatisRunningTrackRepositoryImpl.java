package com.youyisi.admin.infrastructure.persist.run;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.run.RunningTrack;
import com.youyisi.admin.domain.run.RunningTrackRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-06-27
 */
@Repository
public class MybatisRunningTrackRepositoryImpl extends MybatisOperations<Long, RunningTrack>
		implements RunningTrackRepository {

	@Override
	public List<RunningTrack> getRunningTrackByRunId(Long runId) {

		return getSqlSession().selectList(getNamespace().concat(".getRunningTrackByRunId"), runId);
	}
}
