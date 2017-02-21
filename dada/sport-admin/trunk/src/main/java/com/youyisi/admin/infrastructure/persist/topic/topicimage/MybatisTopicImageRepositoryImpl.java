package com.youyisi.admin.infrastructure.persist.topic.topicimage;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.topic.topicimage.TopicImage;
import com.youyisi.admin.domain.topic.topicimage.TopicImageRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Repository
public class MybatisTopicImageRepositoryImpl extends MybatisOperations<Long, TopicImage> implements TopicImageRepository {
}

