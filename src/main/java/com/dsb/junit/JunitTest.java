package com.dsb.junit;


import org.json.JSONObject;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsb.exception.AccessTokenException;
import com.dsb.utils.StaticConstant;
import com.dsb.weChat.service.GetAccess;
import com.dsb.weChat.serviceImpl.GetAccessImpl;


public class JunitTest {
	@Test
	public void test1() throws AccessTokenException {
		// 获取access_token
		GetAccess getAccess = new GetAccessImpl();
		System.out.println("00");
		JSONObject accessTokenJson = new JSONObject(getAccess.getAccessToken(
				StaticConstant.appid, StaticConstant.secret));
		System.out.println("11111");
		if (accessTokenJson != null) {// 获取到json
			StaticConstant.accessToken = accessTokenJson.optString(
					"access_token", "");
			if (StaticConstant.accessToken.equals("")) {
				throw new AccessTokenException();// 无法获取到access_token
			}
		}
	}
	
	@Test
	public void test2() {
		System.out.println("aaaaaaaaa");
	}
	
	
}
