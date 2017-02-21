package com.youyisi.admin.application.intelligent;

import java.util.List;

import com.youyisi.admin.domain.intelligent.Intelligent;
import com.youyisi.lang.Page;

public interface IntelligentService {

	/**
	 * @param id
	 * @return
	 */
	Intelligent get(Long id);
	
	/**
	 * @param Intelligentname
	 * @return
	 */
	Intelligent getByIntelligentname(String Intelligentname);
	
	/**
	 * 
	 * @param admin
	 * @return
	 */
	Intelligent save(Intelligent Intelligent);
	
	/**
	 * 
	 * @param Intelligent
	 * @return
	 */
	int delete(Intelligent Intelligent);
	
	/**
	 * 
	 * @param Intelligent
	 * @return
	 */
	int update(Intelligent Intelligent);
	/**
	 * query all
	 * 
	 * @param entity
	 * @return
	 */
	List<Intelligent> query(Intelligent Intelligent);

	/**
	 * query for page
	 * 
	 * @param page
	 * @return
	 */
	Page<Intelligent> queryPage(Page<Intelligent> page);

	boolean notExistIntelligent(Intelligent Intelligent);

	Integer out(Intelligent Intelligent);

	void pass(Intelligent intelligent);
}
