package com.genfu.reform.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the NAVIGATION_NODE database table.
 * 
 */
@Entity
@Table(name = "GENFU_CONFIGS")
public class GenfuConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONFIG_ID")
	private long id;

	@Column(name = "CONFIG_KEY")
	private String configKey;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CONFIG_CREATED_AT")
	private Date configCreatedAt;

	@Column(name = "CONFIG_DESCRIPTION")
	private String configDescription;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CONFIG_EFF_DATE")
	private Date configEffDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CONFIG_EXP_DATE")
	private Date configExpDate;

	@Column(name = "CONFIG_FLAG")
	private String configFlag;

	@Column(name = "CONFIG_ORDER")
	private BigDecimal configOrder;

	@Column(name = "CONFIG_OTHERS")
	private String configOthers;

	@Column(name = "CONFIG_PARENT_ID")
	private long configParentId;

	@Column(name = "CONFIG_SRC")
	private String configSrc;

	@Column(name = "CONFIG_VALUE")
	private String configValue;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CONFIG_UPDATED_AT")
	private Date configUpdatedAt;

	public GenfuConfig() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long naviId) {
		this.id = naviId;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public Date getConfigCreatedAt() {
		return configCreatedAt;
	}

	public void setConfigCreatedAt(Date configCreatedAt) {
		this.configCreatedAt = configCreatedAt;
	}

	public String getConfigDescription() {
		return configDescription;
	}

	public void setConfigDescription(String configDescription) {
		this.configDescription = configDescription;
	}

	public Date getConfigEffDate() {
		return configEffDate;
	}

	public void setConfigEffDate(Date configEffDate) {
		this.configEffDate = configEffDate;
	}

	public Date getConfigExpDate() {
		return configExpDate;
	}

	public void setConfigExpDate(Date configExpDate) {
		this.configExpDate = configExpDate;
	}

	public String getConfigFlag() {
		return configFlag;
	}

	public void setConfigFlag(String configFlag) {
		this.configFlag = configFlag;
	}

	public BigDecimal getConfigOrder() {
		return configOrder;
	}

	public void setConfigOrder(BigDecimal configOrder) {
		this.configOrder = configOrder;
	}

	public String getConfigOthers() {
		return configOthers;
	}

	public void setConfigOthers(String configOthers) {
		this.configOthers = configOthers;
	}

	public long getConfigParentId() {
		return configParentId;
	}

	public void setConfigParentId(long configParentId) {
		this.configParentId = configParentId;
	}

	public String getConfigSrc() {
		return configSrc;
	}

	public void setConfigSrc(String configSrc) {
		this.configSrc = configSrc;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public Date getConfigUpdatedAt() {
		return configUpdatedAt;
	}

	public void setConfigUpdatedAt(Date configUpdatedAt) {
		this.configUpdatedAt = configUpdatedAt;
	}

	public GenfuConfig(long id, String configKey, Date configCreatedAt,
			String configDescription, Date configEffDate, Date configExpDate,
			String configFlag, BigDecimal configOrder, String configOthers,
			long configParentId, String configSrc, String configValue,
			Date configUpdatedAt) {
		super();
		this.id = id;
		this.configKey = configKey;
		this.configCreatedAt = configCreatedAt;
		this.configDescription = configDescription;
		this.configEffDate = configEffDate;
		this.configExpDate = configExpDate;
		this.configFlag = configFlag;
		this.configOrder = configOrder;
		this.configOthers = configOthers;
		this.configParentId = configParentId;
		this.configSrc = configSrc;
		this.configValue = configValue;
		this.configUpdatedAt = configUpdatedAt;
	}

}