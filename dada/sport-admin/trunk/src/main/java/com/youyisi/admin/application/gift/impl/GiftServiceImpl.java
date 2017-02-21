package com.youyisi.admin.application.gift.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.gift.GiftService;
import com.youyisi.admin.domain.gift.Gift;
import com.youyisi.admin.domain.gift.GiftRepository;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
@Service
public class GiftServiceImpl implements GiftService {

	@Autowired
	private GiftRepository repository;

	@Override
	public Gift get(Long id) {
		return repository.get(id);
	}

	@Override
	public Gift save(Gift gift) {
		return repository.save(gift);
}

	@Override
	public Integer delete(Gift gift) {
		return repository.delete(gift);
	}

	@Override
	public Integer update(Gift gift) {
		return repository.update(gift);
	}
	@Override
	public Page<Gift> queryPage(Page<Gift> page) {
		return repository.queryPage(page);
	}

    @Override
    public void saveBatch(List<Gift> giftList) {
        repository.saveBatch(giftList);
    }
}

