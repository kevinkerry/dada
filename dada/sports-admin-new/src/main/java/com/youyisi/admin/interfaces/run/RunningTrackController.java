package com.youyisi.admin.interfaces.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.run.RunningTrackService;
import com.youyisi.admin.domain.run.RunningTrack;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-27
 */
@Controller
@RequestMapping("/runningtrack")
public class RunningTrackController{

	@Autowired
	private RunningTrackService runningTrackService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<RunningTrack> page = new Page<RunningTrack>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", runningTrackService.queryPage(page));
		return "runningtrack/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("runningTrack", runningTrackService.get(id));
		return "runningtrack/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(RunningTrack runningTrack) {
		runningTrackService.update(runningTrack);
		return "redirect:runningtrack/list";
	}
}

