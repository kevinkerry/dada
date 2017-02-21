package com.youyisi.app.soa.remote.area;

import java.util.List;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.area.Area;

/**
 * @author shuye
 * @time 2016-05-30
 */
public interface AreaServiceRemote extends BaseServiceInterface<Long, Area> {

	List<Area> getByParentId(Long parentId) throws SoaException;

}

