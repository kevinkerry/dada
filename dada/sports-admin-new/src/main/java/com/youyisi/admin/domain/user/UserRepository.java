package com.youyisi.admin.domain.user;

import java.util.List;
import java.util.Map;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-05-16
 */
public interface UserRepository extends MybatisRepository<Long, User> {

	Page<User> queryPageUserList(Page<User> page);

	Page<User> queryPagePushUser(Page<User> page);

	Integer countUser(Map<String, Object> map);

	Integer countClientIdNum(String clientId);

	List<User> getClientIdAndClientIdNum();

	Page<UserInvite> queryPageUserInvite(Page<UserInvite> page);

	Integer countInviteNum(Long activityId);

	Page<UserInvite> queryPageUserInviteDetail(Page<UserInvite> page);

	Page<User> queryPageForPush(Page<User> page);
}
