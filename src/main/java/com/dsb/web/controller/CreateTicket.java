package com.dsb.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsb.domain.GroupTicket;

@Controller
@RequestMapping(value = "/CreateTicket")
public class CreateTicket {
	@RequestMapping(value = "/GroupTicket_input")
	// 创建团购券页面
	public String groupTicketInput() {

		return "";
	}

	@RequestMapping(value = "/GroupTicket_input")
	// 创建团购券
	public String groupTicketSave(JSONObject json) {
		GroupTicket groupTicket = new GroupTicket();
		GroupTicket.sku sku = groupTicket.getSku();
		GroupTicket.date_info dateInfo = groupTicket.getDataInfo();
		try {// 尝试构造团购券
			groupTicket.setLogo_url(json.getString("logo_url"));
			groupTicket.setCode_type(json.getString("code_type"));
			groupTicket.setBrand_name(json.getString("brand_name"));
			groupTicket.setTitle(json.getString("title"));
			groupTicket.setSub_title(json.getString("sub_title"));
			groupTicket.setColor(json.getString("color"));
			groupTicket.setNotice(json.getString("notice"));
			groupTicket.setDescription(json.getString("description"));
			sku.setQuantity(json.getInt("quantity"));
			dateInfo.setType(json.getString("type"));
			if (dateInfo.getType() == "DATE_TYPE_FIX_TIME_RANGE ") {
				String str = "" + json.getInt("begin_time_year")
						+ json.getInt("begin_time_month")
						+ json.getInt("begin_time_day");// str来记录时间，之后转换成秒
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				millionSeconds = sdf.parse(str).getTime()/1000;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("error while creat groupTicket.");
		}
		return "";
	}
}