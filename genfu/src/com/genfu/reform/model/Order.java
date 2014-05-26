package com.genfu.reform.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
 * Entity implementation class for Entity: Order
 * 
 */
@Entity
@Table(name = "ORDERS")
// @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "orderItems" })
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ORDER_ID", insertable = true, updatable = false, unique = true, nullable = true)
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "ORDER_NAME")
	private String orderName;

	@Column(name = "AMOUNT")
	private long amount;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "SHIP_TO_FIRST_NAME")
	private String shipToFirstName;

	@Column(name = "SHIP_TO_LAST_NAME")
	private String shipToLastName;

	@Column(name = "SHIP_TO_ADDRESS")
	private String shipToAddress;

	@Column(name = "SHIP_TO_CITY")
	private String shipToCity;

	@Column(name = "SHIP_TO_POSTAL_CODE")
	private String shipToPostalCode;

	@Column(name = "SHIP_TO_COUNTRY")
	private String shipToCountry;

	@Column(name = "CUSTOMER_IP")
	private String customerIp;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "NUMBER_PEOPLE")
	private String numberPeople;

	@Column(name = "STAFF_NUMBER")
	private String staffNumber;

	@Column(name = "ERROR_MESSAGE")
	private String errorMessage;

	@Column(name = "PAY_FLAG")
	private String payFlag;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_AT")
	private Date updatedAt;

	@OneToMany(cascade = ALL, fetch = LAZY, orphanRemoval = true)
	@JoinColumn(name = "ORDER_ID")
	@OrderBy("updatedAt DESC,id DESC")
	private Set<OrderItem> orderItems;

	public Order() {
		this.status = "OPEN";
		this.createdAt = new Date();
		this.updatedAt = new Date();
		this.payFlag = "000";
	}

	public Order(long id, String orderName, long amount) {
		super();
		this.id = id;
		this.orderName = orderName;
		this.amount = amount;
		this.payFlag = "000";
	}

	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getShipToFirstName() {
		return shipToFirstName;
	}

	public void setShipToFirstName(String shipToFirstName) {
		this.shipToFirstName = shipToFirstName;
	}

	public String getShipToLastName() {
		return shipToLastName;
	}

	public void setShipToLastName(String shipToLastName) {
		this.shipToLastName = shipToLastName;
	}

	public String getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public String getShipToCity() {
		return shipToCity;
	}

	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}

	public String getShipToPostalCode() {
		return shipToPostalCode;
	}

	public void setShipToPostalCode(String shipToPostalCode) {
		this.shipToPostalCode = shipToPostalCode;
	}

	public String getShipToCountry() {
		return shipToCountry;
	}

	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
	}

	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getNumberPeople() {
		return numberPeople;
	}

	public void setNumberPeople(String numberPeople) {
		this.numberPeople = numberPeople;
	}

	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Order other = (Order) obj;
		if (amount != other.amount)
			return false;
		return true;
	}

	public void PlaceOrder(Cart cart, long multiplier,String staffNumber) {
		Set<CartItem> cartItems = cart.getCartItems();
		if (null != cartItems && cartItems.size() > 0) {
			if (null != this.orderItems) {
				// new
				// this.orderItems.clear();
			} else {
				this.orderItems = new HashSet<OrderItem>();
			}
			OrderItem tempOrderItem = null;
			Date timestamp = new Date();
			if (multiplier > 1) {
				for (CartItem tempCartItem : cartItems) {
					tempOrderItem = new OrderItem();
					tempOrderItem.setAmount(tempCartItem.getAmount()
							* multiplier);
					tempOrderItem.setPrice(tempCartItem.getPrice());
					tempOrderItem.setDishId(tempCartItem.getDishId());
					tempOrderItem.setOrderItemName(tempCartItem
							.getCartItemName());
					tempOrderItem.setCreatedAt(timestamp);
					tempOrderItem.setUpdatedAt(timestamp);
					tempOrderItem.setOrderId(this.id);
					tempOrderItem.setStaffNumber(staffNumber);
					this.orderItems.add(tempOrderItem);
				}
			} else {
				for (CartItem tempCartItem : cartItems) {
					tempOrderItem = new OrderItem();
					tempOrderItem.setAmount(tempCartItem.getAmount());
					tempOrderItem.setPrice(tempCartItem.getPrice());
					tempOrderItem.setDishId(tempCartItem.getDishId());
					tempOrderItem.setOrderItemName(tempCartItem
							.getCartItemName());
					tempOrderItem.setCreatedAt(timestamp);
					tempOrderItem.setUpdatedAt(timestamp);
					tempOrderItem.setOrderId(this.id);
					tempOrderItem.setStaffNumber(staffNumber);
					this.orderItems.add(tempOrderItem);
				}
			}
		}
	}

	public void PlaceOrder(List<Dish> dishes, long itemAmount) {
		if (null != dishes && dishes.size() > 0) {
			if (null != this.orderItems) {
				// new
				// this.orderItems.clear();
			} else {
				this.orderItems = new HashSet<OrderItem>();
			}
			OrderItem tempOrderItem = null;
			Date timestamp = new Date();

			for (Dish tempDish : dishes) {
				tempOrderItem = new OrderItem();
				tempOrderItem.setAmount(itemAmount);
				tempOrderItem.setPrice(tempDish.getPrice());
				tempOrderItem.setDishId(tempDish.getId());
				tempOrderItem.setOrderItemName(tempDish.getDishName());
				tempOrderItem.setCreatedAt(timestamp);
				tempOrderItem.setUpdatedAt(timestamp);
				tempOrderItem.setOrderId(this.id);
				this.orderItems.add(tempOrderItem);
			}
			this.updatedAt = timestamp;
		}
	}

	public BigDecimal getTotal() {
		Set<OrderItem> tempOrderIs = this.getOrderItems();
		BigDecimal total = new BigDecimal(0);
		for (OrderItem oi : tempOrderIs) {
			total = total.add(oi.getPrice().multiply(new BigDecimal(oi.getAmount())));
			//total = new BigDecimal(oi.getAmount()).multiply(oi.getPrice()).add(total);
		}
		return total;
	}
}
