package com.dsb.domain;

/**
 * 生成二维码所需的字段
 * 
 * @author Time
 * 
 */
public class ShowQRCode {
	private String action_name = "QR_CARD";
	private Action_info action_info = new Action_info();

	public Action_info getAction_info() {
		return action_info;
	}

	public void setAction_info(Action_info action_info) {
		this.action_info = action_info;
	}

	public class Action_info {
		private Card card = new Card();

		public Card getCard() {
			return card;
		}

		public void setCard(Card card) {
			this.card = card;
		}

		public class Card {
			private String card_id;// 一类券的id
			private String code;// 自定义的id，非自定义不用填
			private String expire_seconds;// 有效时间，60-1800，不填永久有效

			public String getCard_id() {
				return card_id;
			}

			public void setCard_id(String card_id) {
				this.card_id = card_id;
			}

			public String getCode() {
				return code;
			}

			public void setCode(String code) {
				this.code = code;
			}

			public String getExpire_seconds() {
				return expire_seconds;
			}

			public void setExpire_seconds(String expire_seconds) {
				this.expire_seconds = expire_seconds;
			}
		}

	}

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}
}
