package com.dsb.domain;

import java.util.ArrayList;
import java.util.List;

public class BatchCard {//批量查询卡券所需的字段
	private int offset;// 起始偏移量
	private int count = 50;// 每次获得的卡片数量
	private List<String> status_list = new ArrayList<String>();
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
	public List<String> getStatus_list() {
		return status_list;
	}
	public void setStatus_list(List<String> status_list) {
		this.status_list = status_list;
	}
}
