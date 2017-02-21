package com.youyisi.admin.application.indiana.indianadetail;

import com.youyisi.admin.domain.indiana.indianadetail.IndianaDetail;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
public interface IndianaDetailService {

	IndianaDetail save(IndianaDetail indianaDetail);

	IndianaDetail get(Long id);

	Integer delete(IndianaDetail indianaDetail);

	Integer update(IndianaDetail indianaDetail);

	Page<IndianaDetail> queryPage(Page<IndianaDetail> page);

}

