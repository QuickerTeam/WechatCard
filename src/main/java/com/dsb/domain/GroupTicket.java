package com.dsb.domain;

/**
 * �Ź�ȯ
 * 
 * @author Time
 * 
 */
public class GroupTicket {
	private String card_type = "GROUPON";// �Ź�ȯ����
	private String dealDetail = "deal_detail\n������������\n��������������������";// ��ʱ����
	private String logo_url;// ���������ص�logourl
	private String code_type;// code���ͣ���ά�롢code��
	private String brand_name;// �̻�����
	private String title;// ��ȯ��
	private String sub_title;// ȯ��
	private String color;// ȯ��ɫ
	private String notice;// ��ȯʹ������
	private String description;// ��ȯʹ��˵��
	private sku sku = new sku();
	private date_info date_info = new date_info();

	public sku getSku() {
		return sku;
	}
	
	public date_info getDataInfo(){
		return date_info;
	}

	public class sku {// ��������quantity���

		private int quantity;// ���

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	}

	public class date_info {// ����������Ϣ
		private String type;// ��Ч��������(ʱ��λ��ǵ���ʱ)
		// ����Ч������Ϊʱ���ʱ����ǰ�������ݡ����������һ������
		private String begin_time;// ��ʼ����
		private String end_time;// ��ֹ����
		private int fixed_term;// ��Ч������

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getBegin_time() {
			return begin_time;
		}

		public void setBegin_time(String begin_time) {
			this.begin_time = begin_time;
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

	// ����Ϊ�Ǳ���
	private String servicePhone;// �ͷ��绰
	private String source = "�����";
	private int get_limit;// ÿ�˿���ȯ����������

	public String getDealDetail() {
		return dealDetail;
	}

	public void setDealDetail(String dealDetail) {
		this.dealDetail = dealDetail;
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

	public int getGet_limit() {
		return get_limit;
	}

	public void setGet_limit(int get_limit) {
		this.get_limit = get_limit;
	}
}