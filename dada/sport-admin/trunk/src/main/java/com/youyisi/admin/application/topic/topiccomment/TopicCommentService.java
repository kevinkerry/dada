package com.youyisi.admin.application.topic.topiccomment;

import com.youyisi.admin.domain.topic.topiccomment.TopicComment;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
public interface TopicCommentService {

	TopicComment save(TopicComment topicComment);

	TopicComment get(Long id);

	Integer delete(TopicComment topicComment);

	Integer update(TopicComment topicComment);

	Page<TopicComment> queryPage(Page<TopicComment> page);

}

