package com.genfu.reform.model;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Entity implementation class for Entity: CartItem
 * 
 */
@Entity
@Table(name = "TAGGINGS", uniqueConstraints = @UniqueConstraint(columnNames = {
		"TAG_ID", "TAGGABLE_ID", "TAGGABLE_TYPE" }))
public class Tagging implements Serializable {

	@Id
	@Column(name = "TAGGING_ID")
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "TAGGABLE_ID")
	private long taggableId;

	@Column(name = "TAGGABLE_TYPE")
	private String taggableType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@ManyToOne(optional = false, cascade = { DETACH, REMOVE, REFRESH }, fetch = EAGER)
	@JoinColumn(name = "TAG_ID", nullable = false, updatable = false)
	private Tag tag;

	private static final long serialVersionUID = 1L;

	public Tagging() {
		super();
	}

	public Tagging(long id, long taggableId, String taggableType, Date createdAt) {
		super();
		this.id = id;
		this.taggableId = taggableId;
		this.taggableType = taggableType;
		this.createdAt = createdAt;
	}

	public Tagging(long id, long taggableId, String taggableType,
			Date createdAt, Tag tag) {
		super();
		this.id = id;
		this.taggableId = taggableId;
		this.taggableType = taggableType;
		this.createdAt = createdAt;
		this.tag = tag;
	}

	public long getTaggableId() {
		return taggableId;
	}

	public void setTaggableId(long taggableId) {
		this.taggableId = taggableId;
	}

	public String getTaggableType() {
		return taggableType;
	}

	public void setTaggableType(String taggableType) {
		this.taggableType = taggableType;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}
}
