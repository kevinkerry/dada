package com.youyisi.vote.application.image;

import com.youyisi.lang.Page;
import com.youyisi.vote.domain.image.Image;
/**
 * 
 * @author shuye
 *
 */
public interface ImageService {
	/**
	 * 
	 * @param user
	 */
	public void save(Image user);

	/**
	 * 
	 * @param id
	 */
	public Image get(Long id);

	/**
	 * 
	 * @param user
	 */
	public void delete(Image user);

	/**
	 * 
	 * @param user
	 */
	public void update(Image user);
	/**
	 * 
	 * @param page
	 * @return
	 */
	public Page<Image> queryPage(Page<Image> page);
	
	
}
