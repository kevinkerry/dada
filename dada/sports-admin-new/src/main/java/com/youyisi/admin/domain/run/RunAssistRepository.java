package com.youyisi.admin.domain.run;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-08-25
 */
public interface RunAssistRepository extends MybatisRepository<Long, RunAssist> {

	List<RunAssist> getRunAssistByRunId(Long runId);
}
