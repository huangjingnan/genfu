package com.genfu.reform.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the GENFU_LOGS database table.
 * 
 */
@Entity
@Table(name = "GENFU_LOGS")
public class GenfuLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LOG_ID")
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "LOG_USER_ID")
	private long logUserId;

	@Column(name = "LOG_ACTION")
	private String logAction;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOG_AT")
	private Date logAt;

	@Column(name = "LOG_REQUEST_IP")
	private String logRequestIp;

	@Column(name = "LOG_REQUEST_PARAMETER")
	private String logRequestParameter;

	@Column(name = "LOG_AUTH_RESULT")
	private String logAuthResult;

	@Column(name = "LOG_FLAG")
	private String logFlag;

	@Column(name = "LOG_OTHERS")
	private String logOthers;

	public GenfuLog(long id, long logUserId, String logAction,
			String logRequestIp, String logRequestParameter,
			String logAuthResult, String logFlag, String logOthers) {
		super();
		this.id = id;
		this.logUserId = logUserId;
		this.logAction = logAction;
		this.logAt = new Date();
		this.logRequestIp = logRequestIp;
		this.logRequestParameter = logRequestParameter;
		this.logAuthResult = logAuthResult;
		this.logFlag = logFlag;
		this.logOthers = logOthers;
	}

	public GenfuLog() {
		super();
		logAt = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(long logUserId) {
		this.logUserId = logUserId;
	}

	public String getLogAction() {
		return logAction;
	}

	public void setLogAction(String logAction) {
		this.logAction = logAction;
	}

	public Date getLogAt() {
		return logAt;
	}

	public void setLogAt(Date logAt) {
		this.logAt = logAt;
	}

	public String getLogRequestIp() {
		return logRequestIp;
	}

	public void setLogRequestIp(String logRequestIp) {
		this.logRequestIp = logRequestIp;
	}

	public String getLogRequestParameter() {
		return logRequestParameter;
	}

	public void setLogRequestParameter(String logRequestParameter) {
		this.logRequestParameter = logRequestParameter;
	}

	public String getLogAuthResult() {
		return logAuthResult;
	}

	public void setLogAuthResult(String logAuthResult) {
		this.logAuthResult = logAuthResult;
	}

	public String getLogFlag() {
		return logFlag;
	}

	public void setLogFlag(String logFlag) {
		this.logFlag = logFlag;
	}

	public String getLogOthers() {
		return logOthers;
	}

	public void setLogOthers(String logOthers) {
		this.logOthers = logOthers;
	}

}