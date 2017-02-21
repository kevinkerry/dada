package com.youyisi.vote.interfaces.task;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.youyisi.vote.application.user.RedisUserService;
import com.youyisi.vote.domain.user.LeadUser;
import com.youyisi.vote.domain.user.LeadUserRepository;
import com.youyisi.vote.domain.user.User;
import com.youyisi.vote.domain.user.UserRepository;
import com.youyisi.vote.infrastructure.cache.redis.RedisClient;

public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private RedisUserService redisUserService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LeadUserRepository leadUserRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			// 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
			new Thread() {
				public void run() {
					while(true){
					try{
						String value = redisUserService.rightPop();
						
						if(StringUtils.isNotBlank(value)){
							System.out.println("update user vote value,userid:"+value);
							User user = new User();
							user.setId(Long.parseLong(value));
							String vote = (String) RedisClient.get("vote:vote:votenum:"+value);
							if(StringUtils.isBlank(vote)){
								user.setVoteNum(0l);
							}else{
								user.setVoteNum(Long.parseLong(vote));
								if(user.getVoteNum().equals(88l)){
									LeadUser entity = new LeadUser();
									entity.setUserId(user.getId());
									entity.setCreateTime(System.currentTimeMillis());
									leadUserRepository.save(entity);
								}
							}
							userRepository.updateVoteNum(user);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					}
				}
			}.start();
		}
	}
}