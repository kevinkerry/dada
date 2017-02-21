package com.youyisi.admin.application.activity.comment;

import com.youyisi.admin.domain.activity.comment.Comment;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
public interface CommentService {

	Comment save(Comment comment);

	Comment get(Long id);

	Integer delete(Comment comment);

	Integer update(Comment comment);

	Page<Comment> queryPage(Page<Comment> page);

}

