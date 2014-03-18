package com.genfu.reform.model;

import java.io.Serializable;
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
@Table(name = "AUTHORIZATIONES")
public class Authorization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "AUTH_ID")
	private long id;

	@Column(name = "AUTHORIZER")
	private long authorizer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUTH_CREATE_AT")
	private Date authCreateAt;

	@Column(name = "AUTH_DESCRIPTION")
	private String authDescription;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUTH_EFF_DATE")
	private Date authEffDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUTH_EXP_DATE")
	private Date authExpDate;

	@Column(name = "AUTH_FLAG")
	private String authFlag;

	@Column(name = "AUTH_OTHERS")
	private String authOthers;

	@Column(name = "AUTH_CART_ID")
	private long authCartId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUTH_UPDATE_AT")
	private Date authUpdateAt;

	public Authorization() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long naviId) {
		this.id = naviId;
	}

	public long getAuthorizer() {
		return authorizer;
	}

	public void setAuthorizer(long authorizer) {
		this.authorizer = authorizer;
	}

	public Date getAuthCreateAt() {
		return authCreateAt;
	}

	public void setAuthCreateAt(Date authCreateAt) {
		this.authCreateAt = authCreateAt;
	}

	public String getAuthDescription() {
		return authDescription;
	}

	public void setAuthDescription(String authDescription) {
		this.authDescription = authDescription;
	}

	public Date getAuthEffDate() {
		return authEffDate;
	}

	public void setAuthEffDate(Date authEffDate) {
		this.authEffDate = authEffDate;
	}

	public Date getAuthExpDate() {
		return authExpDate;
	}

	public void setAuthExpDate(Date authExpDate) {
		this.authExpDate = authExpDate;
	}

	public String getAuthFlag() {
		return authFlag;
	}

	public void setAuthFlag(String authFlag) {
		this.authFlag = authFlag;
	}

	public String getAuthOthers() {
		return authOthers;
	}

	public void setAuthOthers(String authOthers) {
		this.authOthers = authOthers;
	}

	public long getAuthCartId() {
		return authCartId;
	}

	public void setAuthCartId(long authCartId) {
		this.authCartId = authCartId;
	}

	public Date getAuthUpdateAt() {
		return authUpdateAt;
	}

	public void setAuthUpdateAt(Date authUpdateAt) {
		this.authUpdateAt = authUpdateAt;
	}

}