package com.youyisi.vote.interfaces.controller.user;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.lang.Page;
import com.youyisi.vote.application.user.RedisUserService;
import com.youyisi.vote.application.user.UserService;
import com.youyisi.vote.domain.user.User;
import com.youyisi.vote.infrastructure.cache.redis.RedisClient;
import com.youyisi.vote.infrastructure.util.DateUtil;
import com.youyisi.vote.infrastructure.util.SignUtil;
import com.youyisi.vote.infrastructure.util.Ticket;

/**
 * @author shuye
 * @time 2014年5月8日
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private RedisUserService redisUserService;
	@Autowired
	private UserService userService;
	
	private int pageSize = 10;
	
	private String url="http://vote.dadasports.cn";
	private static final Log logger = LogFactory.getLog(UserController.class);
	
	@RequestMapping(value="/share",method = RequestMethod.GET)
	public void share(Long id,HttpServletResponse response){
		try {
			String redirect_url = "http://vote.dadasports.cn/user/"+id+"/share";
			redirect_url = URLEncoder.encode(redirect_url, "utf-8");
			response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf8bd7ea02a023bb7&redirect_uri="+redirect_url+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/shareQueryPage",method = RequestMethod.GET)
	public void shareQueryPage(String fromUserName,HttpServletResponse response){
		try {
			String redirect_url = url + "/user/queryPage";
			redirect_url = URLEncoder.encode(redirect_url, "utf-8");
			// https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=http%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect

			response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf8bd7ea02a023bb7&redirect_uri="
					+ redirect_url
					+ "&response_type=code&scope=snsapi_base&state="
					+ fromUserName + "#wechat_redirect");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	@RequestMapping(value="/voteuser",method = RequestMethod.GET)
	public void voteuser(Long id,String fromUserName,HttpServletResponse response){
		try {
			String redirect_url = "http://vote.dadasports.cn/user/"+id+"/voteuser";
			redirect_url = URLEncoder.encode(redirect_url, "utf-8");
			response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf8bd7ea02a023bb7&redirect_uri="+redirect_url+"&response_type=code&scope=snsapi_base&state="+fromUserName+"#wechat_redirect");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/{id}/voteuser")
	public String voteuser(@PathVariable("id") Long id,@RequestParam(value="code", required=false) String code,@RequestParam(value="state", required=false) String fromUser,Model model){
		System.out.println("code:"+code);
		if (StringUtils.isNotBlank(code)) {
			String openId = Ticket.getOpenId(code);
			model.addAttribute("openId", openId);
			System.out.println("openId:"+openId);
			if(StringUtils.isNotBlank(code)&& StringUtils.isBlank(openId)){
				return "redirect:/user/voteuser?id="+id+"&fromUserName="+fromUser;
			}
			if (StringUtils.isNotBlank(openId)&& StringUtils.isNotBlank(fromUser)) {
				String _fromUser = (String) RedisClient.get("vote:vote:openid"+ openId);
				String _openId = (String) RedisClient.get("vote:vote:fromuser"+ fromUser);
				if (StringUtils.isBlank(_fromUser) && StringUtils.isBlank(_openId)) {
					RedisClient.set("vote:vote:openid" + openId, fromUser);
					RedisClient.set("vote:vote:fromuser" + fromUser, openId);
				}
			}
		}

		User u = redisUserService.get(id+"");
		if(u==null){
			u = userService.get(id);
			redisUserService.save(u.getId()+"", u);
		}
		model.addAttribute("user", u);
		model.addAttribute("currentPage", 1);
		model.addAttribute("sign", SignUtil.getSin(url+"/user/"+id+"/voteuser?code="+code+"&state="+fromUser));
		return "detail";
	}
	
	
	@RequestMapping(value="/{id}/share")
	public String detailShare(@PathVariable("id") Long id,@RequestParam(value="code", required=false) String code,Model model){
		logger.info("code:" + code );
		String openId = "";
		if (StringUtils.isNotBlank(code)) {
			 openId = Ticket.getOpenId(code);
			 if(StringUtils.isNotBlank(code)&& StringUtils.isBlank(openId)){
					return "redirect:/user/share?id="+id;
				}
			model.addAttribute("openId", openId);
		}

		User u = redisUserService.get(id+"");
		if(u==null){
			u = userService.get(id);
			redisUserService.save(u.getId()+"", u);
		}
		model.addAttribute("user", u);
		model.addAttribute("openId", openId);
		model.addAttribute("currentPage", 1);
		model.addAttribute("sign", SignUtil.getSin(url+"/user/"+id+"/share?code="+code+"&state=123"));
		return "detail";
	}
	
	@RequestMapping(value="/regist",method = RequestMethod.POST)
	@ResponseBody
	public User regist(User user){
		user.setVoteNum(0l);
		user.setState(0);
		user.setCreateTime(System.currentTimeMillis());
		userService.regist(user);
		return user;
	}
	@RequestMapping(value="/regist",method = RequestMethod.GET)
	public String regist(Model model,@RequestParam(value="state", required=false) String fromUser,@RequestParam(value="code", required=false) String code){
		logger.info("fromUser:"+fromUser);
		if (StringUtils.isNotBlank(code)) {
			String openId = Ticket.getOpenId(code);
			model.addAttribute("openId", openId);
			if (StringUtils.isNotBlank(openId)&& StringUtils.isNotBlank(fromUser)) {
				String _fromUser = (String) RedisClient.get("vote:vote:openid"+ openId);
				String _openId = (String) RedisClient.get("vote:vote:fromuser"+ fromUser);
				if (StringUtils.isBlank(_fromUser) && StringUtils.isBlank(_openId)) {
					RedisClient.set("vote:vote:openid" + openId, fromUser);
					RedisClient.set("vote:vote:fromuser" + fromUser, fromUser);
				}
			}
		}
		return "regist";
	}
	
	@RequestMapping(value="/userregist",method = RequestMethod.GET)
	public String userregist(Model model,@RequestParam(value="openId", required=false) String openId){
		model.addAttribute("openId", openId);
		return "regist";
	}
	
	@RequestMapping(value="/gonggao",method = RequestMethod.GET)
	public String gonggao(Model model){
		return "gonggao";
	}
	
	@RequestMapping(value="/{id}/detail")
	public String detail(@PathVariable("id") Long id,@RequestParam(value="openId", required=false) String openId,@RequestParam(value="currentPage", required=false) Integer currentPage,Model model){
		
		User u = redisUserService.get(id+"");
		if(u==null){
			u = userService.get(id);
			redisUserService.save(u.getId()+"", u);
		}
		model.addAttribute("user", u);
		model.addAttribute("openId", openId);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("sign", SignUtil.getSin(url+"/user/"+id+"/detail?openId="+openId+"&currentPage="+currentPage));
		return "detail";
	}
	
	@RequestMapping(value="/vote")
	@ResponseBody
	public String vote(String wechat,Long userId){
		Long date = DateUtil.getLongDate("2015-07-15 23:59:59", "yyyy-MM-dd HH:mm:ss");
		Long today = System.currentTimeMillis();
		if(today>date){
			return "over";
		}
		System.out.println("wechat:"+wechat);
		return userService.vote(wechat, userId);
	}
	
	@RequestMapping(value="/queryPage")
	public String queryPage(@RequestParam(value="currentPage", required=false) Integer currentPage,@RequestParam(value="state", required=false) String fromUser,@RequestParam(value="code", required=false) String code,HttpServletRequest request,Model model){
		logger.info("code:" + code + "fromUser:" + fromUser);
		if (StringUtils.isNotBlank(code)) {
			String openId = Ticket.getOpenId(code);
			 if(StringUtils.isNotBlank(code)&& StringUtils.isBlank(openId)){
					return "redirect:/user/shareQueryPage?fromUserName="+fromUser;
			}
			model.addAttribute("openId", openId);
			logger.info("openId:"+openId);
			if (StringUtils.isNotBlank(openId)&& StringUtils.isNotBlank(fromUser)) {
				String _fromUser = (String) RedisClient.get("vote:vote:openid"+ openId);
				String _openId = (String) RedisClient.get("vote:vote:fromuser"+ fromUser);
				if (StringUtils.isBlank(_fromUser) && StringUtils.isBlank(_openId)) {
					RedisClient.set("vote:vote:openid" + openId, fromUser);
					RedisClient.set("vote:vote:fromuser" + fromUser, openId);
				}
			}
		}

		Page<User> page = new Page<User>();
		if (currentPage != null) {
			page.setCurrentPage(currentPage);
		}
		page.setPageSize(pageSize);
		page = userService.queryPage(page);
		model.addAttribute("page", page);
		// model.addAttribute("fromUser", fromUser);
		model.addAttribute("sign", SignUtil.getSin(url+"/user/queryPage?"+request.getQueryString()));
		return "list";
	}
	
	@RequestMapping(value="/list")
	public String list(@RequestParam(value="currentPage", required=false) Integer currentPage,@RequestParam(value="openId", required=false) String openId,Model model,HttpServletRequest request){
		
		model.addAttribute("openId", openId);
		Page<User> page = new Page<User>();
		if (currentPage != null) {
			page.setCurrentPage(currentPage);
		}
		page.setPageSize(pageSize);
		page = userService.queryPage(page);
		model.addAttribute("page", page);
		// model.addAttribute("fromUser", fromUser);
		
		model.addAttribute("sign", SignUtil.getSin(url+"/user/list?"+request.getQueryString()));
		return "list";
	}
	
	@RequestMapping(value="/test-list")
	public String testList(@RequestParam(value="currentPage", required=false) Integer currentPage,@RequestParam(value="openId", required=false) String openId,Model model){
		
		model.addAttribute("openId", openId);
		Page<User> page = new Page<User>();
		if (currentPage != null) {
			page.setCurrentPage(currentPage);
		}
		page.setPageSize(pageSize);
		page = userService.queryPage(page);
		model.addAttribute("page", page);
		// model.addAttribute("fromUser", fromUser);
		
		model.addAttribute("sign", SignUtil.getSin("${ctx}/user/queryPage"));
		return "test_list";
	}
	

	@RequestMapping(value="/queryPage.json")
	@ResponseBody
	public Page<User> query(@RequestParam(value="currentPage", required=false) Integer currentPage,@RequestParam(value="keyword", required=false)String keyword,Model model){
		logger.info("currentPage:"+currentPage);
		Page<User> page = new Page<User>();
		if(currentPage!=null){
			page.setCurrentPage(currentPage);
		}
		if(StringUtils.isNotBlank(keyword)){
			page.addParam("keyword", keyword);
		}
		page.setPageSize(pageSize);
		page = userService.queryPage(page );
		return page;
	}
	
	@RequestMapping(value="/{id}/admin")
	@ResponseBody
	public String adminVote(@PathVariable("id") Long id,@RequestParam(value="admin", required=false) String admin,@RequestParam(value="password", required=false) String password,@RequestParam(value="votes", required=false) Long votes,HttpServletRequest request){
		logger.info("admin ip:"+getIpAddr(request)+"value:"+votes);
		if(StringUtils.isNotBlank(admin)&&StringUtils.isNotBlank(password)){
			String _password = (String) RedisClient.get("vote:admin:"+admin);
			if(_password.equals(password)){
				User u = userService.get(id);
				Long votenum = RedisClient.increment("vote:vote:votenum:"+u.getId(),votes);
				u.setVoteNum(votenum);
				redisUserService.save(u.getId()+"", u);
				redisUserService.lpush(u.getId());
			}
		}
		return "fail";
	}
	
	private  String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
	
	@RequestMapping(value="/admin/list")
	public String adminList(@RequestParam(value="keyword", required=false)String keyword,@RequestParam(value="currentPage", required=false)Integer currentPage,@RequestParam(value="admin", required=false) String admin,@RequestParam(value="password", required=false) String password,HttpSession session,Model model){
		logger.info("session:"+session.getId());
		if(StringUtils.isBlank(admin)){
			admin = (String) session.getAttribute("adminuser");
			password = (String) session.getAttribute("password");
		}
		model.addAttribute("keyword", keyword);
		if(StringUtils.isNotBlank(admin)&&StringUtils.isNotBlank(password)){
			String _password = (String) RedisClient.get("vote:admin:manager:"+admin);
			if(_password.equals(password)){
				session.setAttribute("adminuser",admin);
				session.setAttribute("password",password);
				Page<User> page = new Page<User>();
				if (currentPage != null) {
					page.setCurrentPage(currentPage);
				}
				if(StringUtils.isNotBlank(keyword)){
					page.addParam("keyword", keyword);
				}
				page = userService.queryPage(page);
				model.addAttribute("page", page);
				return "admin-list";	
			}
		}
		return "error";
	}

	
	@RequestMapping(value="/{id}/displaynone")
	@ResponseBody
	public String displaynone(@PathVariable("id") Long id,HttpSession session,Model model){
			String admin = (String) session.getAttribute("adminuser");
			String password = (String) session.getAttribute("password");
		if(StringUtils.isNotBlank(admin)&&StringUtils.isNotBlank(password)){
			String _password = (String) RedisClient.get("vote:admin:manager:"+admin);
			if(_password.equals(password)){
				User u = userService.get(id);
				u.setState(-1);
				userService.update(u);
				return "success";	
			}
		}
		return "fail";
	}
	

	@RequestMapping(value="/init")
	@ResponseBody
	public String  init(){
		Page<User> page = new Page<User>();
		page = userService.queryPage(page );
		for(int currentPage = 1;currentPage<=page.getTotalPages();currentPage++){
			page.setCurrentPage(currentPage);
			page = userService.queryPage(page );
			for(User u:page.getResult()){
				redisUserService.save(u.getId()+"", u);
				RedisClient.set("vote:vote:votenum:"+u.getId(),u.getVoteNum());
			}
		}
		return "success";
		
	}
	
	
}
