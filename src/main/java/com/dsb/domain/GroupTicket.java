package com.dsb.domain;

/**
 * 团购券
 * 
 * @author Time
 * 
 */
public class GroupTicket {
	private String card_type = "GROUPON";// 团购券类型
	private String deal_detail;// 暂时不填
	private String logo_url;// 服务器返回的logourl
	private String code_type;// code类型，二维码、code等
	private String brand_name;// 商户名字
	private String title;// 卡券名
	private String sub_title;// 券名
	private String color;// 券颜色
	private String notice;// 卡券使用提醒
	private String description;// 卡券使用说明
	private sku sku = new sku();
	private date_info date_info = new date_info();

	public sku getSku() {
		return sku;
	}
	
	public date_info getDataInfo(){
		return date_info;
	}

	public class sku {// 用来保存quantity库存

		private int quantity;// 库存

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	}

	public class date_info {// 所有日期信息
		private String type;// 有效日期类型(时间段还是倒计时)
		// 当有效期类型为时间段时，用前两个数据。否则用最后一个数据
		private String begin_timestamp;// 起始日期
		private String end_time;// 截止日期
		private int fixed_term;// 有效期天数

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getBegin_timestamp() {
			return begin_timestamp;
		}

		public void setBegin_timestamp(String begin_timestamp) {
			this.begin_timestamp = begin_timestamp;
		}

		public String getEnd_time() {
			return end_time;
		}

		public void setEnd_time(String end_time) {
			this.end_time = end_time;
		}

		public int getFixed_term() {
			return fixed_term;
		}

		public void setFixed_term(int fixed_term) {
			this.fixed_term = fixed_term;
		}
	}

	// 以下为非必填
	private String servicePhone;// 客服电话
	private String source = "袋鼠帮";
	private String get_limit;// 每人可领券的数量限制

	public String getDealDetail() {
		return deal_detail;
	}

	public void setDealDetail(String dealDetail) {
		this.deal_detail = dealDetail;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public String getCode_type() {
		return code_type;
	}

	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getGet_limit() {
		return get_limit;
	}

	public void setGet_limit(String get_limit) {
		this.get_limit = get_limit;
	}
}