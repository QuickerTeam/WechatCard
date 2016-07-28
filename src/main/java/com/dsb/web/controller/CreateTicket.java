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
import com.dsb.utils.StaticConstant;
import com.dsb.weChat.service.CardCreateService;
import com.dsb.weChat.service.GetAccess;
import com.dsb.weChat.serviceImpl.CardCreateServiceImpl;
import com.dsb.weChat.serviceImpl.GetAccessImpl;


/**
 * 创建券
 * @author Time
 *
 */
@Controller
@RequestMapping(value = "/CreateTicket")
public class CreateTicket extends HttpServlet {
	private static final long serialVersionUID = -2094822104804129409L;
	private ServletInputStream logo;
	private String logo_url;
	@RequestMapping(value = "/UpLoadLogo")
	public String getLogo(HttpServletRequest request) {// 上传logo
		try {
			logo = request.getInputStream();
		} catch (IOException e) {
			// TODO 获取logo出错
			e.printStackTrace();
		}
		return "mainPage";
	}
	
	@RequestMapping(value = "/GroupTicket_input")
	public String groupTicketInput() {// 创建团购券页面
		return "mainPage";
	}

	@RequestMapping(value = "/GroupTicket_save")
	public String groupTicketSave(GroupTicket groupTicket) throws AccessTokenException{// 创建团购券
		// 获取access_token
		GetAccess getAccess =new GetAccessImpl();
		JSONObject accessTokenJson =new JSONObject(getAccess.getAccessToken(
				StaticConstant.appid, StaticConstant.secret)); 
		if (accessTokenJson != null) {// 获取到json
			StaticConstant.accessToken = accessTokenJson.optString(
					"access_token", "");
			if (StaticConstant.accessToken.equals("")){
				throw new AccessTokenException();// 无法获取到access_token
			}
		}
		//获取logo_url
		CardCreateService cardCreateService =new CardCreateServiceImpl();
		JSONObject json =new JSONObject(cardCreateService.uploadCardLogo(logo,
				StaticConstant.accessToken)); 
		logo_url=json.getString("url");
		groupTicket.setLogo_url(logo_url);
		
		return "";
		/*
		 * if (GeneralMethod.Request2TicketInfo(groupTicket, request)) {// 创建成功
		 * return ""; } else {// 创建失败(bug页面) return ""; }
		 */
	}
}