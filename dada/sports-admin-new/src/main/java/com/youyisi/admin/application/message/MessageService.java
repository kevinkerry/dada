package com.youyisi.admin.application.message;

import com.youyisi.admin.domain.message.Message;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-24
 */
public interface MessageService {

	Message save(Message entity);

	Message get(Long id);

	Integer delete(Message entity);

	Integer update(Message entity);

	Page<Message> queryPage(Page<Message> page);

	Integer send(Long id);

	Integer sendOne(Long mid,Long userId);
}
