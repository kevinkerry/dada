package com.youyisi.admin.application.run;

import java.util.List;

import com.youyisi.admin.domain.run.RunningTrack;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-27
 */
public interface RunningTrackService {

	RunningTrack save(RunningTrack entity);

	RunningTrack get(Long id);

	Integer delete(RunningTrack entity);

	Integer update(RunningTrack entity);

	Page<RunningTrack> queryPage(Page<RunningTrack> page);

	List<RunningTrack> getRunningTrackByRunId(Long runId);

}
