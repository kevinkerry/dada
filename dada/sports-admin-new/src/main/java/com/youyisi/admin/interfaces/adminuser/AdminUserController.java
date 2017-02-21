package com.youyisi.admin.interfaces.adminuser;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.adminuser.AdminUserService;
import com.youyisi.admin.domain.adminuser.AdminUser;
import com.youyisi.admin.infrastructure.exception.UserExistException;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.admin.infrastructure.helper.CurrentUserHelper;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.lang.helper.PasswordEncoder;

/**
 * @author shuye
 * @time 2013-8-9
 */
@Controller
@RequestMapping("/adminuser")
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login";

	}

	/**
	 * 登录
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, AdminUser user, Model model) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser.isAuthenticated()) {
				currentUser.logout();
			}
			String passwordAsMD5 = PasswordEncoder.encode(user.getPassword());
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), passwordAsMD5);
			currentUser.login(token); // 登录
			AdminUser loginUser = adminUserService.getByUsername(user.getUsername());
			CurrentUserHelper.setCurrentUser(loginUser);
			Session session = SecurityUtils.getSubject().getSession();
		} catch (UnknownAccountException e) {
			request.setAttribute("loginFilureReason", "UnknownAccount");
			request.setAttribute("loginFilureInfo", translateException(e, user));
			return login();
		} catch (IncorrectCredentialsException e) {
			request.setAttribute("loginFilureReason", "IncorrectCredentials");
			request.setAttribute("loginFilureInfo", translateException(e, user));
			return login();
		} catch (DisabledAccountException e) {
			request.setAttribute("loginFilureReason", "DisabledAccount");
			request.setAttribute("loginFilureInfo", translateException(e, user));
			return login();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return index(model);

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, String username, String nickname,
			Integer sex) {
		Page<AdminUser> page = new Page<AdminUser>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
		}
		try {
			if (StringUtils.isNotEmpty(username)) {
				page.addParam("username", URLDecoder.decode(username, "UTF-8"));
			}
			if (StringUtils.isNotEmpty(nickname)) {
				page.addParam("nickname", URLDecoder.decode(nickname, "UTF-8"));
			}
			if (null != sex) {
				page.addParam("sex", sex);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		page = adminUserService.queryPage(page);
		model.addAttribute("page", page);
		return "adminuser/list";

	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("user", CurrentUserHelper.getCurrentUser());
		return "index";

	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() {
		return "form";

	}

	@RequestMapping(value = "/interface", method = RequestMethod.GET)
	public String interfaces() {
		return "interface";

	}

	@RequestMapping(value = "/editors", method = RequestMethod.GET)
	public String editors() {
		return "editors";

	}

	@RequestMapping(value = "/buttons", method = RequestMethod.GET)
	public String buttons() {
		return "buttons";

	}

	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public String stats() {
		return "stats";

	}

	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public String calendar() {
		return "calendar";

	}

	@RequestMapping(value = "/tables", method = RequestMethod.GET)
	public String tables() {
		return "tables";

	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard() {
		return "dashboard";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		try {
			Subject subject = SecurityUtils.getSubject();
			if (subject.isAuthenticated()) {
				subject.logout();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return login();
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public WebResultInfoWrapper updatePassword(AdminUser user, String oldPassword) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			AdminUser u = CurrentUserHelper.getCurrentUser();
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser.isAuthenticated()) {
				currentUser.logout();
			}
			String oldPasswordAsMd5 = PasswordEncoder.encode(oldPassword);
			if (!oldPasswordAsMd5.equals(u.getPassword())) {
				webResultInfoWrapper.failed();
				webResultInfoWrapper.setMessage("原始密码不正确，请重新输入或联系管理员！");
				return webResultInfoWrapper;
			}
			String passwordAsMD5 = PasswordEncoder.encode(user.getPassword());
			u.setPassword(passwordAsMD5);
			adminUserService.update(u);

			UsernamePasswordToken token = new UsernamePasswordToken(u.getUsername(), passwordAsMD5);
			currentUser.login(token); // 登录

			AdminUser loginUser = adminUserService.getByUsername(u.getUsername());
			CurrentUserHelper.setCurrentUser(loginUser);
			webResultInfoWrapper.addResult("user", loginUser);
			Session session = SecurityUtils.getSubject().getSession();
			System.out.println(session.getId());
			webResultInfoWrapper.addResult("JSESSIONID", session.getId());
		} catch (Exception e) {
			e.printStackTrace();
			webResultInfoWrapper.setState(WebResultInfoWrapper.FAILED);
			webResultInfoWrapper.setMessage(e.getMessage());
		}
		return webResultInfoWrapper;
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public WebResultInfoWrapper resetPassword(AdminUser user) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			AdminUser u = adminUserService.getByUsername(user.getUsername());

			String passwordAsMD5 = PasswordEncoder.encode(user.getPassword());
			u.setPassword(passwordAsMD5);
			adminUserService.update(u);
		} catch (Exception e) {
			e.printStackTrace();
			webResultInfoWrapper.setState(WebResultInfoWrapper.FAILED);
			webResultInfoWrapper.setMessage(e.getMessage());
		}
		return webResultInfoWrapper;
	}

	@RequestMapping(value = "/exist", method = RequestMethod.POST)
	@ResponseBody
	public WebResultInfoWrapper exist(AdminUser user) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			AdminUser u = new AdminUser();
			u.setUsername(user.getUsername());
			if (adminUserService.notExistUser(u)) {
				webResultInfoWrapper.addResult("isExist", false);
				return webResultInfoWrapper;
			}
			webResultInfoWrapper.addResult("isExist", true);
		} catch (Exception e) {
			e.printStackTrace();
			webResultInfoWrapper.setState(WebResultInfoWrapper.FAILED);
			webResultInfoWrapper.setMessage(e.getMessage());
		}
		return webResultInfoWrapper;
	}

	@RequestMapping(value = "/passwordValidate", method = RequestMethod.POST)
	@ResponseBody
	public WebResultInfoWrapper password(AdminUser user) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			AdminUser u = CurrentUserHelper.getCurrentUser();
			String passwordAsMD5 = PasswordEncoder.encode(user.getPassword());
			if (u.getPassword().equals(passwordAsMD5)) {
				webResultInfoWrapper.addResult("isRight", true);
				return webResultInfoWrapper;
			}
			webResultInfoWrapper.addResult("isRight", false);
		} catch (Exception e) {
			e.printStackTrace();
			webResultInfoWrapper.setState(WebResultInfoWrapper.FAILED);
			webResultInfoWrapper.setMessage(e.getMessage());
		}
		return webResultInfoWrapper;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "adminuser/add";
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public WebResultInfoWrapper check(Model model, AdminUser user) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();

		String username = "";
		try {
			username = URLDecoder.decode(user.getUsername(), "UTF-8");
			user.setUsername(username);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (!adminUserService.notExistUser(user)) {
			webResultInfoWrapper.failed();
			webResultInfoWrapper.addResult("msg", "用户名已存在，请重新输入！");
			return webResultInfoWrapper;
		}
		webResultInfoWrapper.success();
		return webResultInfoWrapper;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, AdminUser user) {
		user.setPassword("123");// 新增用户时密码默认为123
		if (adminUserService.notExistUser(user)) {
			user.setCreateOn(new Date().getTime());
			adminUserService.save(user.encodePassword());
		}
		return list(model, 1, null, null, null, null);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public WebResultInfoWrapper register(AdminUser user) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			if (adminUserService.notExistUser(user)) {
				user.setCreateOn(new Date().getTime());
				adminUserService.save(user.encodePassword());
				return webResultInfoWrapper;
			}
			throw new UserExistException();
		} catch (UserExistException e) {
			webResultInfoWrapper.setState(WebResultInfoWrapper.FAILED);
			webResultInfoWrapper.setMessage(translateException(e, user));
		} catch (Exception e) {
			e.printStackTrace();
			webResultInfoWrapper.setState(WebResultInfoWrapper.FAILED);
			webResultInfoWrapper.setMessage(e.getMessage());
		}
		return webResultInfoWrapper;

	}

	@RequestMapping(value = "{id}/update", method = RequestMethod.GET)
	public String update(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("user", adminUserService.get(id));
		return "adminuser/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, AdminUser user) {
		try {
			AdminUser u = adminUserService.get(user.getId());

			user.setUsername(u.getUsername());
			user.setPassword(u.getPassword());
			user.setAuths(u.getAuths());
			user.setRoles(u.getRoles());

			adminUserService.update(user);
			CurrentUserHelper.setCurrentUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list(model, 1, null, null, null, null);
	}

	@RequestMapping(value = "{id}/delete")
	public String delete(Model model, @PathVariable("id") Integer id) {
		AdminUser user = new AdminUser();
		user.setId(id);
		adminUserService.delete(user);
		return list(model, 1, null, null, null, null);
	}

	private String translateException(Exception e, AdminUser u) {
		if (e instanceof IncorrectCredentialsException) { // 密码不正确异常
			return "用户密码不正确";
		}
		if (e instanceof UserExistException) { // 密码不正确异常
			return "用户名已存在";
		}
		if (e instanceof UnknownAccountException) {
			return "未知登录用户";
		}
		if (e instanceof DisabledAccountException) {
			return "帐号已被锁定";
		}
		e.printStackTrace();
		return "未知异常，请联系管理员";
	}

	@RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
	@ResponseBody
	public WebResultInfoWrapper unauthorized() {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		webResultInfoWrapper.setState(WebResultInfoWrapper.AUTH_FAILED);
		return webResultInfoWrapper;
	}

	@RequestMapping(value = "/changeheadPortrait", method = RequestMethod.GET)
	@ResponseBody
	public WebResultInfoWrapper changeheadPortrait(Integer userId, String headPortrait) {
		AdminUser user = adminUserService.get(userId);
		if (null != user) {
			user.setHeadPortrait(headPortrait);
		}
		adminUserService.update(user);
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		webResultInfoWrapper.setState(WebResultInfoWrapper.SUCCESS);
		return webResultInfoWrapper;
	}

}
