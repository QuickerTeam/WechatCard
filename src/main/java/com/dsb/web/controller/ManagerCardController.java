package com.dsb.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsb.domain.BatchCard;
import com.dsb.domain.CardId;
import com.dsb.domain.Response2Web;
import com.dsb.utils.UsedMethod;
import com.dsb.weChat.service.ManageCardService;
import com.dsb.weChat.serviceImpl.ManageCardServiceImpl;

@Controller
public class ManagerCardController {
	private BatchCard batchCard = new BatchCard();
	private ManageCardService manageCardService = new ManageCardServiceImpl();
	private JSONObject receiveJson;// 接收微信返回的信息
	private Response2Web response = new Response2Web();
	// private List<SimpleCardInfo> cardInfoList = new
	// ArrayList<SimpleCardInfo>();
	private List<String> cardList = new ArrayList<String>();
	private CardId card_id = new CardId();
	private String str;// 用于临时存放字符串
	private JSONObject json;// 用于临时存放json

	@RequestMapping(value = "/ManagerCard")
	@ResponseBody
	public Object ManagerCard() {
		UsedMethod.log("/ManagerCard", 1);
		cardList.clear();// 删除list的所有元素
		// 每次获得的卡券数量
		batchCard.setOffset(0);
		batchCard.setCount(50);
		json = new JSONObject(batchCard);// 将批量查询bean传给服务器
		UsedMethod.log("json=" + json, 1);
		str = manageCardService.batchGet(json.toString());
		receiveJson = new JSONObject(str);
		UsedMethod.log("receiveJson=" + receiveJson, 1);
		if (receiveJson.getBoolean("status")) {
			// 接收成功
			// cardIdArray用于储存cardId
			JSONArray cardIdArray = receiveJson.getJSONArray("card_id_list");
			for (int i = 0; i < cardIdArray.length(); i++) {
				// 将cardid装入json
				card_id.setCard_id((String) cardIdArray.get(i));
				json = new JSONObject(card_id);
				UsedMethod.log("receiveJson=" + receiveJson, 1);
				UsedMethod.log("cardIdArray=" + cardIdArray, 1);
				UsedMethod.log("json=" + json, 1);
				// 获取某张卡券的所有信息
				str = manageCardService.queryCardInfo(json.toString());
				cardList.add(str);
				UsedMethod.log("----" + i + "----", 1);
			}
			response.setCode(true);
			response.setMsg(new JSONArray(cardList).toString());
		} else {
			response.setCode(false);
			response.setMsg("无法获取到已创建的卡券");
			return response;
		}
		UsedMethod.log(response.getMsg(), 2);
		return response;
	}

	@RequestMapping(value = "/ManagerCard/RemoveCard")
	@ResponseBody
	public Object oneCard(HttpServletRequest request) {// 删除某一张卡券
		UsedMethod.log("card_id=" + request.getParameter("card_id"), 2);
		str = request.getParameter("card_id");
		card_id.setCard_id(str);
		json = new JSONObject(card_id);
		UsedMethod.log(json.toString(), 1);
		str = manageCardService.deleteCard(json.toString());
		UsedMethod.log(str, 1);
		response.setCode(true);
		return response;
	}
}
