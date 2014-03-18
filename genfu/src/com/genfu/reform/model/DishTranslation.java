package com.genfu.reform.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Dish
 * 
 */
@Entity
@Table(name = "DISHES_TRANSLATIONS")
public class DishTranslation implements Serializable {

	@Id
	@Column(name = "TRANSLATION_ID", insertable = true, updatable = false, unique = true, nullable = true)
	private long id;

	@Column(name = "DISH_ID")
	private long dishId;

	@Column(name = "DISH_NAME")
	private String dishName;

	@Column(name = "LOCAL")
	private String local;

	@Column(name = "BLURB")
	private String blurb;

	@Column(name = "PRICE")
	private BigDecimal price;

	private static final long serialVersionUID = 1L;

	public DishTranslation() {
		super();
	}

	public DishTranslation(long id, long dishId, String dishName, String local,
			String blurb, BigDecimal price) {
		super();
		this.id = id;
		this.dishId = dishId;
		this.dishName = dishName;
		this.local = local;
		this.blurb = blurb;
		this.price = price;
	}

	public long getId() {
		return id;
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

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
