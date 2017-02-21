package com.youyisi.admin.infrastructure.persist.sportsvenues.sportvenueschedule;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.sportsvenues.sportvenueschedule.SportVenueSchedule;
import com.youyisi.admin.domain.sportsvenues.sportvenueschedule.SportVenueScheduleRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author yinjunfeng
 * @time 2015-07-24
 */
@Repository
public class MybatisSportVenueScheduleRepositoryImpl extends MybatisOperations<Long, SportVenueSchedule> implements SportVenueScheduleRepository {
}

