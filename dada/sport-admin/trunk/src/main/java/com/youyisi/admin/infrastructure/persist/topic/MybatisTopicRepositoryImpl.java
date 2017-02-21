package com.youyisi.admin.infrastructure.persist.topic;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.topic.Topic;
import com.youyisi.admin.domain.topic.TopicRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Repository
public class MybatisTopicRepositoryImpl extends MybatisOperations<Long, Topic> implements TopicRepository {
}

