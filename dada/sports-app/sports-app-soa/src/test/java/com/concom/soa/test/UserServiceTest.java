package com.concom.soa.test;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.youyisi.app.soa.remote.user.UserServiceRemote;
import com.youyisi.sports.domain.user.User;

public class UserServiceTest {
	
	public static void main(String[] args) throws MalformedURLException {
		String url = "http://localhost:8081/sports-app-soa/user";
		com.caucho.hessian.client.HessianProxyFactory factory = new HessianProxyFactory();
		factory.setHessian2Reply(true);
		factory.setHessian2Request(false);
		UserServiceRemote hello = (UserServiceRemote) factory.create(UserServiceRemote.class, url);
		if(hello != null) {
			try {
				User entity = new User();
				entity.setUsername("shuye");
				User user = hello.save(entity);
				System.out.println(user.getId()+user.getUsername());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
