package com.youyisi.app.soa.remote.medal;

import java.util.List;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.medal.UserMedal;
import com.youyisi.sports.domain.medal.UserMedalWithMedal;

/**
 * @author shuye
 * @time 2016-09-05
 */
public interface UserMedalServiceRemote extends BaseServiceInterface<Long, UserMedal> {

	List<UserMedalWithMedal> getByUserId(Long userId)throws SoaException;

}

