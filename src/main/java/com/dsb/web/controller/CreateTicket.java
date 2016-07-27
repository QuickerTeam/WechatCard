package com.dsb.web.controller;

import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsb.domain.GroupTicket;

@Controller
@RequestMapping(value = "/CreateTicket")
public class CreateTicket {
	@RequestMapping(value = "/GroupTicket_input")
	// �����Ź�ȯҳ��
	public String groupTicketInput() {

		return "";
	}

	@RequestMapping(value = "/GroupTicket_input")
	// �����Ź�ȯ
	public String groupTicketSave(JSONObject json) {
		GroupTicket groupTicket = new GroupTicket();
		GroupTicket.sku sku = groupTicket.getSku();
		GroupTicket.date_info dateInfo = groupTicket.getDataInfo();
		try {// ���Թ����Ź�ȯ
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
			if (dateInfo.getType() == "DATE_TYPE_FIX_TIME_RANGE ") {// ��ʱ��μ����ֹ����
				String str = "" + json.getInt("begin_time_year")
						+ json.getInt("begin_time_month")
						+ json.getInt("begin_time_day");// str����¼ʱ�䣬֮��ת������
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				dateInfo.setBegin_time(sdf.parse(str).getTime() / 1000 + "");
				str = "" + json.getInt("end_time_year")
						+ json.getInt("end_time_month")
						+ json.getInt("end_time_day");// str����¼ʱ�䣬֮��ת������
				dateInfo.setEnd_time(sdf.parse(str).getTime() / 1000 + "");
			} else {
				dateInfo.setFixed_term(json.getInt("fixed_term"));
			}
			try {//�Ƿ��пͷ��绰
				groupTicket.setServicePhone(json.getString("service_phone"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {//�Ƿ�����ȯ��������
				groupTicket.setGet_limit(json.getInt("get_limit"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("groupTicket="+groupTicket);
			return "";//��д�ɹ���jsp
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("error while creat groupTicket.");
			return "";//error
		}
	}
}