package com.youyisi.admin.application.user;

import java.util.List;

import com.youyisi.admin.domain.managemoney.ManageMoney;
import com.youyisi.admin.domain.sportrecord.SportRecord;
import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.domain.user.UserInvite;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-16
 */
public interface UserService {

	User save(User entity);

	User get(Long id);

	Integer delete(User entity);

	Integer update(User entity);

	Page<User> queryPage(Page<User> page);

	Page<User> queryPageUserList(Page<User> page);

	Page<User> queryPagePushUser(Integer currentPage, Integer pageSize, Integer clientType);

	Integer countUser(Long beginTime, Long endTime, Integer clientType);

	ManageMoney getManageMoney(Long userId);

	SportRecord getSportRecord(Long userId);

	List<User> getClientIdAndClientIdNum();

	Page<UserInvite> queryPageUserInvite(Page<UserInvite> page);

	Integer countInviteNum(Long activityId);

	Page<UserInvite> queryPageUserInviteDetail(Page<UserInvite> page);

	Page<User> queryPageForPush(Page<User> page);

}
