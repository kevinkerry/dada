package com.youyisi.admin.infrastructure.persist.activity.activityimage;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.activity.activityimage.ActivityImage;
import com.youyisi.admin.domain.activity.activityimage.ActivityImageRepository;
import com.youyisi.mybatis.MybatisOperations;
/**
 * @author shuye
 * @time 2015-07-07 16 49 34
 */
@Repository
public class MybatisActivityImageRepositoryImpl extends MybatisOperations<Long, ActivityImage> implements ActivityImageRepository {
}

