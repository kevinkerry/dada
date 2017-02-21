package com.youyisi.admin.application.activity.comment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.activity.comment.CommentService;
import com.youyisi.admin.domain.activity.comment.Comment;
import com.youyisi.admin.domain.activity.comment.CommentRepository;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repository;

	@Override
	public Comment get(Long id) {
		return repository.get(id);
	}

	@Override
	public Comment save(Comment comment) {
		return repository.save(comment);
}

	@Override
	public Integer delete(Comment comment) {
		return repository.delete(comment);
	}

	@Override
	public Integer update(Comment comment) {
		return repository.update(comment);
	}
	@Override
	public Page<Comment> queryPage(Page<Comment> page) {
		return repository.queryPage(page);
	}
}

