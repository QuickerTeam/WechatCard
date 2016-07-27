package com.dsb.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsb.domain.GroupTicket;

@Controller
public class GeneralMethod {

	/**
	 * 将接收到的request数据传入到javabean中
	 * 
	 * @param groupTicket
	 * @param json
	 * @return 是否成功传入bean中
	 */
	/*public static Boolean Request2TicketInfo(GroupTicket groupTicket,
			HttpServletRequest request) {
		GroupTicket.sku sku = groupTicket.getSku();
		GroupTicket.date_info dateInfo = groupTicket.getDataInfo();
		try {// 尝试构造团购券
			InputStream logoUrl = new InputStream() {
				@Override
				public int read() throws IOException {
					// TODO Auto-generated method stub
					return 0;
				}
			};
			// groupTicket.setLogo_url(json.getString("logo_url"));
			groupTicket.setCode_type(json.getString("code_type"));
			groupTicket.setBrand_name(json.getString("brand_name"));
			groupTicket.setTitle(json.getString("title"));
			groupTicket.setSub_title(json.getString("sub_title"));
			groupTicket.setColor(json.getString("color"));
			groupTicket.setNotice(json.getString("notice"));
			groupTicket.setDescription(json.getString("description"));
			sku.setQuantity(json.getInt("quantity"));
			dateInfo.setType(json.getString("type"));
			if (dateInfo.getType() == "DATE_TYPE_FIX_TIME_RANGE ") {// 按时间段计算截止日期
				String str = "" + json.getInt("begin_time_year")
						+ json.getInt("begin_time_month")
						+ json.getInt("begin_time_day");// str来记录时间，之后转换成秒
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				dateInfo.setBegin_time(sdf.parse(str).getTime() / 1000 + "");
				str = "" + json.getInt("end_time_year")
						+ json.getInt("end_time_month")
						+ json.getInt("end_time_day");// str来记录时间，之后转换成秒
				dateInfo.setEnd_time(sdf.parse(str).getTime() / 1000 + "");
			} else {
				dateInfo.setFixed_term(json.getInt("fixed_term"));
			}
			try {// 是否有客服电话
				groupTicket.setServicePhone(json.getString("service_phone"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {// 是否有领券数量限制
				groupTicket.setGet_limit(json.getInt("get_limit"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("groupTicket=" + groupTicket);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error while creat groupTicket.");
			return false;
		}

	}*/
	
}
