package com.youyisi.app.soa.infrastructure.persist.version;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.version.AppVersion;

/**
 * @author shuye
 * @time 2016-08-30
 */
public interface AppVersionRepository extends MybatisRepository<Long, AppVersion> {

	AppVersion getByChannel(String channel);
}

