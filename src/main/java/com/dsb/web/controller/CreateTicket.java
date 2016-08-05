package com.dsb.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dsb.domain.GroupTicket;
import com.dsb.domain.Response2Web;
import com.dsb.utils.StaticConstant;
import com.dsb.utils.UsedMethod;
import com.dsb.weChat.service.CreateCardService;
import com.dsb.weChat.serviceImpl.CreateCardServiceImpl;

/**
 * 创建券
 * 
 * @author Time
 * 
 */
@Controller
@RequestMapping(value = "/CreateTicket")
public class CreateTicket {
	private GroupTicket groupTicket = new GroupTicket();
	private File logoFile;
	private String logo_url;
	private JSONObject groupTicketJson;// 用于返回给微信创建卡券
	private Response2Web response = new Response2Web();// 用于给web返回信息

	@RequestMapping(value = "/UpLoadLogo")
	@ResponseBody
	public Object getLogo(HttpServletRequest request)
			throws IllegalStateException {// 上传logo
		UsedMethod.log("/UpLoadLogo", 1);
		MultipartHttpServletRequest re = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = re.getFile("logo_url");
		String fileName = multipartFile.getOriginalFilename();
		String filePath = "E:\\myeclipse\\workspace\\WechatCard\\src\\main\\webapp\\upload\\"
				+ fileName;
		logoFile = new File(filePath);
		try {
			multipartFile.transferTo(logoFile);
			System.out.println(filePath);
			response.setCode(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			response.setCode(false);
			e.printStackTrace();
			System.out.println("error");
		}
		return response;
	}

	@RequestMapping(value = "/GroupTicket_input")
	public String groupTicketInput() {// 创建团购券页面
		UsedMethod.log("/GroupTicket_input", 1);
		return "mainPage";
	}

	// @RequestMapping(value = "/aaaa")
	// @ResponseBody
	// public Object aa() {
	// Response2Web response = new Response2Web();
	// response.setCode(true);
	// response.setMsg("aaaaaaaa");
	// return response;
	// }

	@RequestMapping(value = "/GroupTicket_save")
	@ResponseBody
	public Object groupTicketSave(HttpServletRequest request) {// 创建团购券
		UsedMethod.log("/GroupTicket_save", 1);
		try {
			request.setCharacterEncoding("UTF-8");
			UsedMethod.log("改变编码成功", 2);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			UsedMethod.log("改变编码出错", 2);
		}
		UsedMethod.log(request.getParameter("title"), 2);
		// 获取access_token
		if (StaticConstant.accessToken.equals("")) {
			// accessToken出错
			response.setCode(false);
			response.setMsg("accessToken error");
			// 返回response
			return response;
		}
		UsedMethod.write2GroupTicket(groupTicket, request);
		// 获取logo_url
		CreateCardService cardCreateService = new CreateCardServiceImpl();
		if (logoFile == null) {
			response.setCode(false);
			response.setMsg("logo未获取到");
			// 返回response
			return response;
		}
		JSONObject json = new JSONObject(
				cardCreateService.uploadCardLogo(logoFile));

		if (!json.getBoolean("status")) { // 获取图片url出错
			response.setCode(false);
			response.setMsg("获取图片url出错"); // 返回response
			return response;
		}

		logo_url = json.getString("url");
		// 将url封装到bean中
		GroupTicket.Card.Groupon.Base_info base_info = groupTicket.getCard()
				.getGroupon().getBase_info();
		base_info.setLogo_url(logo_url);
		// 向微信发出创建卡券申请
		groupTicketJson = new JSONObject(groupTicket);
		json = new JSONObject(cardCreateService.createCard(groupTicketJson
				.toString()));

		if (json.getBoolean("status")) {// 创建成功 System.out.println("创建卡券成功");
			UsedMethod.log("card_id=" + json.getString("card_id"), 2);
			response.setCode(true);
		} else {
			UsedMethod.log("创建卡券失败 errcode=" + json.getInt("errcode"), 2);
			response.setCode(false);
		}
		return response;
	}
}