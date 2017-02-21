package com.youyisi.admin.infrastructure.persist.relay;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.relay.RelayMessage;
import com.youyisi.admin.domain.relay.RelayMessageRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-10
 */
@Repository
public class MybatisRelayMessageRepositoryImpl extends MybatisOperations<Long, RelayMessage> implements RelayMessageRepository {
}

