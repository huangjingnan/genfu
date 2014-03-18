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
 * Entity implementation class for Entity: CartItem
 * 
 */
@Entity
@Table(name = "CART_ITEMS")
public class CartItem implements Serializable {

	@Id
	@Column(name = "CART_ITEM_ID")
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "DISH_ID")
	private long dishId;

	@Column(name = "CART_ID")
	private long cartId;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "AMOUNT")
	private long amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_AT")
	private Date updatedAt;

	@Column(name = "CART_ITEM_NAME")
	private String cartItemName;

	private static final long serialVersionUID = 1L;

	public CartItem() {
		super();
	}

	public CartItem(long id, long dishId, long cartId, BigDecimal price,
			long amount, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.dishId = dishId;
		this.cartId = cartId;
		this.price = price;
		this.amount = amount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
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

	public String getCartItemName() {
		return cartItemName;
	}

	public void setCartItemName(String cartItemName) {
		this.cartItemName = cartItemName;
	}

}
