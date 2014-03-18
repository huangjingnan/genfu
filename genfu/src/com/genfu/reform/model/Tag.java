package com.genfu.reform.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: CartItem
 * 
 */
@Entity
@Table(name = "TAGS")
public class Tag implements Serializable {

	@Id
	@Column(name = "TAG_ID")
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "TAG_NAME")
	private String name;

	@Column(name = "TAG_SN")
	private BigDecimal tagSn;
	// @OneToMany(orphanRemoval = true, fetch = LAZY)
	// @JoinColumn(name = "TAG_ID")
	// private Set<Tagging> taggings;

	private static final long serialVersionUID = 1L;

	public Tag() {
		super();
	}

	public Tag(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Tag(long id, String name, BigDecimal tagSn) {
		super();
		this.id = id;
		this.name = name;
		this.tagSn = tagSn;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getTagSn() {
		return tagSn;
	}

	public void setTagSn(BigDecimal tagSn) {
		this.tagSn = tagSn;
	}

	// public Set<Tagging> getTaggings() {
	// return taggings;
	// }
	//
	// public void setTaggings(Set<Tagging> taggings) {
	// this.taggings = taggings;
	// }

}
