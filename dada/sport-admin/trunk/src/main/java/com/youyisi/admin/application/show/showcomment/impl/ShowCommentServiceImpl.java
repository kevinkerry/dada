package com.youyisi.admin.application.show.showcomment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.show.showcomment.ShowCommentService;
import com.youyisi.admin.domain.show.showcomment.ShowComment;
import com.youyisi.admin.domain.show.showcomment.ShowCommentRepository;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
@Service
public class ShowCommentServiceImpl implements ShowCommentService {

	@Autowired
	private ShowCommentRepository repository;

	@Override
	public ShowComment get(Long id) {
		return repository.get(id);
	}

	@Override
	public ShowComment save(ShowComment showComment) {
		return repository.save(showComment);
}

	@Override
	public Integer delete(ShowComment showComment) {
		return repository.delete(showComment);
	}

	@Override
	public Integer update(ShowComment showComment) {
		return repository.update(showComment);
	}
	@Override
	public Page<ShowComment> queryPage(Page<ShowComment> page) {
		return repository.queryPage(page);
	}
}

