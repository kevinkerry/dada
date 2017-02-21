package com.youyisi.vote.application.spot;

import java.util.List;

import com.youyisi.vote.domain.spot.Spot;

/**
 * 
 * @author shuye
 *
 */
public interface SpotService {

	String lucky(String name, String mobile);

	List<Spot> queryAllSpot();
	
	
}
