package com.youyisi.vote.application.image.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.lang.Page;
import com.youyisi.vote.application.image.ImageService;
import com.youyisi.vote.domain.image.Image;
import com.youyisi.vote.domain.image.ImageRepository;
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
