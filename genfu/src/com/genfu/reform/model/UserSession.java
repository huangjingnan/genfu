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
 * The persistent class for the USER_SESSION_LOG database table.
 * 
 */
@Entity
@Table(name="USER_SESSIONS")
public class UserSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_SESSION_LOG_ID")
	private long id;

	@Column(name="ACTOR_ID")
	private long actorId;

	@Column(name="REGION_ID")
	private long regionId;

	@Column(name="SESSION_ID")
	private String sessionId;

	@Column(name="USER_ID")
	private long userId;

	@Temporal(TemporalType.DATE)
	@Column(name="USER_SESSION_EFF_DATE")
	private Date userSessionEffDate;

	@Temporal(TemporalType.DATE)
	@Column(name="USER_SESSION_EXP_DATE")
	private Date userSessionExpDate;

	@Column(name="USER_SESSION_FLAG")
	private String userSessionFlag;

	@Column(name="USER_SESSION_IP")
	private String userSessionIp;

	@Column(name="USER_SESSION_OTHERS")
	private String userSessionOthers;

	public UserSession() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getActorId() {
		return this.actorId;
	}

	public void setActorId(long actorId) {
		this.actorId = actorId;
	}

	public long getRegionId() {
		return this.regionId;
	}

	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getUserSessionEffDate() {
		return this.userSessionEffDate;
	}

	public void setUserSessionEffDate(Date userSessionEffDate) {
		this.userSessionEffDate = userSessionEffDate;
	}

	public Date getUserSessionExpDate() {
		return this.userSessionExpDate;
	}

	public void setUserSessionExpDate(Date userSessionExpDate) {
		this.userSessionExpDate = userSessionExpDate;
	}

	public String getUserSessionFlag() {
		return this.userSessionFlag;
	}

	public void setUserSessionFlag(String userSessionFlag) {
		this.userSessionFlag = userSessionFlag;
	}

	public String getUserSessionIp() {
		return this.userSessionIp;
	}

	public void setUserSessionIp(String userSessionIp) {
		this.userSessionIp = userSessionIp;
	}

	public String getUserSessionOthers() {
		return this.userSessionOthers;
	}

	public void setUserSessionOthers(String userSessionOthers) {
		this.userSessionOthers = userSessionOthers;
	}

}