package com.youyisi.admin.application.topic.topicimage;

import com.youyisi.admin.domain.topic.topicimage.TopicImage;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
public interface TopicImageService {

	TopicImage save(TopicImage topicImage);

	TopicImage get(Long id);

	Integer delete(TopicImage topicImage);

	Integer update(TopicImage topicImage);

	Page<TopicImage> queryPage(Page<TopicImage> page);

}

