package com.youyisi.app.soa.remote.step;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.step.Step;
import com.youyisi.sports.domain.step.StepWithUser;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.user.UserMoreInfo;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface StepServiceRemote extends BaseServiceInterface<Long, Step> {

	UserMoreInfo updateStep(Step entity, User user) throws SoaException;

	Page<StepWithUser> queryPageRanklist(Page<StepWithUser> page) throws SoaException;

	Long getMyRanking(Integer step, Long id) throws SoaException;

	Step getByUserIdAndDate(Long userId) throws SoaException;

}
