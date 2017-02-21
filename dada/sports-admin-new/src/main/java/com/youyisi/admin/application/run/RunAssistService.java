package com.youyisi.admin.application.run;

import java.util.List;

import com.youyisi.admin.domain.run.RunAssist;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-08-25
 */
public interface RunAssistService {

	RunAssist save(RunAssist entity);

	RunAssist get(Long id);

	Integer delete(RunAssist entity);

	Integer update(RunAssist entity);

	Page<RunAssist> queryPage(Page<RunAssist> page);

	List<RunAssist> getRunAssistByRunId(Long runId);
}
