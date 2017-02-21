package com.youyisi.admin.interfaces.admin.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.user.RoleService;
import com.youyisi.admin.domain.user.Role;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.lang.helper.DemonPredict;


@RequestMapping("/role")
@Controller
public class RoleController {

	@Autowired
	private RoleService service;

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
    public WebResultInfoWrapper check(Model model,Role role) {
	    WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
	    String msg = "";
	    String name = "";
	    String showName = "";
        try {
            name = URLDecoder.decode(role.getName(), "UTF-8");
            showName = URLDecoder.decode(role.getShowName(), "UTF-8");
            role.setName(name);
            role.setShowName(showName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
	    if(StringUtils.isEmpty(name)) {
	        msg = "角色为空，请输入角色！";
	        webResultInfoWrapper.failed();
	        webResultInfoWrapper.addResult("msg", msg);
	        return webResultInfoWrapper;
	    }else {
	        if(service.isExistRole(role)) {
	            msg = "角色已存在，请重新输入角色！";
	            webResultInfoWrapper.failed();
	            webResultInfoWrapper.addResult("msg", msg);
	            return webResultInfoWrapper;
	        }
	    }
	    
        if(StringUtils.isEmpty(showName)) {
            msg = "角色名称为空，请输入角色名称！";
            webResultInfoWrapper.failed();
            webResultInfoWrapper.addResult("msg", msg);
            return webResultInfoWrapper;
        }
	    
        webResultInfoWrapper.success();
        
        return webResultInfoWrapper;
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String create(Model model,@ModelAttribute Role role, HttpServletRequest request,
			BindingResult result) {
		DemonPredict.isTrue(!result.hasErrors(), "数据绑定错误");
		service.save(role);
		return list(model, 1, null, null , null);
	}

	@RequestMapping(value = "{id}/delete")
	public String delete(Model model, @PathVariable("id") Integer id) {
	    Role role = new Role();
	    role.setId(id);
		service.delete(role);
		return list(model,1, null, null , null);
	}

	@RequestMapping(value = "{id}/update", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
    public String update(@PathVariable(value="id")Integer id) {
	    Role role = service.get(id);
	    ObjectMapper mapper = new ObjectMapper();
	    Map<String, Object> result = new HashMap<String, Object>();
	    result.put("success", true);
	    result.put("role", role);
	    String json = "";
        try {
            json = mapper.writeValueAsString(result);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, @ModelAttribute Role role, HttpServletRequest request,
			BindingResult result) {

		DemonPredict.isTrue(!result.hasErrors(), "数据绑定错误");
		service.update(role);
		return list(model,1,null, null, null);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model,Integer currentPage, Integer pageSize, String name, String showName) {
	    Page<Role> page = new Page<Role>();
        if (currentPage != null) {
            page.setCurrentPage(currentPage);
        }
        if(null != pageSize) {
            page.setPageSize(pageSize);
        }else {
            page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
        }
	    
	    try {
    	    if(StringUtils.isNotEmpty(name)) {
    	        name = URLDecoder.decode(name, "UTF-8");
    	    }
    	    if(StringUtils.isNotEmpty(showName)) {
                showName = URLDecoder.decode(showName, "UTF-8");
            }
	    } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
	    
		page.addParam("name", name);
		page.addParam("showName", showName);
		page = service.queryPage(page);
		model.addAttribute("page",page);
		return "user/role/list";
	}
	
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    @ResponseBody
    public WebResultInfoWrapper queryAll() {
        WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
        List<Role> roleList = service.query(new Role());
        webResultInfoWrapper.success();
        webResultInfoWrapper.addResult("roleList", roleList);
        return webResultInfoWrapper;
    }
}
