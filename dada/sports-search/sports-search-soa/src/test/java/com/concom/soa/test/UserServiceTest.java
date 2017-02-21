package com.concom.soa.test;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.youyisi.soa.remote.user.UserServiceRemote;

public class UserServiceTest {
	
	public static void main(String[] args) throws MalformedURLException {
		String url = "http://localhost:8083/sports-search-soa/topic";
		com.caucho.hessian.client.HessianProxyFactory factory = new HessianProxyFactory();
		factory.setHessian2Reply(true);
		factory.setHessian2Request(false);
		UserServiceRemote hello = (UserServiceRemote) factory.create(UserServiceRemote.class, url);
		if(hello != null) {
			try {
				 //hello.get(431l);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
