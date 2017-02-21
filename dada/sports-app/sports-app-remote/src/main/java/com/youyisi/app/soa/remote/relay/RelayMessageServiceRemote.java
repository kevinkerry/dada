package com.youyisi.app.soa.remote.relay;

import java.util.List;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.relay.RelayMessage;
import com.youyisi.sports.domain.user.UserLessInfo;

/**
 * @author shuye
 * @time 2016-10-08
 */
public interface RelayMessageServiceRemote extends BaseServiceInterface<Long, RelayMessage> {

	List<UserLessInfo> newUser(Long teamId)throws SoaException;

	Integer totalCount(Long teamId)throws SoaException;

	RelayMessage newMessage(Long teamId)throws SoaException;

}

