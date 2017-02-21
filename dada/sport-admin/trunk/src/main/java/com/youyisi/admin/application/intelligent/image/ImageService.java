package com.youyisi.admin.application.intelligent.image;

import com.youyisi.admin.domain.intelligent.image.Image;
import com.youyisi.lang.Page;
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
