package com.youyisi.admin.infrastructure.persist.run;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.run.RunAssist;
import com.youyisi.admin.domain.run.RunAssistRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-08-25
 */
@Repository
public class MybatisRunAssistRepositoryImpl extends MybatisOperations<Long, RunAssist> implements RunAssistRepository {

	@Override
	public List<RunAssist> getRunAssistByRunId(Long runId) {

		return getSqlSession().selectList(getNamespace().concat(".getRunAssistByRunId"), runId);
	}
}
