package com.youyisi.admin.infrastructure.shiro;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.youyisi.admin.application.user.UserService;
import com.youyisi.admin.domain.user.Role;
import com.youyisi.admin.domain.user.User;


public class ShiroRealm extends AuthorizingRealm{

	private final static Logger LOG = LoggerFactory.getLogger(ShiroRealm.class);
	
	@Autowired
	private UserService userService;
	
	public ShiroRealm() {
		setName("ShiroCasRealm"); // This name must match the name in the User
								// class's getPrincipals() method
	//	setCredentialsMatcher(new Sha256CredentialsMatcher());
	}
	
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = token.getUsername();
		try {
			if(StringUtils.isBlank(username)){
				throw new AccountException("can not handle this login");
			}
			User user = userService.getByUsername(username);
			checkUser(user, username);
			return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
		} catch (Exception e) {
			throw translateAuthenticationException(e);
		}
	}
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		String username = (String)getAvailablePrincipal(principals);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			User user = userService.getByUsername(username);
			Set<String> rolesAsString = user.getRolesAsString();
			info.addRoles(rolesAsString);
			if(user.hasAuths()){
				info.addStringPermissions(user.getAuthAsString());
			}
			for(Role role : user.getRoles()){
				info.addStringPermissions(role.getAuthsAsString());
			}
		return info;
	}

	
	
	/**
	 * 
	 */
	public void clearCache() {
		Subject subject = SecurityUtils.getSubject();
		clearCache(subject.getPrincipals());
		LOG.info("成功清理 "+subject.getPrincipal().toString()+" 相关缓存信息...");
	}

	/**
	 * 清理认证缓存
	 */
	public void clearCachedAuthenticationInfo() {
		Subject subject = SecurityUtils.getSubject();
		clearCachedAuthenticationInfo(subject.getPrincipals());
		LOG.info("成功清理 "+subject.getPrincipal().toString()+" 认证缓存信息...");
	}
	
	/**
	 * 清理权限缓存
	 */
	public void clearCachedAuthorizationInfo() {
		Subject subject = SecurityUtils.getSubject();
		clearCachedAuthorizationInfo(subject.getPrincipals());
		LOG.info("成功清理 "+subject.getPrincipal().toString()+" 授权缓存信息...");
	}
	/**
	 * 
	 * @param usrename
	 */
	public void clearCachedAuthorizationInfo(String username){
		SimplePrincipalCollection principals = new SimplePrincipalCollection(username, getName());
		clearCachedAuthorizationInfo(principals);
		LOG.info("成功清理 "+principals.toString()+" 授权缓存信息...");
	}
	/**
	 * 获得用户权限
	 * @return
	 */
	public AuthorizationInfo getAuthorizationInfo(){
		Subject subject = SecurityUtils.getSubject();
		return getAuthorizationInfo(subject.getPrincipals());
	}

	/**
	 * 异常转换
	 * @param e
	 * @return
	 */
	private AuthenticationException translateAuthenticationException(Exception e) {
		if (e instanceof AuthenticationException) {
			return (AuthenticationException) e;
		}
		if(e instanceof DisabledAccountException){
			return (DisabledAccountException)e;
		}
		if(e instanceof UnknownAccountException){
			return (UnknownAccountException)e;
		}
		return new AuthenticationException(e);
	}
	/**
	 * 检查用户
	 * @param user
	 * @param username
	 */
	private void checkUser(User user,String username){
		if(null == user){
			throw new UnknownAccountException(username + " can not find "+username+" from system");
		}
	}
	
}
