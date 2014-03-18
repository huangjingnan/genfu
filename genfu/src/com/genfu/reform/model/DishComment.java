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
 * Entity implementation class for Entity: DishComment
 * 
 */
@Entity
@Table(name = "DISH_COMMENTS")
public class DishComment implements Serializable {

	@Id
	@Column(name = "COMMENT_ID", insertable = true, updatable = false, unique = true, nullable = true)
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "ORDER_ITEM_ID")
	private long orderItemId;

	@Column(name = "DISH_ID")
	private long dishId;

	@Column(name = "ORDER_ID")
	private long orderId;

	@Column(name = "DISH_NAME")
	private String dishName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMENT_AT")
	private Date commentAt;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "SCORE")
	private BigDecimal score;

	@Column(name = "COMMENTATOR")
	private String commentator;

	@Column(name = "CONTACT")
	private String contact;

	@Column(name = "COMMENT_OTHERS")
	private String commentOthers;

	@Column(name = "COMMENT_FLAG")
	private String commentFlag;

	@Column(name = "COVER_IMAGE")
	private String coverImage;

	private static final long serialVersionUID = 1L;

	public DishComment() {
		super();
		this.commentAt = new Date();
		this.commentFlag = "000";
		this.score = new BigDecimal(0);
	}

	public DishComment(long id, long orderItemId, long dishId, long orderId,
			String dishName, Date commentAt, String comment, BigDecimal score,
			String commentator, String contact, String commentOthers,
			String commentFlag, String coverImage) {
		super();
		this.id = id;
		this.orderItemId = orderItemId;
		this.dishId = dishId;
		this.orderId = orderId;
		this.dishName = dishName;
		this.commentAt = commentAt;
		this.comment = comment;
		this.score = score;
		this.commentator = commentator;
		this.contact = contact;
		this.commentOthers = commentOthers;
		this.commentFlag = commentFlag;
		this.coverImage = coverImage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
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

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public Date getCommentAt() {
		return commentAt;
	}

	public void setCommentAt(Date commentAt) {
		this.commentAt = commentAt;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getCommentator() {
		return commentator;
	}

	public void setCommentator(String commentator) {
		this.commentator = commentator;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCommentOthers() {
		return commentOthers;
	}

	public void setCommentOthers(String commentOthers) {
		this.commentOthers = commentOthers;
	}

	public String getCommentFlag() {
		return commentFlag;
	}

	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

}
