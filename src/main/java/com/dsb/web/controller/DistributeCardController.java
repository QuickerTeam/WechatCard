package com.dsb.web.controller;

import java.lang.reflect.Array;
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
import com.dsb.weChat.serviceImpl.ManageCardServiceImpl;

@Controller
@RequestMapping(value = "/DistributeCard")
public class DistributeCardController {
	private BatchCard batchCard;
	private ManageCardServiceImpl manageCardService = new ManageCardServiceImpl();
	private JSONObject receiveJson;// 接收微信返回的信息
	private Response2Web response = new Response2Web();
	private List<SimpleCardInfo> cardInfoList;
	private String str;// 用于临时存放字符串
	private JSONObject json;// 用于临时存放json

	@RequestMapping(value = "/AllCard")
	@ResponseBody
	public Object AllCard() {// 显示所有卡片供商家选择
		batchCard.setOffset(0);
		batchCard.setCount(50);
		json = new JSONObject(batchCard);// 将批量查询bean传给服务器
		str = manageCardService.batchGet(StaticConstant.accessToken,
				json.toString());
		receiveJson = new JSONObject(str);
		if (receiveJson.getBoolean("status")) {
			// 接收成功
			// cardIdArray用于储存cardId
			JSONArray cardIdArray = receiveJson
					.getJSONArray("card_id_list");
			for (int i = 0; i < cardIdArray.length(); i++) {
				json = new JSONObject(cardIdArray.get(i));
				str = manageCardService.batchGet(StaticConstant.accessToken,
						json.toString());
				receiveJson = new JSONObject(str);
				boolean b = UsedMethod.write2SimpleCardInfo(json,
						cardInfoList.get(i));
				if (b) {
					response.setCode(true);
				} else {
					response.setCode(false);
					return response;
				}
			}
			response.setMsg(new JSONArray(cardInfoList).toString());
			System.out.println(response.getMsg());
		} else {
			response.setCode(false);
			response.setMsg("无法获取到已创建的卡券");
			return response;
		}
		return response;
	}
}
