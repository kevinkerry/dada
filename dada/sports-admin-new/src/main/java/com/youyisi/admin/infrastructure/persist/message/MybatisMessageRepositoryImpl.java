package com.youyisi.admin.infrastructure.persist.message;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.message.Message;
import com.youyisi.admin.domain.message.MessageRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-05-24
 */
@Repository
public class MybatisMessageRepositoryImpl extends MybatisOperations<Long, Message> implements MessageRepository {
}

