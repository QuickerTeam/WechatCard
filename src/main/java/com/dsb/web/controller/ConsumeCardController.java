package com.dsb.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsb.domain.CardCode;
import com.dsb.domain.Response2Web;
import com.dsb.utils.UsedMethod;
import com.dsb.weChat.service.CheckCardService;
import com.dsb.weChat.serviceImpl.CheckCardServiceImpl;

@Controller
public class ConsumeCardController {
	private CheckCardService checkCardService = new CheckCardServiceImpl();
	private Response2Web response = new Response2Web();
	private String str;// 用于临时存放字符串
	private JSONObject json;// 用于临时存放json

	@RequestMapping(value = "/ConsumeCard")
	@ResponseBody
	public Object consumeCard(HttpServletRequest request) {
		CardCode code = new CardCode();
		code.setCode(request.getParameter("code"));
		//code.setCode("068219271584");
		json = new JSONObject(code);
		str = checkCardService.queryCardStage(json.toString());
		json = new JSONObject(str);
		if (json.getBoolean("status")) {
			code.setCode(request.getParameter("code"));
			//code.setCode("068219271584");
			json = new JSONObject(code);
			checkCardService.consumeCard(json.toString());
			response.setCode(true);
			UsedMethod.log("核销成功", 1);
		} else {
			response.setCode(false);
			response.setMsg(json.getString("errmsg"));
		}
		return response;
	}
}