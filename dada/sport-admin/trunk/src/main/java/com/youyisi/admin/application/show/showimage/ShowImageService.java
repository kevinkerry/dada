package com.youyisi.admin.application.show.showimage;

import com.youyisi.admin.domain.show.showimage.ShowImage;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
public interface ShowImageService {

	ShowImage save(ShowImage showImage);

	ShowImage get(Long id);

	Integer delete(ShowImage showImage);

	Integer update(ShowImage showImage);

	Page<ShowImage> queryPage(Page<ShowImage> page);

}

