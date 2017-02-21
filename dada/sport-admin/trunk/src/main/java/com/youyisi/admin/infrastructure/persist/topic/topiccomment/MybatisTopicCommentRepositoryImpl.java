package com.youyisi.admin.infrastructure.persist.topic.topiccomment;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.topic.topiccomment.TopicComment;
import com.youyisi.admin.domain.topic.topiccomment.TopicCommentRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Repository
public class MybatisTopicCommentRepositoryImpl extends MybatisOperations<Long, TopicComment> implements TopicCommentRepository {
}

