package com.genfu.reform.model;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
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
 * Entity implementation class for Entity: Cart
 * 
 */
@Entity
@Table(name = "CARTS")
public class Cart implements Serializable {

	@Id
	@Column(name = "CART_ID", insertable = true, updatable = false, unique = true, nullable = true)
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "BLURB")
	private String blurb;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_AT")
	private Date updatedAt;

	// REMOVE, PERSIST, MERGE, REFRESH, DETACH,//, orphanRemoval = true
	@OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch = EAGER, orphanRemoval = true)
	@JoinColumn(name = "CART_ID")
	@OrderBy("id DESC")
	private Set<CartItem> cartItems;

	private static final long serialVersionUID = 1L;

	public Cart() {
		super();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
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

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public void addDish(Dish dish) {
		Date timestamp = new Date();
		CartItem tempCartItem = new CartItem();
		tempCartItem.setCartId(this.id);
		tempCartItem.setDishId(dish.getId());
		tempCartItem.setPrice(dish.getPrice());
		tempCartItem.setCreatedAt(timestamp);
		tempCartItem.setUpdatedAt(timestamp);
		tempCartItem.setAmount(1);
		tempCartItem.setCartItemName(dish.getDishName());

		if (null != this.cartItems) {

			boolean notFound = true;
			for (CartItem tempItem : cartItems) {

				if (tempItem.getDishId() == dish.getId()) {
					// cartItems.get(i).setAmount(tempCartItem.getAmount() + 1);
					notFound = false;
					tempCartItem.setCartItemName(dish.getDishName());
					tempItem.setPrice(dish.getPrice());
					tempItem.setUpdatedAt(timestamp);
					tempItem.setAmount(tempItem.getAmount() + 1);
					break;
				}
			}
			// not found!
			// tempCartItem.setAmount(tempCartItem.getAmount() + 1);
			if (notFound) {
				this.cartItems.add(tempCartItem);
			}
		} else {
			this.cartItems = new HashSet<CartItem>();
			this.cartItems.add(tempCartItem);
		}

	}

	public void removeDish(Dish dish) {
		if (null != cartItems) {
			for (CartItem tempItem : cartItems) {

				if (tempItem.getDishId() == dish.getId()) {

					tempItem.setUpdatedAt(new Date());
					if (tempItem.getAmount() > 1) {
						tempItem.setAmount(tempItem.getAmount() - 1);
					} else {
						cartItems.remove(tempItem);
					}
					break;
				}
			}
		}
	}

	public void clearDishes() {

		if (null != cartItems) {
			cartItems.clear();
		}
	}

	public BigDecimal total() {
		BigDecimal total = new BigDecimal(0.0);
		if (null != cartItems) {
			for (CartItem tempItem : cartItems) {
				if (null != tempItem.getPrice()) {
					total = total.add(new BigDecimal(tempItem.getAmount())
							.multiply(tempItem.getPrice()));
				}
			}
		}
		return total;
	}
}
