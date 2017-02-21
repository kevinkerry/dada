package com.youyisi.admin.application.intelligent.image.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.intelligent.image.ImageService;
import com.youyisi.admin.domain.intelligent.image.Image;
import com.youyisi.admin.domain.intelligent.image.ImageRepository;
import com.youyisi.lang.Page;
/**
 * 
 * @author shuye
 *
 */
@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository userRepository;
	@Override
	public Page<Image> queryPage(Page<Image> page) {
		return userRepository.queryPage(page);
	}
	@Override
	public void save(Image user) {
		userRepository.save(user);
	}
	@Override
	public Image get(Long id) {
		return userRepository.get(id);
	}
	@Override
	public void delete(Image user) {
		userRepository.delete(user);
	}
	@Override
	public void update(Image user) {
		userRepository.update(user);
	}
}
