package com.youyisi.app.soa.remote.run;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.run.Run;
import com.youyisi.sports.domain.run.RunWithTrack;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface RunServiceRemote extends BaseServiceInterface<Long, Run> {

	RunWithTrack getDetail(Long id) throws SoaException;

	Run correction(Run r);

	Run getMaxRun(Long userId, Long dateForDay, Double avspeed, Double maxspeed)throws SoaException;

}

