package com.youyisi.admin.domain.user;

import java.io.Serializable;

/**
 * 
 * @author hetao
 * @date 2016年7月5日 下午4:22:01
 * @version 1.0
 * @parameter
 * @return
 */
public class RequestUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1059092304524009320L;

	private Integer currentPage;
	private Integer pageSize;
	private String condition;
	private String field;
	private String sort;
	private String beginTime;
	private String endTime;
	private Integer dateType;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getDateType() {
		return dateType;
	}

	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}

}
