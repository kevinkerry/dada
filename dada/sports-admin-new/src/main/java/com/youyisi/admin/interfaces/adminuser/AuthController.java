package com.youyisi.admin.interfaces.adminuser;

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

import com.youyisi.admin.application.adminuser.AuthService;
import com.youyisi.admin.domain.adminuser.Auth;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.lang.helper.DemonPredict;

@RequestMapping("/auth")
@Controller
public class AuthController {

	@Autowired
	private AuthService service;

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public WebResultInfoWrapper check(Model model, Auth auth) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		String msg = "";
		String name = "";
		String permission = "";
		try {
			name = URLDecoder.decode(auth.getName(), "UTF-8");
			permission = URLDecoder.decode(auth.getPermission(), "UTF-8");
			auth.setName(name);
			auth.setPermission(permission);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (StringUtils.isEmpty(name)) {
			msg = "权限名为空，请输入权限名！";
			webResultInfoWrapper.failed();
			webResultInfoWrapper.addResult("msg", msg);
			return webResultInfoWrapper;
		} else {
			if (service.isExistAuth(auth)) {
				msg = "权限名已存在，请重新输入权限名！";
				webResultInfoWrapper.failed();
				webResultInfoWrapper.addResult("msg", msg);
				return webResultInfoWrapper;
			}
		}

		if (StringUtils.isEmpty(permission)) {
			msg = "权限为空，请输入权限！";
			webResultInfoWrapper.failed();
			webResultInfoWrapper.addResult("msg", msg);
			return webResultInfoWrapper;
		}

		webResultInfoWrapper.success();

		return webResultInfoWrapper;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String create(Model model, @ModelAttribute Auth auth, HttpServletRequest request, BindingResult result) {
		DemonPredict.isTrue(!result.hasErrors(), "数据绑定错误");
		service.save(auth);
		return list(model, 1, null, null);
	}

	@RequestMapping(value = "{id}/delete")
	public String delete(Model model, @PathVariable(value = "id") Integer id) {
		Auth auth = new Auth();
		auth.setId(id);
		service.delete(auth);
		return list(model, 1, null, null);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, String name) {
		Page<Auth> page = new Page<Auth>();
		if (currentPage != null) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
		}

		try {
			if (StringUtils.isNotEmpty(name)) {
				name = URLDecoder.decode(name, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		page.addParam("name", name);
		page = service.queryPage(page);
		model.addAttribute("page", page);
		return "adminuser/auth/list";
	}

	@RequestMapping(value = "{id}/update", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String update(@PathVariable(value = "id") Integer id) {
		Auth auth = service.get(id);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("auth", auth);
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
	public String update(Model model, @ModelAttribute Auth auth, HttpServletRequest request, BindingResult result) {

		DemonPredict.isTrue(!result.hasErrors(), "数据绑定错误");
		service.update(auth);
		return list(model, 1, null, null);
	}

	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	@ResponseBody
	public WebResultInfoWrapper queryAll() {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		List<Auth> authList = service.query(new Auth());
		webResultInfoWrapper.success();
		webResultInfoWrapper.addResult("authList", authList);
		return webResultInfoWrapper;
	}
}
