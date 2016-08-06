package com.dsb.web.controller;

import com.dsb.domain.*;
import com.dsb.utils.StaticConstant;
import com.dsb.utils.UsedMethod;
import com.dsb.weChat.service.DistributeCardService;
import com.dsb.weChat.service.ManageCardService;
import com.dsb.weChat.serviceImpl.DistributeCardServiceImpl;
import com.dsb.weChat.serviceImpl.ManageCardServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/DistributeCard")
public class DistributeCardController {
	private BatchCard batchCard = new BatchCard();
	private ManageCardService manageCardService = new ManageCardServiceImpl();
	private JSONObject receiveJson;// 接收微信返回的信息
	private Response2Web response = new Response2Web();
	private List<SimpleCardInfo> cardInfoList = new ArrayList<SimpleCardInfo>();
	private CardId card_id = new CardId();
	private String str;// 用于临时存放字符串
	private JSONObject json;// 用于临时存放json

	@RequestMapping(value = "/AllCard")
	@ResponseBody
	public Object AllCard() {// 显示所有卡片供商家选择
		UsedMethod.log("/AllCard", 1);
		cardInfoList.clear();// 删除list的所有元素
		// 每次获得的卡券数量
		batchCard.setOffset(0);
		batchCard.setCount(50);
		batchCard.getStatus_list().clear();// 清除数组里面的数据再使用
		batchCard.getStatus_list().add("CARD_STATUS_VERIFY_OK");// 只显示审核通过以及待审核的卡券
		batchCard.getStatus_list().add("CARD_STATUS_NOT_VERIFY");
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
				card_id.setCard_id((String) cardIdArray.get(i));
				json = new JSONObject(card_id);
				UsedMethod.log("receiveJson=" + receiveJson, 1);
				UsedMethod.log("cardIdArray=" + cardIdArray, 1);
				UsedMethod.log("json=" + json, 1);
				// 获取某张卡券的所有信息
				str = manageCardService.queryCardInfo(json.toString());
				receiveJson = new JSONObject(str);
				SimpleCardInfo simpleCardInfo = new SimpleCardInfo();
				simpleCardInfo.setCard_id(card_id.getCard_id());// 写入card_id
				boolean b = UsedMethod.write2SimpleCardInfo(receiveJson,
						simpleCardInfo);
				// 获取卡券当前状态
				String status = null;// 储存卡券状态
				json = receiveJson.getJSONObject("card");
				if (json.getString("card_type").equals("GROUPON")) {
					// 团购券类型
					json = json.getJSONObject("groupon");
					json = json.getJSONObject("base_info");
					status = json.getString("status");
				} else {
					// 其他卡券类型
				}
				if (!status.equals("CARD_STATUS_DELETE")) {
					// 如果卡券状态为已删除将不会传给前端
					cardInfoList.add(simpleCardInfo);
				}
				UsedMethod.log(status, 1);
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

	@RequestMapping(value = "/OneCard")
	@ResponseBody
	public Object oneCard(HttpServletRequest request) {// 发放某一张卡券
		ShowQRCode qrCode = new ShowQRCode();
		UsedMethod.log("cardid=" + request.getParameter("card_id"), 2);
		DistributeCardService distributeCardService = new DistributeCardServiceImpl();
		str = request.getParameter("card_id");
		qrCode.getAction_info().getCard().setCard_id(str);
		json = new JSONObject(qrCode);
		str = distributeCardService.getQRCode(json.toString());
		UsedMethod.log(str, 2);
		UsedMethod.log("json=" + json.toString(), 2);
		json = new JSONObject(str);
		response.setCode(true);
		response.setMsg(json.getString("show_qrcode_url"));
		UsedMethod.log(response.getMsg(), 2);
		return response;
	}
}
