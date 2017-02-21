package com.youyisi.admin.application.intelligent.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.intelligent.IntelligentService;
import com.youyisi.admin.domain.intelligent.Intelligent;
import com.youyisi.admin.domain.intelligent.IntelligentRepository;
import com.youyisi.admin.domain.intelligent.image.Image;
import com.youyisi.admin.domain.intelligent.image.ImageRepository;
import com.youyisi.admin.domain.intelligent.items.SportsItems;
import com.youyisi.admin.domain.intelligent.items.SportsItemsRepository;
import com.youyisi.lang.Page;


@Service
public class IntelligentServiceImpl implements IntelligentService {

	@Autowired
	private IntelligentRepository repository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private SportsItemsRepository sportsItemsRepository;

	@Override
	public Intelligent get(Long id) {
		return repository.get(id);
	}

	@Override
	public Intelligent getByIntelligentname(String username) {
		return repository.getByUsername(username);
	}

	@Override
	public Intelligent save(Intelligent user) {
		List<Image> images = user.getImages();
		List<SportsItems> items = user.getItems();
		Intelligent u = repository.save(user);
		for(Image image:images){
			image.setUserId(user.getId());
			imageRepository.save(image);
		}
		for(SportsItems s:items){
			s.setUserId(user.getId());
			sportsItemsRepository.save(s);
		}
		return u;
	}

	@Override
	public int delete(Intelligent user) {
		return repository.delete(user);
	}

	@Override
	public int update(Intelligent user) {
		return repository.update(user);
	}

	@Override
	public List<Intelligent> query(Intelligent user) {
		return repository.query(user);
	}

	@Override
	public Page<Intelligent> queryPage(Page<Intelligent> page) {
		return repository.queryPage(page);
	}

	@Override
	public boolean notExistIntelligent(Intelligent user) {
		// TODO Auto-generated method stub
		return repository.notExistUser(user);
	}

	@Override
	public Integer out(Intelligent user) {
		// TODO Auto-generated method stub
		return repository.out(user);
	}

	@Override
	public void pass(Intelligent intelligent) {
		// TODO Auto-generated method stub
		repository.pass(intelligent);
	}

}
