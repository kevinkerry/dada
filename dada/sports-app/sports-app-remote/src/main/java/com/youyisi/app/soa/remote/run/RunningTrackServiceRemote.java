package com.youyisi.app.soa.remote.run;

import java.util.List;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.run.RunningTrack;
import com.youyisi.sports.domain.user.User;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface RunningTrackServiceRemote extends BaseServiceInterface<Long, RunningTrack> {

	List<RunningTrack> add(List<RunningTrack> fromJson, User user) throws SoaException;
}

