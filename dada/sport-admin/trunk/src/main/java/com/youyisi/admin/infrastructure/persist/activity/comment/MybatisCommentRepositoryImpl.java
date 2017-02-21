package com.youyisi.admin.infrastructure.persist.activity.comment;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.activity.comment.Comment;
import com.youyisi.admin.domain.activity.comment.CommentRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
@Repository
public class MybatisCommentRepositoryImpl extends MybatisOperations<Long, Comment> implements CommentRepository {
}

