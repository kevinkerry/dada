package com.youyisi.admin.infrastructure.helper.quartz;

import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.domain.user.UserRepository;
import com.youyisi.admin.infrastructure.constant.Constant;
import com.youyisi.admin.infrastructure.helper.gexinpush.PushToSingleHelper;
import com.youyisi.admin.infrastructure.helper.gexinpush.TransmissionContent;
import com.youyisi.lang.Page;
public class ActivityQuartzJob implements Job { 
	private static UserRepository userRepository;
	private static final String root = "classpath:/META-INF/spring/application-root.xml";

	static {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(root);
		userRepository = (UserRepository) appContext.getBean("mybatisUserRepositoryImpl");
	}
  
    @Override  
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	System.out.println("执行了...............");
    	MyJob myjob = (MyJob) jobExecutionContext.getJobDetail().getJobDataMap().get("scheduleJob");
    	String title = (String) myjob.getParams().get("title");
    	String contents = (String) myjob.getParams().get("content");
    	Page<User> page =  new Page<User>();
		//page.addParam("performanceRemind", 0);
    	page.setPageSize(1000);
		page = userRepository.queryPage(page);
		for (int currentPage = 1; currentPage <= page.getTotalPages(); currentPage++) {
			page.setCurrentPage(currentPage);
			page.setPageSize(1000);
			page = userRepository.queryPage(page);
			for (User a : page.getResult()) {
				pushMessage(a,title,contents);
				}
			}
			
		}
    
    private void pushMessage(User sportUser, String title, String contents) {
		TransmissionContent content = new TransmissionContent();
		content.setTitle(title);
		content.setType("ACTIVITY_WILL_BEGIN");
		Map<String, Object> entity = new HashMap<String, Object>();
		entity.put("content", contents);
		entity.put("sendTime", System.currentTimeMillis());
		content.setType(Constant.PUSH_SYS_MESSAGE);
		content.setEntity(entity);
		content.setToUserId(sportUser.getId());
		PushToSingleHelper.push(sportUser, content);
	}
}  