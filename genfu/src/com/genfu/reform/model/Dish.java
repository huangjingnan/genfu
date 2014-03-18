package com.genfu.reform.model;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Entity implementation class for Entity: Dish
 * 
 */
@Entity
@org.hibernate.annotations.Cache(region = "common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "DISHES")
public class Dish implements Serializable {

	@Id
	@Column(name = "DISH_ID", insertable = true, updatable = false, unique = true, nullable = true)
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "DISH_NAME")
	private String dishName;

	@Column(name = "ISBN")
	private String isbn;

	@Column(name = "PUBLISHED_ID")
	private long publishedId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PUBLISHED_AT")
	private Date publishedAt;

	@Column(name = "BLURB")
	private String blurb;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_AT")
	private Date updatedAt;

	@Column(name = "COVER_IMAGE")
	private String coverImage;

	@Column(name = "DISH_FLAG")
	private String dishFlag;

	@OneToMany(orphanRemoval = true)
	@JoinColumn(name = "TAGGABLE_ID")
	private Set<Tagging> taggings;

	private static final long serialVersionUID = 1L;

	public Dish() {
		super();
		this.createdAt = new Date();
	}

	public Dish(long id, String dishName, String isbn, long publishedId,
			Date publishedAt, String blurb, BigDecimal price, Date createdAt,
			Date updatedAt, String coverImage, String dishFlag) {
		super();
		this.id = id;
		this.dishName = dishName;
		this.isbn = isbn;
		this.publishedId = publishedId;
		this.publishedAt = publishedAt;
		this.blurb = blurb;
		this.price = price;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.coverImage = coverImage;
		this.dishFlag = dishFlag;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public long getPublishedId() {
		return publishedId;
	}

	public void setPublishedId(long publishedId) {
		this.publishedId = publishedId;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
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

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getDishFlag() {
		return dishFlag;
	}

	public void setDishFlag(String dishFlag) {
		this.dishFlag = dishFlag;
	}

	public Set<Tagging> getTaggings() {
		return taggings;
	}

	public void setTaggings(Set<Tagging> taggings) {
		this.taggings = taggings;
	}

}
