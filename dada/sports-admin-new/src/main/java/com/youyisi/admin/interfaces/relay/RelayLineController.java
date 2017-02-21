package com.youyisi.admin.interfaces.relay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.youyisi.admin.application.relay.RelayLineService;
import com.youyisi.admin.domain.relay.RelayLine;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-10
 */
@Controller
@RequestMapping("/relayline")
public class RelayLineController{

	@Autowired
	private RelayLineService RelayLineService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize) {
		Page<RelayLine> page = new Page<RelayLine>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(10);
		}
		model.addAttribute("page", RelayLineService.queryPage(page));
		return "relayline/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("RelayLine", RelayLineService.get(id));
		return "relayline/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(RelayLine RelayLine) {
		RelayLineService.update(RelayLine);
		return "redirect:relayline/list";
	}
}

