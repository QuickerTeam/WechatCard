package com.dsb.domain;

public class BatchCard {//批量查询卡券所需的字段
	private int offset;// 起始偏移量
	private int count = 50;// 每次获得的卡片数量
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
