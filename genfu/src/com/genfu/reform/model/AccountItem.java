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
@Table(name = "ACCOUNT_ITEMS")
public class AccountItem implements Serializable {

	@Id
	@Column(name = "ACCOUNT_ITEM_ID", insertable = true, updatable = false, unique = true, nullable = true)
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "ACCOUNT_ID")
	private long accountId;

	@Column(name = "ORDER_ID")
	private long orderId;

	@Column(name = "ACCOUNT_ITEM_NAME")
	private String accountItemName;

	@Column(name = "SUB_TOTAL")
	private BigDecimal subTotal;

	@Column(name = "SUB_TAX")
	private BigDecimal subTax;

	@Column(name = "STAFF_NUMBER")
	private String staffNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_AT")
	private Date updatedAt;

	@Column(name = "STATUS")
	private String status;

	private static final long serialVersionUID = 1L;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getAccountItemName() {
		return accountItemName;
	}

	public void setAccountItemName(String accountItemName) {
		this.accountItemName = accountItemName;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubTax() {
		return subTax;
	}

	public void setSubTax(BigDecimal subTax) {
		this.subTax = subTax;
	}

	public AccountItem() {
		super();
	}

}
