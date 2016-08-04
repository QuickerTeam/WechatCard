package com.dsb.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsb.domain.BatchCard;
import com.dsb.domain.Response2Web;
import com.dsb.domain.SimpleCardInfo;
import com.dsb.utils.StaticConstant;
import com.dsb.utils.UsedMethod;
import com.dsb.weChat.service.ManageCardService;
import com.dsb.weChat.serviceImpl.ManageCardServiceImpl;

@Controller
@RequestMapping(value = "/DistributeCard")
public class DistributeCardController {
	private BatchCard batchCard = new BatchCard();
	private ManageCardService manageCardService = new ManageCardServiceImpl();
	private JSONObject receiveJson;// 接收微信返回的信息
	private Response2Web response = new Response2Web();
	private List<SimpleCardInfo> cardInfoList = new ArrayList<SimpleCardInfo>();
	private String card_id;
	private String str;// 用于临时存放字符串
	private JSONObject json;// 用于临时存放json

	@RequestMapping(value = "/AllCard")
	@ResponseBody
	public Object AllCard() {// 显示所有卡片供商家选择
		UsedMethod.log("/AllCard", 1);
		cardInfoList.clear();// 删除list的所有元素
		batchCard.setOffset(0);
		batchCard.setCount(50);
		json = new JSONObject(batchCard);// 将批量查询bean传给服务器
		UsedMethod.log("json=" + json, 1);
		UsedMethod.log("StaticConstant.accessToken="
				+ StaticConstant.accessToken, 1);
		str = manageCardService.batchGet(json.toString());
		receiveJson = new JSONObject(str);
		UsedMethod.log("receiveJson=" + receiveJson, 1);
		if (receiveJson.getBoolean("status")) {
			// 接收成功
			// cardIdArray用于储存cardId
			JSONArray cardIdArray = receiveJson.getJSONArray("card_id_list");
			for (int i = 0; i < cardIdArray.length(); i++) {
				// 将cardid装入json
				card_id = (String) cardIdArray.get(i);
				json = new JSONObject("{\"card_id\":" + card_id + "}");
				UsedMethod.log("receiveJson=" + receiveJson, 1);
				UsedMethod.log("cardIdArray=" + cardIdArray, 1);
				UsedMethod.log("json=" + json, 1);
				str = manageCardService.queryCardInfo(json.toString());
				receiveJson = new JSONObject(str);
				SimpleCardInfo e = new SimpleCardInfo();
				e.setCard_id(card_id);// 写入card_id
				boolean b = UsedMethod.write2SimpleCardInfo(receiveJson, e);
				cardInfoList.add(e);
				UsedMethod.log("----" + i + "----", 1);
				if (b) {
					response.setCode(true);
				} else {
					response.setCode(false);
					response.setMsg("未能将数据写入卡券对象中");
					return response;
				}
			}
			response.setMsg(new JSONArray(cardInfoList).toString());
		} else {
			response.setCode(false);
			response.setMsg("无法获取到已创建的卡券");
			return response;
		}
		UsedMethod.log(response.getMsg(), 2);
		return response;
	}
	
	@RequestMapping(value = "/AllCard")
	@ResponseBody
	public Object 
}
