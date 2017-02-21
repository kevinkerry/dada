package com.youyisi.admin.application.show.showcomment;

import com.youyisi.admin.domain.show.showcomment.ShowComment;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
public interface ShowCommentService {

	ShowComment save(ShowComment showComment);

	ShowComment get(Long id);

	Integer delete(ShowComment showComment);

	Integer update(ShowComment showComment);

	Page<ShowComment> queryPage(Page<ShowComment> page);

}

