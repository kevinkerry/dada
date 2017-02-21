package com.youyisi.vote.application.user.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyisi.lang.Page;
import com.youyisi.lang.helper.TimeHelper;
import com.youyisi.vote.application.user.RedisUserService;
import com.youyisi.vote.application.user.UserService;
import com.youyisi.vote.domain.image.Image;
import com.youyisi.vote.domain.image.ImageRepository;
import com.youyisi.vote.domain.user.User;
import com.youyisi.vote.domain.user.UserRepository;
import com.youyisi.vote.infrastructure.cache.redis.RedisClient;
/**
 * 
 * @author shuye
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private RedisUserService redisUserService;
	@Override
	public Page<User> queryPage(Page<User> page) {
		return userRepository.queryPage(page);
	}
	@Override
	public void save(User user) {
		userRepository.save(user);
	}
	@Override
	public User get(Long id) {
		return userRepository.get(id);
	}
	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}
	@Override
	public void update(User user) {
		userRepository.update(user);
	}
	@Override
	public void regist(User user) {
		List<Image> images = user.getImages();
		save(user);
		for(Image image:images){
			image.setUserId(user.getId());
			if (StringUtils.isNotBlank(image.getImageUrl())) {
				imageRepository.save(image);
			}
		}
		user.setImages(images);
		redisUserService.save(user.getId()+"", user);
	}
	@Override
	public void updateVoteNum(User user) {
		// TODO Auto-generated method stub
		userRepository.updateVoteNum(user);
	}
	@Override
	public String vote(String wechat,Long userId){
		String openId = (String) RedisClient.get("vote:vote:openid"+wechat);
		System.out.println("fromuser:"+openId);
		if(StringUtils.isBlank(openId)){
			return "havenoattention";
		}
		String voted = (String) RedisClient.get("vote:vote:"+getToday()+":"+wechat);
		if(StringUtils.isNotBlank(voted)){
			Long number = Long.parseLong(voted);
			if(number<=0){
				return "havenovote";
			}
		}else{
			RedisClient.increment("vote:vote:"+getToday()+":"+wechat, 1l, 1, TimeUnit.DAYS);//代表有三次机会
		}
		RedisClient.increment("vote:vote:"+getToday()+":"+wechat, -1l, 1, TimeUnit.DAYS);
		Long votenum = RedisClient.increment("vote:vote:votenum:"+userId,1l);
		User u = redisUserService.get(userId+"");
		if(u==null){
			u = get(userId);
		}
		u.setVoteNum(votenum);
		redisUserService.save(userId+"", u);
		redisUserService.lpush(userId);
		return votenum+"";
	}
	
	private String getToday(){
		Date today = new Date();
		return TimeHelper.getStandardDate(today);
	}
}
