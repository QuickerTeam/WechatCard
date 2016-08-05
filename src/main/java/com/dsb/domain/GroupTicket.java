package com.dsb.domain;



/**
 * 团购券 所有的get/set方法都是自动生成
 * 
 * @author Time
 * 
 */
public class GroupTicket {
	// card字段以及set/get方法
	private Card card = new Card();

	public void setCard(Card card) {
		this.card = card;
	}

	public Card getCard() {
		return card;
	}

	public class Card {// 所有的卡券信息
		// card_type字段以及set/get方法
		private String card_type;// 团购券类型

		public String getCard_type() {
			return card_type;
		}

		public void setCard_type(String card_type) {
			this.card_type = card_type;
		}

		// groupon字段以及set/get方法
		private Groupon groupon = new Groupon();

		public Groupon getGroupon() {
			return groupon;
		}

		public void setGroupon(Groupon groupon) {
			this.groupon = groupon;
		}

		public class Groupon {
			// deal_detail字段以及set/get方法
			public String deal_detail;// 团购详情

			public String getDeal_detail() {
				return deal_detail;
			}

			public void setDeal_detail(String deal_detail) {
				this.deal_detail = deal_detail;
			}

			// base_info字段以及set/get方法
			private Base_info base_info = new Base_info();

			public Base_info getBase_info() {
				return base_info;
			}

			public void setBase_info(Base_info base_info) {
				this.base_info = base_info;
			}

			public class Base_info {
				private int get_limit;// 每人可领券的数量限制

				public int getGet_limit() {
					return get_limit;
				}

				public void setGet_limit(int get_limit) {
					this.get_limit = get_limit;
				}

				private Sku sku = new Sku();

				public void setSku(Sku sku) {
					this.sku = sku;
				}

				public Sku getSku() {
					return sku;
				}

				public class Sku {// 用来保存quantity库存
					private int quantity;// 库存

					public int getQuantity() {
						return quantity;
					}

					public void setQuantity(int quantity) {
						this.quantity = quantity;
					}
				}

				private String sub_title;// 券名

				public String getSub_title() {
					return sub_title;
				}

				public void setSub_title(String sub_title) {
					this.sub_title = sub_title;
				}

				private String title;// 卡券名

				public String getTitle() {
					return title;
				}

				public void setTitle(String title) {
					this.title = title;
				}

				private String description;// 卡券使用说明

				public String getDescription() {
					return description;
				}

				public void setDescription(String description) {
					this.description = description;
				}

				private String brand_name;// 商户名字

				public String getBrand_name() {
					return brand_name;
				}

				public void setBrand_name(String brand_name) {
					this.brand_name = brand_name;
				}

				private String source;

				public String getSource() {
					return source;
				}

				public void setSource(String source) {
					this.source = source;
				}

				private String color;// 券颜色

				public String getColor() {
					return color;
				}

				public void setColor(String color) {
					this.color = color;
				}

				private String notice;// 卡券使用提醒

				public String getNotice() {
					return notice;
				}

				public void setNotice(String notice) {
					this.notice = notice;
				}

				private String logo_url;// 服务器返回的logourl

				public String getLogo_url() {
					return logo_url;
				}

				public void setLogo_url(String logo_url) {
					this.logo_url = logo_url;
				}

				private String code_type;// code类型，二维码、code等

				public String getCode_type() {
					return code_type;
				}

				public void setCode_type(String code_type) {
					this.code_type = code_type;
				}

				private String service_phone;// 客服电话

				public String getService_phone() {
					return service_phone;
				}

				public void setService_phone(String service_phone) {
					this.service_phone = service_phone;
				}

				private Date_info date_info = new Date_info();

				public Date_info getDate_info() {
					return date_info;
				}

				public void setDate_info(Date_info date_info) {
					this.date_info = date_info;
				}

				public class Date_info {// 所有日期信息
					private String type;// 有效日期类型(时间段还是倒计时)
					// 当有效期类型为时间段时，用前两个数据。否则用最后一个数据
					private String begin_timestamp;// 起始日期
					private String end_timestamp;// 截止日期
					private int fixed_term;// 有效期天数
					private int fixed_begin_term = 0;// 有效期生效延期

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

					public String getEnd_timestamp() {
						return end_timestamp;
					}

					public void setEnd_timestamp(String end_time) {
						this.end_timestamp = end_time;
					}

					public int getFixed_term() {
						return fixed_term;
					}

					public void setFixed_term(int fixed_term) {
						this.fixed_term = fixed_term;
					}

					public int getFixed_begin_term() {
						return fixed_begin_term;
					}

					public void setFixed_begin_term(int fixed_begin_term) {
						this.fixed_begin_term = fixed_begin_term;
					}
				}
			}
		}
	}
}