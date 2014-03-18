package com.genfu.reform.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the NAVIGATION_NODE database table.
 * 
 */
@Entity
@Table(name = "NAVIGATION_NODES")
public class NavigationNode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NAVI_ID")
	private long id;

	@Column(name = "NAVI_ACTION")
	private String naviAction;

	@Column(name = "NAVI_OPERATE")
	private String naviOperate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NAVI_CREATE_DATE")
	private Date naviCreateDate;

	@Column(name = "NAVI_DESCRIPTION")
	private String naviDescription;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NAVI_EFF_DATE")
	private Date naviEffDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NAVI_EXP_DATE")
	private Date naviExpDate;

	@Column(name = "NAVI_FLAG")
	private String naviFlag;

	@Column(name = "NAVI_OTHERS")
	private String naviOthers;

	@Column(name = "NAVI_PARENT_ID")
	private long naviParentId;

	@Column(name = "NAVI_SRC")
	private String naviSrc;

	@Column(name = "NAVI_ORDER")
	private BigDecimal naviOrder;

	@Column(name = "NAVI_TEXT")
	private String naviText;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NAVI_UPDATE_DATE")
	private Date naviUpdateDate;

	@Column(name = "NAVI_LFT")
	private long lft;

	@Column(name = "NAVI_RGT")
	private long rgt;

	@Column(name = "NAVI_LEVEL")
	private long level;

	public NavigationNode() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long naviId) {
		this.id = naviId;
	}

	public String getNaviAction() {
		return this.naviAction;
	}

	public void setNaviAction(String naviAction) {
		this.naviAction = naviAction;
	}

	public Date getNaviCreateDate() {
		return this.naviCreateDate;
	}

	public void setNaviCreateDate(Date naviCreateDate) {
		this.naviCreateDate = naviCreateDate;
	}

	public String getNaviDescription() {
		return this.naviDescription;
	}

	public void setNaviDescription(String naviDescription) {
		this.naviDescription = naviDescription;
	}

	public Date getNaviEffDate() {
		return this.naviEffDate;
	}

	public void setNaviEffDate(Date naviEffDate) {
		this.naviEffDate = naviEffDate;
	}

	public Date getNaviExpDate() {
		return this.naviExpDate;
	}

	public void setNaviExpDate(Date naviExpDate) {
		this.naviExpDate = naviExpDate;
	}

	public String getNaviFlag() {
		return this.naviFlag;
	}

	public void setNaviFlag(String naviFlag) {
		this.naviFlag = naviFlag;
	}

	public String getNaviOthers() {
		return this.naviOthers;
	}

	public void setNaviOthers(String naviOthers) {
		this.naviOthers = naviOthers;
	}

	public long getNaviParentId() {
		return this.naviParentId;
	}

	public void setNaviParentId(long naviParentId) {
		this.naviParentId = naviParentId;
	}

	public String getNaviSrc() {
		return this.naviSrc;
	}

	public void setNaviSrc(String naviSrc) {
		this.naviSrc = naviSrc;
	}

	public String getNaviText() {
		return this.naviText;
	}

	public void setNaviText(String naviText) {
		this.naviText = naviText;
	}

	public Date getNaviUpdateDate() {
		return this.naviUpdateDate;
	}

	public void setNaviUpdateDate(Date naviUpdateDate) {
		this.naviUpdateDate = naviUpdateDate;
	}

	public long getLft() {
		return lft;
	}

	public void setLft(long lft) {
		this.lft = lft;
	}

	public long getRgt() {
		return rgt;
	}

	public void setRgt(long rgt) {
		this.rgt = rgt;
	}

	public BigDecimal getNaviOrder() {
		return naviOrder;
	}

	public void setNaviOrder(BigDecimal naviOrder) {
		this.naviOrder = naviOrder;
	}

	public String getNaviOperate() {
		return naviOperate;
	}

	public void setNaviOperate(String naviOperate) {
		this.naviOperate = naviOperate;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

}