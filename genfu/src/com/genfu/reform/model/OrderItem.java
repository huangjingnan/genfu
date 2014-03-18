package com.genfu.reform.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: OrderItem
 * 
 */
@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem implements Serializable {

	@Id
	@Column(name = "ORDER_ITEM_ID", insertable = true, updatable = false, unique = true, nullable = true)
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "DISH_ID")
	private long dishId;

	@Column(name = "ORDER_ID")
	private long orderId;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "AMOUNT")
	private long amount;

	@Column(name = "STAFF_NUMBER")
	private String staffNumber;

	@Column(name = "KITCHENER")
	private String kitchener;

	@Column(name = "ORDER_ITEM_NAME")
	private String orderItemName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_AT")
	private Date updatedAt;

	@Column(name = "STATUS")
	private String status;

	private static final long serialVersionUID = 1L;

	public OrderItem() {
		super();
		this.status = "OPEN";
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDishId() {
		return dishId;
	}

	public void setDishId(long dishId) {
		this.dishId = dishId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getOrderItemName() {
		return orderItemName;
	}

	public void setOrderItemName(String orderItemName) {
		this.orderItemName = orderItemName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	public String getKitchener() {
		return kitchener;
	}

	public void setKitchener(String kitchener) {
		this.kitchener = kitchener;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
