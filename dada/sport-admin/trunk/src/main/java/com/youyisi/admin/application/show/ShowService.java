package com.youyisi.admin.application.show;

import com.youyisi.admin.domain.show.Show;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
public interface ShowService {

	Show save(Show show);

	Show get(Long id);

	Integer delete(Show show);

	Integer update(Show show);

	Page<Show> queryPage(Page<Show> page);

}

