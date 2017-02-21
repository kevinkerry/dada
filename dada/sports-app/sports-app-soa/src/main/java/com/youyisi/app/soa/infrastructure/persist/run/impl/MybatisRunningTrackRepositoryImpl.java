package com.youyisi.app.soa.infrastructure.persist.run.impl;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.run.RunningTrackRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.run.RunningTrack;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Repository
public class MybatisRunningTrackRepositoryImpl extends MybatisOperations<Long, RunningTrack> implements RunningTrackRepository {

	@Override
	public void deleteByRunId(Long runId) {
		// TODO Auto-generated method stub
		 getSqlSession().delete(getNamespace().concat(".deleteByRunId"), runId);
	}
}

