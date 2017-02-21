package com.youyisi.admin.application.relay;

import com.youyisi.admin.domain.relay.RelayMessage;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-10
 */
public interface RelayMessageService {

	RelayMessage save(RelayMessage entity);

	RelayMessage get(Long id);

	Integer delete(RelayMessage entity);

	Integer update(RelayMessage entity);

	Page<RelayMessage> queryPage(Page<RelayMessage> page);

}

