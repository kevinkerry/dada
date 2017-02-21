package com.youyisi.app.soa.remote.thigh;

import java.util.List;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.thigh.MyThigh;
import com.youyisi.sports.domain.thigh.Thigh;
import com.youyisi.sports.domain.thigh.ThighMoreInfo;
import com.youyisi.sports.domain.thigh.ThighRank;

/**
 * @author shuye
 * @time 2016-07-11
 */
public interface ThighServiceRemote extends BaseServiceInterface<Long, Thigh> {

	Thigh getThigh(Long activityId, Long userId) throws SoaException;

	List<ThighMoreInfo> getList(Long activityId)throws SoaException;

	Thigh getByActivityIdAndUserId(Long activityId, Long userId,Long createTime)throws SoaException;

	Long thighCount(Long id, long time)throws SoaException;

	Page<MyThigh> queryPageMyThigh(Page<MyThigh> page)throws SoaException;

	public List<ThighRank> getListThighRank(Page<ThighRank> page) throws SoaException;

}

