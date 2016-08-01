package com.dsb.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
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
import com.dsb.weChat.service.CreateCardService;
import com.dsb.weChat.service.GetAccess;
import com.dsb.weChat.serviceImpl.CreateCardServiceImpl;
import com.dsb.weChat.serviceImpl.GetAccessImpl;

/**
 * 创建券
 * 
 * @author Time
 * 
 */
@Controller
@RequestMapping(value = "/CreateTicket")
public class CreateTicket extends HttpServlet {
	private static final long serialVersionUID = -2094822104804129409L;
	private File logoFile;
	private String logo_url;
	private String responseJson;
	private JSONObject groupTicketJson;// 用于返回给微信创建卡券

	@RequestMapping(value = "/UpLoadLogo")
	@ResponseBody
	public String getLogo(HttpServletRequest request)
			throws IllegalStateException {// 上传logo
		MultipartHttpServletRequest re = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = re.getFile("logo_url");
		String fileName = multipartFile.getOriginalFilename();
		String filePath = "E:\\myeclipse\\workspace\\WechatCard\\src\\main\\webapp\\upload\\"
				+ fileName;
		logoFile = new File(filePath);
		Response2Web response = new Response2Web();

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
		JSONObject j = new JSONObject(response);
		String json = j.toString();
		System.out.println(json);
		return json;
	}

	@RequestMapping(value = "/GroupTicket_input")
	public String groupTicketInput() {// 创建团购券页面
		System.out.println("/GroupTicket_input");
		return "mainPage";
	}

	@RequestMapping(value = "/GroupTicket_save")
	@ResponseBody
	public String groupTicketSave(GroupTicket groupTicket,HttpServletRequest requst) {// 创建团购券
		// 获取access_token
		System.out.println("description="+requst.getParameter("description"));
		System.out.println("deal_detail="+requst.getParameter("deal_detail"));
		System.out.println("/GroupTicket_save");
		Response2Web response = new Response2Web();
		GetAccess getAccess = new GetAccessImpl();
		JSONObject accessTokenJson = new JSONObject(getAccess.getAccessToken(
				StaticConstant.appid, StaticConstant.secret));
		System.out.println("1");
		if (accessTokenJson != null) {// 获取到json
			StaticConstant.accessToken = accessTokenJson.optString(
					"access_token", "");
			if (StaticConstant.accessToken.equals("")) {
				response.setCode(false);
				response.setMsg("accessToken error");
				// response转换成json字符串
				JSONObject j = new JSONObject(response);
				responseJson = j.toString();
				System.out.println(responseJson);
				return responseJson;
			}
		}
		System.out.println("2");
		// 获取logo_url
		CreateCardService cardCreateService = new CreateCardServiceImpl();
		if (logoFile == null) {
			response.setCode(false);
			response.setMsg("logo未获取到");
			// response转换成json字符串
			JSONObject j = new JSONObject(response);
			responseJson = j.toString();
			System.out.println(responseJson);
			return responseJson;
		}
		// 获取logo的url
		JSONObject json = new JSONObject(cardCreateService.uploadCardLogo(
				StaticConstant.accessToken, logoFile));
		logo_url = json.getString("url");
		System.out.println("json===" + json);
		// 将url封装到bean中
		groupTicket.setLogo_url(logo_url);
		// json接收是否成功的消息
		groupTicketJson = new JSONObject(groupTicket);
		System.out.println("00000" + groupTicketJson.toString());
		json = new JSONObject(cardCreateService.createCard(
				StaticConstant.accessToken, groupTicketJson.toString()));
		System.out.println(groupTicketJson.toString());
		if (json.getInt("errcode")==0) {// 创建成功
			System.out.println("创建卡券成功");
			response.setCode(true);
		} else {
			System.out.println("创建卡券失败 errcode="+json.getInt("errcode"));
			response.setCode(false);
		}
		// response转换成json字符串
		JSONObject j = new JSONObject(response);
		responseJson = j.toString();
		System.out.println(responseJson);
		return responseJson;
		/*
		 * if (GeneralMethod.Request2TicketInfo(groupTicket, request)) {// 创建成功
		 * return ""; } else {// 创建失败(bug页面) return ""; }
		 */
	}
}