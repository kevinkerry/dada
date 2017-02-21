package com.youyisi.admin.infrastructure.persist.show.showcomment;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.show.showcomment.ShowComment;
import com.youyisi.admin.domain.show.showcomment.ShowCommentRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
@Repository
public class MybatisShowCommentRepositoryImpl extends MybatisOperations<Long, ShowComment> implements ShowCommentRepository {
}

