package com.youyisi.admin.application.show.showimage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyisi.admin.application.show.showimage.ShowImageService;
import com.youyisi.admin.domain.show.showimage.ShowImage;
import com.youyisi.admin.domain.show.showimage.ShowImageRepository;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
@Service
public class ShowImageServiceImpl implements ShowImageService {

	@Autowired
	private ShowImageRepository repository;

	@Override
	public ShowImage get(Long id) {
		return repository.get(id);
	}

	@Override
	public ShowImage save(ShowImage showImage) {
		return repository.save(showImage);
}

	@Override
	public Integer delete(ShowImage showImage) {
		return repository.delete(showImage);
	}

	@Override
	public Integer update(ShowImage showImage) {
		return repository.update(showImage);
	}
	@Override
	public Page<ShowImage> queryPage(Page<ShowImage> page) {
		return repository.queryPage(page);
	}
}

