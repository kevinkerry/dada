package com.youyisi.admin.infrastructure.persist.show.showimage;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.show.showimage.ShowImage;
import com.youyisi.admin.domain.show.showimage.ShowImageRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
@Repository
public class MybatisShowImageRepositoryImpl extends MybatisOperations<Long, ShowImage> implements ShowImageRepository {
}

