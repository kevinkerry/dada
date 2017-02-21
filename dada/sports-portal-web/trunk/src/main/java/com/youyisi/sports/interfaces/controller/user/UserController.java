package com.youyisi.sports.interfaces.controller.user;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.activity.ActivityServiceRemote;
import com.youyisi.app.soa.remote.coupon.UserCouponServiceRemote;
import com.youyisi.app.soa.remote.user.UserServiceRemote;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;
import com.youyisi.sports.constant.Constants;
import com.youyisi.sports.constant.SystemMessage;
import com.youyisi.sports.domain.activity.Activity;
import com.youyisi.sports.domain.sms.SmsMessage;
import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.domain.user.UserLessInfo;
import com.youyisi.sports.domain.user.UserMoreInfo;
import com.youyisi.sports.gexinpush.PushToSingleHelper;
import com.youyisi.sports.gexinpush.TransmissionContent;
import com.youyisi.sports.interfaces.controller.BaseController;
import com.youyisi.sports.interfaces.helper.RedisClient;
import com.youyisi.sports.utils.DES3Helper;
import com.youyisi.sports.utils.DateUtil;
import com.youyisi.sports.utils.SmsMessageHelper;
import com.youyisi.sports.utils.SportUtil;
import com.youyisi.sports.utils.StrUtil;

/**
 * @author shuye
 * @time 2016-05-10
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserServiceRemote userServiceRemote;
	@Autowired
	private ActivityServiceRemote activityServiceRemote;
	@Autowired
	private UserCouponServiceRemote userCouponServiceRemote;

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper list(Integer currentPage, Integer pageSize,String token,Long activityId) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			Page<UserLessInfo> page = new Page<UserLessInfo>();
			if (null != currentPage) {
				page.setCurrentPage(currentPage);
			}
			if (null != pageSize) {
				page.setPageSize(pageSize);
			} else {
				page.setPageSize(10);
			}
			page.addParam("time", DateUtil.getBeforeDate(-3));
			page.addParam("id", user.getId());
			page.addParam("activityId", activityId);
			page = userServiceRemote.queryPageForPush(page);
			String _currentPage = RedisClient.get(Constants.INVITE_MESSAGE_KEY+DateUtil.getDateStr());
			if(StringUtils.isBlank(_currentPage)){
				currentPage = 0;
			}else{
				currentPage = Integer.parseInt(_currentPage);
				currentPage = currentPage%page.getTotalPages();
			}
			/*Random r = new Random();
			currentPage = r.nextInt(page.getTotalPages());*/
			page.setCurrentPage(currentPage);
			page = userServiceRemote.queryPageForPush(page);
			webResultInfoWrapper.addResult("page",page);
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/sendmessage", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper sendmessage(Integer currentPage,Integer pageSize,String token,Long activityId) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			Page<User> page = new Page<User>();
			if (null != currentPage) {
				page.setCurrentPage(currentPage);
			}
			if (null != pageSize) {
				page.setPageSize(pageSize);
			} else {
				page.setPageSize(10);
			}
			page.addParam("time", DateUtil.getBeforeDate(-3));
			page.addParam("id", user.getId());
			page.addParam("activityId", activityId);
			page = userServiceRemote.queryPage(page);
			RedisClient.increment(Constants.INVITE_MESSAGE_KEY+DateUtil.getDateStr(), 1l);
			for(User u:page.getResult()){
				pushMessage(user,u,activityId);
			}
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	private void pushMessage(User sender,User user,Long activityId) {
		if(user.getActivityInviteRemind()==null||user.getActivityInviteRemind().intValue()==0){
			TransmissionContent content = new TransmissionContent();
			content.setTitle(sender.getNickname()+"邀请您一起来接力赛,我的邀请码是:"+sender.getUsercode());
			content.setType("RELAY_INVITE");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("code",sender.getUsercode());
			param.put("activityId",activityId);
			content.setEntity(param);
			content.setToUserId(user.getId());
			PushToSingleHelper.push(user, content);
		}
		
	}

	@ResponseBody
	@RequestMapping(value = "/tokenerror", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper tokenerror() {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		webResultInfoWrapper.setState(TOKEN_ERROR);
		webResultInfoWrapper.setMessage(SystemMessage.TOKENERROR_TEXT);
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper login(UserMoreInfo user) {
		WebResultInfoWrapper result = new WebResultInfoWrapper();
		try {
			if (user.getUsername() != null && user.getClientType() != null&& user.getUserType() != null) {
				if (user.getUserType() == 1) {
					if (user.getPassword() == null) {
						result.setState(ERROR);
						result.setMessage(SystemMessage.PARAMETERDEFECT_TEXT);
						return result;
					}
				}
				UserMoreInfo u = userServiceRemote.login(user);
				if (u.getToken() != null) {
					u = setPassword(u);
					result.addResult("user", u);
					result.setState(SUCCEED);
				} else if (u.getStatus() == -1) {
					result.setState(ERROR);
					result.setMessage(SystemMessage.NAMEORPWDERROR_TEXT);
				}
			} else {
				result.setState(ERROR);
				result.setMessage(SystemMessage.PARAMETERDEFECT_TEXT);
			}
		} catch (SoaException e) {
			log.error("params:" + gson.toJson(user) + "----message:" + e.getMessage());
			result.setState(ERROR);
			result.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return result;
	}

	private UserMoreInfo setPassword(UserMoreInfo u) {
		if(StringUtils.isBlank(u.getPassword())){
			u.setPassword("no");
		}else{
			u.setPassword("yes");
		}
		if(StringUtils.isBlank(u.getPayPassword())){
			u.setPayPassword("no");
		}else{
			u.setPayPassword("yes");
		}
		return u;
	}

	@ResponseBody
	@RequestMapping(value = "/getMyUserInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper getMyUserInfo(String token) {
		WebResultInfoWrapper result = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			if (user != null) {
				user.setToken(token);
				UserMoreInfo us = userServiceRemote.getMyUserInfo(user);
				us = setPassword(us);
				us.setToken(token);
				result.addResult("user", us);
				result.setState(SUCCEED);
			}
		} catch (Exception e) {
			log.error("params:" + token + "----message:" + e.getMessage());
			result.setState(ERROR);
			result.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper detail(@PathVariable("id") Long id) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			webResultInfoWrapper.addResult("user", userServiceRemote.get(id));
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper update(User User) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			userServiceRemote.update(User);
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	/**
	 * 发送验证码
	 * 
	 * @param mobile
	 * @param type
	 *            1. 注册 2修改密码 3绑定手机号 4修改手机号
	 * @return ResponseModel
	 */
	@ResponseBody
	@Deprecated
	@RequestMapping(value = "/sendCode", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper sendCode(String mobile, Integer type, String token) {
		WebResultInfoWrapper result = new WebResultInfoWrapper();
		try {
			if (type != null) {
				User user = null;
				String code = SportUtil.getAuthCode();
				if (mobile != null && type != 4) {
					user = userServiceRemote.getByUserNameOrMobile(mobile);
					if (type == 1) {
						if (user == null) {
							RedisClient.delete(Constants.REGISTERCODE + mobile);
							RedisClient.set(Constants.REGISTERCODE + mobile, code, 15);
							// 调用短信接口 发送验证码
							sendSmsMessage(mobile, code);
						} else {
							result.setState(ERROR);
							result.setMessage(SystemMessage.REGISTERERROR_TEXT1);
						}
					}
					if (type == 2) {
						if (user != null) {
							RedisClient.delete(Constants.FINDPWDCODE + mobile);
							RedisClient.set(Constants.FINDPWDCODE + mobile, code, 15);
							// 调用短信接口 发送验证码
							sendSmsMessage(mobile, code);
						} else {
							result.setState(ERROR);
							result.setMessage(SystemMessage.REGISTERERROR_TEXT2);
						}
					}
					if (type == 3) {
						if (user == null) {
							RedisClient.delete(Constants.BINDINGMOBILECODE + mobile);
							RedisClient.set(Constants.BINDINGMOBILECODE + mobile, code, 15);
							// 调用短信接口 发送验证码
							sendSmsMessage(mobile, code);
						} else {
							result.setState(ERROR);
							result.setMessage(SystemMessage.REGISTERERROR_TEXT1);
						}
					}
				} else if (type == 4) {
					if (token != null) {
						user = getUserByToken(token);
						RedisClient.delete(Constants.BINDINGMOBILECODE + user.getMobile());
						RedisClient.set(Constants.BINDINGMOBILECODE + user.getMobile(), code, 15);
						// 调用短信接口 发送验证码
						sendSmsMessage(user.getMobile(), code);
					} else {
						result.setState(ERROR);
						result.setMessage(SystemMessage.PARAMETERDEFECT_TEXT);
					}
				} else {
					result.setState(ERROR);
					result.setMessage(SystemMessage.PARAMETERDEFECT_TEXT);
				}

			} else {
				result.setState(ERROR);
				result.setMessage(SystemMessage.PARAMETERDEFECT_TEXT);
			}
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			result.setState(ERROR);
			result.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return result;
	}
	
	
	/**
	 * 发送验证码
	 * 
	 * @param mobile
	 * @param type
	 *            1. 注册 2修改密码 3绑定手机号 4修改手机号
	 * @return ResponseModel
	 */
	@ResponseBody
	@RequestMapping(value = "/sendCodes", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper sendCodes(String mobile, Integer type, String token) {
		WebResultInfoWrapper result = new WebResultInfoWrapper();
		try {
			mobile = DES3Helper.decode(mobile);
		} catch (Exception e1) {
			result.setState(ERROR);
			result.setMessage("非法请求！请更新到最新版本");
			return result;
		}
		try {
			if (type != null) {
				User user = null;
				String code = SportUtil.getAuthCode();
				if (mobile != null && type != 4) {
					user = userServiceRemote.getByUserNameOrMobile(mobile);
					if (type == 1) {
						if (user == null) {
							RedisClient.delete(Constants.REGISTERCODE + mobile);
							RedisClient.set(Constants.REGISTERCODE + mobile, code, 15);
							// 调用短信接口 发送验证码
							sendSmsMessage(mobile, code);
						} else {
							result.setState(ERROR);
							result.setMessage(SystemMessage.REGISTERERROR_TEXT1);
						}
					}
					if (type == 2) {
						if (user != null) {
							RedisClient.delete(Constants.FINDPWDCODE + mobile);
							RedisClient.set(Constants.FINDPWDCODE + mobile, code, 15);
							// 调用短信接口 发送验证码
							sendSmsMessage(mobile, code);
						} else {
							result.setState(ERROR);
							result.setMessage(SystemMessage.REGISTERERROR_TEXT2);
						}
					}
					if (type == 3) {
						if (user == null) {
							RedisClient.delete(Constants.BINDINGMOBILECODE + mobile);
							RedisClient.set(Constants.BINDINGMOBILECODE + mobile, code, 15);
							// 调用短信接口 发送验证码
							sendSmsMessage(mobile, code);
						} else {
							result.setState(ERROR);
							result.setMessage(SystemMessage.REGISTERERROR_TEXT1);
						}
					}
				} else if (type == 4) {
					if (token != null) {
						user = getUserByToken(token);
						RedisClient.delete(Constants.BINDINGMOBILECODE + user.getMobile());
						RedisClient.set(Constants.BINDINGMOBILECODE + user.getMobile(), code, 15);
						// 调用短信接口 发送验证码
						sendSmsMessage(user.getMobile(), code);
					} else {
						result.setState(ERROR);
						result.setMessage(SystemMessage.PARAMETERDEFECT_TEXT);
					}
				} else {
					result.setState(ERROR);
					result.setMessage(SystemMessage.PARAMETERDEFECT_TEXT);
				}

			} else {
				result.setState(ERROR);
				result.setMessage(SystemMessage.PARAMETERDEFECT_TEXT);
			}
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			result.setState(ERROR);
			result.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return result;
	}

	private void sendSmsMessage(String mobile, String code) {
		SmsMessage message = new SmsMessage();
		message.setTelephone(mobile);
		message.setBody("尊敬的用户，您的验证码为：" + code + "，请尽快完成验证（此验证码15分钟内有效），回复TD退订");
		SmsMessageHelper.send(message);
	}

	/**
	 * 用户注册
	 * 
	 * @return ResponseModel
	 */
	@ResponseBody
	@RequestMapping(value = "/validateCode", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper validateCode(String mobile, String code,Integer type) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			if (mobile != null && code != null&& type != null) {
				String cd = null;
				if (type == 1) {
					cd = RedisClient.get(Constants.REGISTERCODE + mobile);
				}
				else if (type == 2) {
					cd = RedisClient.get(Constants.FINDPWDCODE + mobile);
				}
				else if (type == 3) {
				
					cd = RedisClient.get(Constants.BINDINGMOBILECODE + mobile);
				
			} else  if(type == 4) {
				
				cd = RedisClient.get(Constants.BINDINGMOBILECODE + mobile);
			}
				// 获取Redis中保存的验证码
				if (cd == null || !cd.equals(code)) {
					webResultInfoWrapper.setState(ERROR);
					webResultInfoWrapper.setMessage(SystemMessage.AUTHCODEERROR_TEXT);
				}
			} else {
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage(SystemMessage.PARAMETERDEFECT_TEXT);
			}
		} catch (Exception e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	/**
	 * 用户注册
	 * 
	 * @return ResponseModel
	 */
	@ResponseBody
	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper register(User user, String code) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			if (user.getUsername() != null && user.getPassword() != null) {
				// 获取Redis中保存的验证码
				String cd = RedisClient.get(Constants.REGISTERCODE + user.getUsername());
				if (cd != null && cd.equals(code)) {
					user.setPassword(StrUtil.getMD532Str(user.getPassword()));
					User us = userServiceRemote.getByUserNameOrMobile(user.getUsername());
					if (us == null) {
						user.setMobile(user.getUsername());
						user.setNickname("dada_"+SportUtil.getAuthCode());
						user.setUserType(1);
						Long usercode = RedisClient.increment(Constants.USER_CODE_KEY, 1l);
						user.setUsercode(usercode+"");
						userServiceRemote.save(user);
						RedisClient.delete(Constants.REGISTERCODE + user.getUsername());
					} else {
						webResultInfoWrapper.setState(ERROR);
						webResultInfoWrapper.setMessage(SystemMessage.REGISTERERROR_TEXT1);
					}
				} else {
					webResultInfoWrapper.setState(ERROR);
					webResultInfoWrapper.setMessage(SystemMessage.AUTHCODEERROR_TEXT);
				}
			} else {
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage(SystemMessage.PARAMETERDEFECT_TEXT);
			}
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	/**
	 * 绑定手机号
	 * 
	 * @return ResponseModel
	 */
	@ResponseBody
	@RequestMapping(value = "/bindingMobile", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper bindingMobile(String token, String mobile, String password, String code) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			if (mobile != null && code != null) {
				User user = getUserByToken(token);
				// 获取Redis中保存的验证码
				String cd = RedisClient.get(Constants.BINDINGMOBILECODE + mobile);
				if (cd != null && cd.equals(code)) {
					if (user.getMobile() == null) {
						if (password != null) {
							user.setMobile(mobile);
							user.setPassword(StrUtil.getMD532Str(password));
						} else {
							webResultInfoWrapper.setState(ERROR);
							webResultInfoWrapper.setMessage(SystemMessage.PARAMETERDEFECT_TEXT);
							return webResultInfoWrapper;
						}
					} else {
						user.setMobile(mobile);
					}
					user = userServiceRemote.bindingMobile(user);
					RedisClient.set(Constants.USER_KEY + token, user);
					RedisClient.delete(Constants.BINDINGMOBILECODE + mobile);
				} else {
					webResultInfoWrapper.setState(ERROR);
					webResultInfoWrapper.setMessage(SystemMessage.AUTHCODEERROR_TEXT);
				}
			} else {
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage(SystemMessage.PARAMETERDEFECT_TEXT);
			}
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

	/**
	 * 更新用户信息
	 * 
	 * @return WebResultInfoWrapper
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper updateUserInfo(User user) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User us = getUserByToken(user.getToken());
			us.setHeadPortrait(user.getHeadPortrait());
			us.setNickname(user.getNickname());
			us.setSex(user.getSex());
			us.setBirthday(user.getBirthday());
			us.setHeight(user.getHeight());
			us.setWeight(user.getWeight());
			us.setCity(user.getCity());
			us.setProvince(user.getProvince());
			us.setAge(user.getAge());
			userServiceRemote.update(us);
			RedisClient.set(Constants.USER_KEY + user.getToken(), us);
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateUserSet", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper updateUserSet(User user) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User us = getUserByToken(user.getToken());
			us.setPerformanceRemind(user.getPerformanceRemind());
			us.setBigDealsRemind(user.getBigDealsRemind());
			us.setVoiceRemind(user.getVoiceRemind());
			us.setCheerRemind(user.getCheerRemind());
			us.setActivityInviteRemind(user.getActivityInviteRemind());
			us.setPraiseRemind(user.getPraiseRemind());
			userServiceRemote.update(us);
			RedisClient.set(Constants.USER_KEY + user.getToken(), us);
		} catch (SoaException e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	

	/**
	 * 退出登陆
	 * 
	 * @param token
	 * @return WebResultInfoWrapper
	 */
	@ResponseBody
	@RequestMapping(value = "/logOut", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper logOut(String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			if (user != null) {
				// 清除token
				RedisClient.deletes(Constants.USER_KEY + user.getId() + "_*");
			}
		} catch (Exception e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	/**
	 * 修改密码
	 * 
	 * @param token
	 * @return WebResultInfoWrapper
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePassword", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper updatePassword(String token,String oldpassword,String  newPassword) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			if(user.getPassword().equals(StrUtil.getMD532Str(oldpassword))){
				user.setPassword(StrUtil.getMD532Str(newPassword));
				userServiceRemote.update(user);
				RedisClient.set(Constants.USER_KEY + token, user);
			}else{
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("原密码不正确");
			}
		} catch (Exception e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	/**
	 * 设置支付密码
	 * 
	 * @param token
	 * @return WebResultInfoWrapper
	 */
	@ResponseBody
	@RequestMapping(value = "/setPayPassword", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper updatePayPassword(String token,String payPassword) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			    User user = getUserByToken(token);
				user.setPayPassword(StrUtil.getMD532Str(payPassword));
				userServiceRemote.update(user);
				RedisClient.set(Constants.USER_KEY + token, user);
		} catch (Exception e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	/**
	 * 判断支付密码是否正确
	 * 
	 * @param token
	 * @return WebResultInfoWrapper
	 */
	@ResponseBody
	@RequestMapping(value = "/rightPayPassword", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper rightPayPassword(String token,String payPassword) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			    User user = getUserByToken(token);
			    if(user.getPayPassword().equals(StrUtil.getMD532Str(payPassword))){
			    	webResultInfoWrapper.addResult("payPassword", "right");
			    }else{
			    	webResultInfoWrapper.addResult("payPassword", "error");
			    }
		} catch (Exception e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	/**
	 * 修改密码
	 * 
	 * @param token
	 * @return WebResultInfoWrapper
	 */
	@ResponseBody
	@RequestMapping(value = "/forgetPassword", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper forgetPassword(String mobile,String password) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = userServiceRemote.getByUserNameOrMobile(mobile);
			if(user!=null){
				user.setPassword(StrUtil.getMD532Str(password));
				userServiceRemote.update(user);
			}else{
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("账户不存在");
			}
		} catch (Exception e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	
	/**
	 * 修改密码
	 * 
	 * @param token
	 * @return WebResultInfoWrapper
	 */
	@ResponseBody
	@RequestMapping(value = "/updateClientId", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper updateClientId(String clientId,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			user.setClientId(clientId);
			userServiceRemote.update(user);
			RedisClient.set(Constants.USER_KEY + token, user);
		} catch (Exception e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}
	
	/**
	 * 修改密码
	 * 
	 * @param token
	 * @return WebResultInfoWrapper
	 */
	@ResponseBody
	@RequestMapping(value = "/saveRecommendCode", method = { RequestMethod.GET, RequestMethod.POST })
	public WebResultInfoWrapper saveRecommendCode(String recommendCode,Long activityId,String token) {
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			User user = getUserByToken(token);
			if(StringUtils.isBlank(recommendCode)||activityId==null){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("参数不全！");
				return webResultInfoWrapper;
			}
			if(user.getUsercode()!=null&&user.getUsercode().equals(recommendCode)){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("自己不能邀请自己哦！");
				return webResultInfoWrapper;
			}
			if(user.getRecommendCode()!=null){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("您已经领取过了！");
				return webResultInfoWrapper;
			}
			
			User u = userServiceRemote.getByUsercode(recommendCode);
			if(u==null){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("推荐码错误！");
				return webResultInfoWrapper;
			}
			
			if(u.getClientId()!=null&&u.getClientId().equals(user.getClientId())){
				webResultInfoWrapper.setState(ERROR);
				webResultInfoWrapper.setMessage("自己的多个账号不能相互邀请哦！");
				return webResultInfoWrapper;
			}
				Activity a = activityServiceRemote.get(activityId);
				if(a.getBeginTime()<user.getRegisterTime()){
					user.setRecommendCode(recommendCode);
					userServiceRemote.update(user);
					RedisClient.set(Constants.USER_KEY + token, user);
					userCouponServiceRemote.issue(a,user);
				}else{
					webResultInfoWrapper.setState(ERROR);
					webResultInfoWrapper.setMessage("您不是新用户");
				}
				
			
		} catch (Exception e) {
			log.error("----message:" + e.getMessage());
			webResultInfoWrapper.setState(ERROR);
			webResultInfoWrapper.setMessage(SystemMessage.INTERFACEEXCEPTION_TEXT);
		}
		return webResultInfoWrapper;
	}

}
