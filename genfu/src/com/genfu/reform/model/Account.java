package com.genfu.reform.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Account
 * 
 */
@Entity
@Table(name = "ACCOUNTS")
// @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "orderItems" })
public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ACCOUNT_ID", insertable = true, updatable = false, unique = true, nullable = true)
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "ACCOUNT_NAME")
	private String accountName;

	@Column(name = "TOTAL")
	private BigDecimal total;

	@Column(name = "TAX")
	private BigDecimal tax;

	@Column(name = "RECEIVABLE")
	private BigDecimal receivable;

	@Column(name = "RECEIVED")
	private BigDecimal received;

	@Column(name = "CHANGE")
	private BigDecimal change;

	@Column(name = "PAYER")
	private String payer;

	@Column(name = "CARD_NO")
	private String cardNo;

	@Column(name = "OPERATED_IP")
	private String operatedIp;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "STAFF_NUMBER")
	private String staffNumber;

	@Column(name = "ACCOUNT_REMARKS")
	private String accountRemarks;

	@Column(name = "ACCOUNT_FLAG")
	private String accountFlag;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_AT")
	private Date updatedAt;

	@OneToMany(cascade = ALL, fetch = LAZY, orphanRemoval = true)
	@JoinColumn(name = "ACCOUNT_ID")
	@OrderBy("updatedAt DESC,id DESC")
	private Set<AccountItem> accountItems;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Account other = (Account) obj;
		if (receivable != other.receivable)
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigDecimal getReceivable() {
		return receivable;
	}

	public void setReceivable(BigDecimal receivable) {
		this.receivable = receivable;
	}

	public BigDecimal getReceived() {
		return received;
	}

	public void setReceived(BigDecimal received) {
		this.received = received;
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getOperatedIp() {
		return operatedIp;
	}

	public void setOperatedIp(String operatedIp) {
		this.operatedIp = operatedIp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	public String getAccountRemarks() {
		return accountRemarks;
	}

	public void setAccountRemarks(String accountRemarks) {
		this.accountRemarks = accountRemarks;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getAccountFlag() {
		return accountFlag;
	}

	public void setAccountFlag(String accountFlag) {
		this.accountFlag = accountFlag;
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

	public Set<AccountItem> getAccountItems() {
		return accountItems;
	}

	public void setAccountItems(Set<AccountItem> accountItems) {
		this.accountItems = accountItems;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public Account() {
		super();
	}

}
