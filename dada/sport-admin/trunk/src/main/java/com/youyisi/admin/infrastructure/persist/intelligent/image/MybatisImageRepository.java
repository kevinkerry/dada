package com.youyisi.admin.infrastructure.persist.intelligent.image;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.intelligent.image.Image;
import com.youyisi.admin.domain.intelligent.image.ImageRepository;
import com.youyisi.mybatis.MybatisOperations;
/**
 * 
 * @author shuye
 *
 */
@Repository
public class MybatisImageRepository extends MybatisOperations<Long,Image> implements ImageRepository {

}
