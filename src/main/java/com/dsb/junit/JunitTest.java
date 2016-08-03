package com.dsb.junit;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsb.domain.BatchCard;
import com.dsb.domain.Response2Web;
import com.dsb.domain.SimpleCardInfo;
import com.dsb.utils.StaticConstant;
import com.dsb.utils.UsedMethod;
import com.dsb.weChat.serviceImpl.ManageCardServiceImpl;

public class JunitTest {

	@Test
	public void test2() {
		System.out.println("a");
	}

	public void test3() {
		BatchCard batchCard = null;
		ManageCardServiceImpl manageCardService = new ManageCardServiceImpl();
		JSONObject receiveJson;// 接收微信返回的信息
		Response2Web response = new Response2Web();
		List<SimpleCardInfo> cardInfoList = null;
		String str;// 用于临时存放字符串
		JSONObject json;// 用于临时存放json
		System.out.println("1");
		batchCard.setOffset(0);
		batchCard.setCount(50);
		json = new JSONObject(batchCard);// 将批量查询bean传给服务器
		str = "{\"status\": true,\"card_id_list\": [\"ph_gmt7cUVrlRk8swPwx7aDyF-pg\",\"abcd\"],\"total_num\": 2}";
		receiveJson = new JSONObject(str);
		System.out.println("receiveJson=" + receiveJson);
		if (receiveJson.getBoolean("status")) {
			// 接收成功
			// cardIdArray用于储存cardId
			JSONArray cardIdArray = receiveJson.getJSONArray("card_id_list");
			for (int i = 0; i < cardIdArray.length(); i++) {
				json = new JSONObject(cardIdArray.get(i));
				receiveJson = new JSONObject(str);
				boolean b = UsedMethod.write2SimpleCardInfo(json,
						cardInfoList.get(i));
				if (b) {
					response.setCode(true);
				} else {
					response.setCode(false);

				}
			}
			response.setMsg(new JSONArray(cardInfoList).toString());
			System.out.println(response.getMsg());
		} else {
			response.setCode(false);
			response.setMsg("无法获取到已创建的卡券");

		}

	}

}
