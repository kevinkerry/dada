package com.youyisi.admin.interfaces.task;

import org.springframework.beans.factory.annotation.Autowired;

import com.youyisi.admin.application.user.UserService;
import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.infrastructure.helper.gexinpush.PushToSingleHelper;
import com.youyisi.admin.infrastructure.helper.gexinpush.TransmissionContent;
import com.youyisi.lang.Page;


/**
 * @author shuye
 * @time 2013-5-24
 */
public class UserTask {
	@Autowired
	private UserService userService;

	public void autoResultsRemind() {
		Page<User> page =  new Page<User>();
		page.addParam("performanceRemind", 0);
		page.setPageSize(1000);
		page = userService.queryPageForPush(page);
		for (int currentPage = 1; currentPage <= page.getTotalPages(); currentPage++) {
			page.setCurrentPage(currentPage);
			page.setPageSize(1000);
			page = userService.queryPageForPush(page);
			for (User a : page.getResult()) {
				pushMessage(a);
				}
			}
			
		}
	
	private void pushMessage(User user) {
		TransmissionContent content = new TransmissionContent();
		content.setTitle("今日运动数据已经出来，快点点击查看啦！");
		content.setType("RESULTS_REMIND");
		content.setToUserId(user.getId());
		PushToSingleHelper.push(user, content);
	}
		
	}
