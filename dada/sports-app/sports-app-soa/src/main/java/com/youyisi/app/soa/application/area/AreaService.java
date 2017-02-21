package com.youyisi.app.soa.application.area;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.infrastructure.persist.area.AreaRepository;
import com.youyisi.app.soa.remote.area.AreaServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.area.Area;

/**
 * @author shuye
 * @time 2016-05-30
 */
@Service
public class AreaService implements AreaServiceRemote {

	@Autowired
	private AreaRepository repository;

	@Override
	public Area get(Long id) throws SoaException{
		return repository.get(id);
	}

	@Override
	public Area save(Area entity) throws SoaException{
		return repository.save(entity);
}

	@Override
	public Integer delete(Area entity) throws SoaException{
		return repository.delete(entity);
	}

	@Override
	public Integer update(Area entity) throws SoaException{
		return repository.update(entity);
	}
	@Override
	public Page<Area> queryPage(Page<Area> page) throws SoaException{
		return repository.queryPage(page);
	}

	@Override
	public List<Area> getByParentId(Long parentId) throws SoaException {
		return repository.getByParentId(parentId);
	}
}

