package com.youyisi.app.soa.remote.version;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.version.AppVersion;

/**
 * @author shuye
 * @time 2016-08-30
 */
public interface AppVersionServiceRemote extends BaseServiceInterface<Long, AppVersion> {

	AppVersion getByChannel(String channel) throws SoaException;

}

