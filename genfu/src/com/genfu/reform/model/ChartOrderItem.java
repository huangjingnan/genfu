package com.genfu.reform.model;

import java.io.Serializable;
import java.util.Date;

public class ChartOrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long dish_id;
	private String order_item_name;
	private long sum_amount;
	private double sum_total;
	private Date create_at;
	
	

	public ChartOrderItem(long dish_id, String order_item_name,
			long sum_amount, double sum_total, Date create_at) {
		super();
		this.dish_id = dish_id;
		this.order_item_name = order_item_name;
		this.sum_amount = sum_amount;
		this.sum_total = sum_total;
		this.create_at = create_at;
	}

	public ChartOrderItem(long dish_id, String order_item_name,
			long sum_amount, double sum_total) {
		super();
		this.dish_id = dish_id;
		this.order_item_name = order_item_name;
		this.sum_amount = sum_amount;
		this.sum_total = sum_total;
	}

	public ChartOrderItem() {
		// TODO Auto-generated constructor stub
	}

	public long getDish_id() {
		return dish_id;
	}

	public void setDish_id(long dish_id) {
		this.dish_id = dish_id;
	}

	public String getOrder_item_name() {
		return order_item_name;
	}

	public void setOrder_item_name(String order_item_name) {
		this.order_item_name = order_item_name;
	}

	public long getSum_amount() {
		return sum_amount;
	}

	public void setSum_amount(long sum_amount) {
		this.sum_amount = sum_amount;
	}

	public double getSum_total() {
		return sum_total;
	}

	public void setSum_total(double sum_total) {
		this.sum_total = sum_total;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

}
