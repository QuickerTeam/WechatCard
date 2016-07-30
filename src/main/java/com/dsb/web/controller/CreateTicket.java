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
	private File logoFile;// 商户上传的logo
	private String logo_url;// 服务器返回的商户logoURL
	private String responseJson;// 用于给前端返回信息

	@RequestMapping(value = "/UpLoadLogo")
	@ResponseBody
	public String getLogo(HttpServletRequest request)
			throws IllegalStateException {// 上传logo
		System.out.println("/UpLoadLogo");
		// 获取图片
		MultipartHttpServletRequest re = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = re.getFile("logo_url");
		String fileName = multipartFile.getOriginalFilename();
		String filePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "upload\\" + fileName;
		Response2Web response = new Response2Web();
		System.out.println(filePath);
		logoFile = new File(filePath);
		try {// 将文件储存到本地
			multipartFile.transferTo(logoFile);
			response.setCode(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			response.setCode(false);
			e.printStackTrace();
		}
		// 返回给前端信息
		JSONObject j = new JSONObject(response);
		String json = j.toString();
		return json;
	}

	@RequestMapping(value = "/GroupTicket_input")
	public String groupTicketInput() {// 创建团购券页面
		System.out.println("/GroupTicket_input");
		return "mainPage";
	}

	@RequestMapping(value = "/GroupTicket_save")
	@ResponseBody
	public String groupTicketSave(GroupTicket groupTicket) {// 创建团购券
		// 获取access_token
		System.out.println("/GroupTicket_save");
		Response2Web response = new Response2Web();
		GetAccess getAccess = new GetAccessImpl();
		JSONObject accessTokenJson = new JSONObject(getAccess.getAccessToken(
				StaticConstant.appid, StaticConstant.secret));
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
		System.out.println("3");
		System.out.println(logoFile.exists());
		// JSONObject json = new JSONObject(cardCreateService.uploadCardLogo(
		// logoFile, StaticConstant.accessToken));
		// System.out.println("00json=" + json);
		// logo_url = json.getString("url");
		// System.out.println("json=" + json);
		// groupTicket.setLogo_url(logo_url);
		System.out.println("4");
		response.setCode(true);
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