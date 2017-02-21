package com.youyisi.admin.application.show.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.show.ShowService;
import com.youyisi.admin.domain.show.Show;
import com.youyisi.admin.domain.show.ShowRepository;
import com.youyisi.admin.infrastructure.constant.Constant;
import com.youyisi.admin.infrastructure.message.ActiveMqSender;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	private ShowRepository repository;
	
	@Resource
	private ActiveMqSender activeMqSender;

	@Override
	public Show get(Long id) {
		return repository.get(id);
	}

	@Override
	public Show save(Show show) {
	    Show savedShow = repository.save(show);
	    activeMqSender.send(savedShow.getShowId().toString(), Constant.JMS_QUEUE_SHOW);
		return savedShow;
	}

	@Override
	public Integer delete(Show show) {
	    //只做逻辑删除
	    show.setStatus("I");
	    
		return repository.update(show);
	}

	@Override
	public Integer update(Show show) {
	    int result = repository.update(show);
	    activeMqSender.send(show.getShowId().toString(), Constant.JMS_QUEUE_SHOW);
		return result;
	}
	@Override
	public Page<Show> queryPage(Page<Show> page) {
		return repository.queryPage(page);
	}
}

