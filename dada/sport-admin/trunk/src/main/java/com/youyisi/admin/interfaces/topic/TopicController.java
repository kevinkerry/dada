package com.youyisi.admin.interfaces.topic;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mchange.v2.beans.BeansUtils;
import com.youyisi.admin.application.topic.TopicService;
import com.youyisi.admin.domain.topic.Topic;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Controller
@RequestMapping("/topic")
public class TopicController{

	@Autowired
	private TopicService topicService;

	@RequestMapping(value = "/list")
	public String list(Model model, Integer currentPage, Integer pageSize, Topic topic) {
		Page<Topic> page = new Page<Topic>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
        try {
            if(null != topic) {
                BeansUtils.extractAccessiblePropertiesToMap(params, topic);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        page.addParams(params);
		model.addAttribute("page", topicService.queryPage(page));
		return "topic/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("topic", topicService.get(id));
		return "topic/form";
	}

	@RequestMapping(value = "{topicId}/update", method = RequestMethod.GET)
	public String update(Model model, @PathVariable(value = "topicId")Long topicId) {
	    model.addAttribute("topic", topicService.get(topicId));
		return "topic/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, Topic topic) {
        topicService.update(topic);
        model.addAttribute("topic", topic);
        return "topic/update";
    }
	
	@RequestMapping(value = "/changeStatus", method = RequestMethod.GET)
    public String changeStatus(Model model, Long topicId,String status) {
        Topic topic = topicService.get(topicId);
        if(topic != null) {
            if(StringUtils.isNotBlank(status)) {
                topic.setStatus(status);
            }
            
            topicService.update(topic);
        }
        return list(model, 1, null,null);
    }
}

