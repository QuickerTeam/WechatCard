package com.dsb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.dsb.domain.GroupTicket;
import com.dsb.domain.SimpleCardInfo;

/**
 * 一些常用的方法
 * 
 * @author Time
 * 
 */
public class UsedMethod {
	/**
	 * 
	 * @param groupTicket团购券实例
	 * @param request来自web的请求
	 * @return 是否成功写入进javabean中
	 */
	public static boolean write2GroupTicket(GroupTicket groupTicket,
			HttpServletRequest request) {

		// 属性初始化

		// groupTicket.setCard();
		// GroupTicket.Card card=groupTicket.Card();
		// GroupTicket.Card.Groupon groupon=new Groupon();
		// GroupTicket.Card.Groupon.BaseInfo base_info=new BaseInfo();
		// GroupTicket.Card.Groupon.BaseInfo.Sku sku=new Sku();
		// GroupTicket.Card.Groupon.BaseInfo.DateInfo date_info=new DateInfo();

		GroupTicket.Card card = groupTicket.getCard();
		GroupTicket.Card.Groupon groupon = groupTicket.getCard().getGroupon();
		GroupTicket.Card.Groupon.Base_info base_info = groupTicket.getCard()
				.getGroupon().getBase_info();
		GroupTicket.Card.Groupon.Base_info.Sku sku = groupTicket.getCard()
				.getGroupon().getBase_info().getSku();
		GroupTicket.Card.Groupon.Base_info.Date_info date_info = groupTicket
				.getCard().getGroupon().getBase_info().getDate_info();

		// 写入值
		card.setCard_type("GROUPON");
		base_info.setGet_limit(Integer.parseInt(request
				.getParameter("get_limit")));
		sku.setQuantity(Integer.valueOf(request.getParameter("quantity")));
		base_info.setBrand_name("商户名字");
		base_info.setSub_title(request.getParameter("sub_title"));
		base_info.setTitle(request.getParameter("title"));
		System.out.println("sub_title=" + base_info.getSub_title());
		base_info.setSource("袋鼠帮");
		if (request.getParameter("service_phone") != null) {
			// 有客服电话
			base_info.setService_phone(request.getParameter("service_phone"));
		}
		base_info.setColor(request.getParameter("color"));
		base_info.setDescription(request.getParameter("description"));
		base_info.setNotice(request.getParameter("notice"));
		base_info.setCode_type("CODE_TYPE_BARCODE");
		groupon.setDeal_detail(request.getParameter("deal_detail"));
		// 将时间信息封装进javabean中
		if (request.getParameter("type").equals("DATE_TYPE_FIX_TIME_RANGE"))//
		{// 表示固定日期区间
			date_info.setType("DATE_TYPE_FIX_TIME_RANGE");
			// 将时间转化成1970年开始按秒计时
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String beginTime = "" + request.getParameter("begin_time_year")
					+ request.getParameter("begin_time_month")
					+ request.getParameter("begin_time_day");// beginTime来记录开始时间，之后转换成秒
			String endTime = "" + request.getParameter("end_time_year")
					+ request.getParameter("end_time_month")
					+ request.getParameter("end_time_day");// endTime来记录开始时间，之后转换成秒
			long beginSeconds = 0;// 转换成的秒数
			long endSeconds = 0;
			try {
				beginSeconds = (sdf.parse(beginTime).getTime() / 1000);
				endSeconds = (sdf.parse(endTime).getTime() / 1000);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("时间转换出错");
				return false;
			}
			date_info.setBegin_timestamp(beginSeconds + "");
			date_info.setEnd_timestamp(endSeconds + "");
		} else {// 表示固定时长（自领取后按天算）
			date_info.setType("DATE_TYPE_FIX_TERM");
			date_info.setFixed_term(Integer.valueOf(request
					.getParameter("fixed_term")));
		}
		return true;
	}

	public static boolean write2SimpleCardInfo(JSONObject json,
			SimpleCardInfo simpleCardInfo) {
		JSONObject base_info = null;// 储存获得的一张卡券信息
		try {
			JSONObject card = json.getJSONObject("card");
			if (card.getString("card_type").equals("GROUPON")) {
				// 团购券
				simpleCardInfo.setCard_type("GROUPON");
				base_info = card.getJSONObject("groupon").getJSONObject(
						"base_info");
				System.out.println(base_info);
			} else {
				// 其他的券再写else if
			}
			simpleCardInfo.setColor(base_info.getString("color"));
			simpleCardInfo.setLogo_url(base_info.getString("logo_url"));
			simpleCardInfo.setTitle(base_info.getString("title"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out
					.println("UsedMethod中write2SimpleCardInfo方法出错，未能将数据写入卡券对象中");
			return false;
		}
		return true;
	}

	/**
	 * 这个方法可以控制当级别高于某个值时才会输出。 建议调试信息grade=1，常规信息grade=2,必须信息grade=3
	 * 
	 * @author Time
	 * @param obj
	 *            所需要输出的对象
	 * @param grade
	 *            输出的级别
	 */
	public static void log(Object obj, int grade) {
		int i = 0;// 当i大于grade时obj才会被输出
		if (grade > i)
			System.out.println(obj);
	}
}
