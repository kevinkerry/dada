package com.youyisi.admin.application.gift.gifttype.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.gift.gifttype.GiftTypeService;
import com.youyisi.admin.domain.gift.gifttype.GiftType;
import com.youyisi.admin.domain.gift.gifttype.GiftTypeRepository;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
@Service
public class GiftTypeServiceImpl implements GiftTypeService {

	@Autowired
	private GiftTypeRepository repository;

	@Override
	public GiftType get(Long id) {
		return repository.get(id);
	}

	@Override
	public GiftType save(GiftType giftType) {
		return repository.save(giftType);
}

	@Override
	public Integer delete(GiftType giftType) {
		return repository.delete(giftType);
	}

	@Override
	public Integer update(GiftType giftType) {
		return repository.update(giftType);
	}
	@Override
	public Page<GiftType> queryPage(Page<GiftType> page) {
		return repository.queryPage(page);
	}

    @Override
    public List<GiftType> getAll(Map<String, Object> params) {
        return repository.getAll(params);
    }
}

