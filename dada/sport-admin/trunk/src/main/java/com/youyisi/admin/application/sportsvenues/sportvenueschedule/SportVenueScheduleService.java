package com.youyisi.admin.application.sportsvenues.sportvenueschedule;

import com.youyisi.admin.domain.sportsvenues.sportvenueschedule.SportVenueSchedule;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-07-24
 */
public interface SportVenueScheduleService {

	SportVenueSchedule save(SportVenueSchedule entity);

	SportVenueSchedule get(Long id);

	Integer delete(SportVenueSchedule entity);

	Integer update(SportVenueSchedule entity);

	Page<SportVenueSchedule> queryPage(Page<SportVenueSchedule> page);

}

