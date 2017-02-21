package com.youyisi.admin.application.topic;

import com.youyisi.admin.domain.topic.Topic;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
public interface TopicService {

	Topic save(Topic topic);

	Topic get(Long id);

	Integer delete(Topic topic);

	Integer update(Topic topic);

	Page<Topic> queryPage(Page<Topic> page);

}

