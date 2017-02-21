package com.youyisi.vote.infrastructure.persist.image;

import org.springframework.stereotype.Repository;

import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.vote.domain.image.Image;
import com.youyisi.vote.domain.image.ImageRepository;
/**
 * 
 * @author shuye
 *
 */
@Repository
public class MybatisImageRepository extends MybatisOperations<Long,Image> implements ImageRepository {

}
