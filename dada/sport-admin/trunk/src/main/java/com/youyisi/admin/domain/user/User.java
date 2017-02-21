/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.youyisi.admin.domain.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.youyisi.lang.domain.BaseObject;
import com.youyisi.lang.helper.PasswordEncoder;

public class User  extends BaseObject<Integer>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5394552769206983498L;
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 头像
	 */
	private String headPortrait;
	/**
	 * 密码
	 */
    private String password;
    /**
	 * 邮箱
	 */
    private String email;
    /**
     * 创建时间
     */
    private long createOn;
    /**
     * 生日
     */
    private String birthday;
    
	/**
	 * 性别1:男，0：女，3：保密
	 */
	private Integer sex;
	
    /**
     * 角色
     */
	@JsonIgnore
    private Set<Role> roles = new HashSet<Role>();
    /**
     * 权限
     */
	@JsonIgnore
    private Set<Auth> auths = new HashSet<Auth>();
    
    /**
     * 用于转换role
     */
	@JsonIgnore
    private Map<String,Integer> roleTrans = new HashMap<String,Integer>();
    /**
     * 用于转换auth
     */
	@JsonIgnore
    private Map<String,Integer> authTrans = new HashMap<String,Integer>();
    
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    /**
     * Returns the password for this user.
     *
     * @return this user's password
     */
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    public Map<String, Integer> getRoleTrans() {
		return roleTrans;
	}
    
	public void setRoleTrans(Map<String, Integer> roleTrans) {
		this.roleTrans = roleTrans;
	}
	
	public Set<Auth> getAuths() {
		return auths;
	}
	public void setAuths(Set<Auth> auths) {
		this.auths = auths;
	}
	public Map<String, Integer> getAuthTrans() {
		return authTrans;
	}
	public void setAuthTrans(Map<String, Integer> authTrans) {
		this.authTrans = authTrans;
	}
	public User beforeCreate(){
		translateRole();
		translateAuth();
		return this;
	}
	
	/**
	 * 将装有role id的Map的值注入到role中
	 */
	private void translateRole() {
		Map<String,Integer> roleIds = getRoleTrans();
		if(roleIds.isEmpty()){
			return;
		}
		getRoles().clear();
		for(String key : roleIds.keySet()){
			Role role = new Role();
			role.setId(roleIds.get(key));
			getRoles().add(role);
		}
	}
	
	/**
	 * 将装有auth id的Map的值注入到auth中
	 */
	private void translateAuth(){
		Map<String,Integer> authIds = getAuthTrans();
		if(authIds.isEmpty()){
			return;
		}
		getAuths().clear();
		for(String key : authIds.keySet()){
			Auth auth = new Auth();
			auth.setId(authIds.get(key));
			getAuths().add(auth);
		}
	}
	/**
     * @return
     */
	@JsonIgnore
    public Set<String> getRolesAsString(){
    	Set<String> roles = new HashSet<String>();
    	for(Role role : getRoles()){
    		roles.add(role.getName());
    	}
    	return roles;
    }
    /**
     * 
     * @return
     */
	@JsonIgnore
    public Set<String> getAuthAsString(){
    	Set<String> auths = new HashSet<String>();
    	for(Auth auth : getAuths()){
    		auths.add(auth.getPermission());
    	}
    	return auths;
    }
    
    public boolean hasRoles(){
    	return !getRoles().isEmpty();
    }
    
    public boolean hasAuths(){
    	return !getAuths().isEmpty();
    }
    
    public User encodePassword(){
    	setPassword(PasswordEncoder.encode(getPassword()));
    	return this;
    }
	public long getCreateOn() {
		return createOn;
	}
	public void setCreateOn(long createOn) {
		this.createOn = createOn;
	}
	
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}


