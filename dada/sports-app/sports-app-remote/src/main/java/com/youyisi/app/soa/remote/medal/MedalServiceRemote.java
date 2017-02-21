package com.youyisi.app.soa.remote.medal;

import java.util.List;

import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.medal.Medal;

/**
 * @author shuye
 * @time 2016-09-05
 */
public interface MedalServiceRemote extends BaseServiceInterface<Long, Medal> {

	List<Medal> getNoHaveByUserId(Long userId);

}

