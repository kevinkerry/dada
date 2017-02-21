package com.youyisi.app.soa.remote;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.lang.Page;

public interface BaseServiceInterface<K, T> {
	
	    T save(T entity) throws SoaException;
	    
	    T get(Long id) throws SoaException;
	    
	    Integer delete(T entity) throws SoaException;
	    
	    Integer update(T entity) throws SoaException;
	    
	    Page<T> queryPage(Page<T> page) throws SoaException;
}
