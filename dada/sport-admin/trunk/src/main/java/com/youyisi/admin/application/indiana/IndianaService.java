package com.youyisi.admin.application.indiana;

import com.youyisi.admin.domain.indiana.Indiana;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
public interface IndianaService {

	Indiana save(Indiana indiana);

	Indiana get(Long id);

	Integer delete(Indiana indiana);

	Integer update(Indiana indiana);

	Page<Indiana> queryPage(Page<Indiana> page);

}

