package com.youyisi.admin.infrastructure.persist.member.memberimage;
import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.member.memberimage.MemberImage;
import com.youyisi.admin.domain.member.memberimage.MemberImageRepository;
import com.youyisi.mybatis.MybatisOperations;
/**
 * @author yinjunfeng
 * @time 2015-07-23 20 22 12
 */
@Repository
public class MybatisMemberImageRepositoryImpl extends MybatisOperations<Long, MemberImage> implements MemberImageRepository {
}

