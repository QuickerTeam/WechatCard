package com.dsb.web.controller;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsb.domain.GroupTicket;
import com.dsb.exception.AccessTokenException;
import com.dsb.utils.GeneralMethod;
import com.dsb.utils.StaticConstant;

@Controller
@RequestMapping(value = "/CreateTicket")
public class CreateTicket extends HttpServlet {
	private ServletInputStream logo;

	@RequestMapping(value = "/UpLoadLogo")
	public String getLogo(HttpServletRequest request) {// 上传logo
		try {
			logo = request.getInputStream();
		} catch (IOException e) {
			// TODO 获取logo出错
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/GroupTicket_input")
	public String groupTicketInput() {// 创建团购券页面
		return "mainPage";
	}

	@RequestMapping(value = "/GroupTicket_save")
	public String groupTicketSave(GroupTicket groupTicket) {// 创建团购券
		// 获取access_token
		JSONObject accessTokenJson = getAccess.getAccessToken(
				StaticConstant.appid, StaticConstant.secret);
		if (accessTokenJson != null) {// 获取到json
			StaticConstant.accessToken = accessTokenJson.optString(
					"access_token", "");
			if (StaticConstant.accessToken.equals(""))
				throw new AccessTokenException();// 无法获取到access_token
		} else {
			throw new AccessTokenException();// 无法获取到access_token
		}
		
		/*
		 * if (GeneralMethod.Request2TicketInfo(groupTicket, request)) {// 创建成功
		 * return ""; } else {// 创建失败(bug页面) return ""; }
		 */
	}
}