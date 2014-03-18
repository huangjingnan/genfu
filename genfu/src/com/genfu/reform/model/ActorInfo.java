package com.genfu.reform.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ACTOR_INFO database table.
 * 
 */
@Entity
@Table(name="ACTOR_INFO")
public class ActorInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACTOR_ID")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="ACTOR_CREATE_DATE")
	private Date actorCreateDate;

	@Column(name="ACTOR_DESCRIPTION")
	private String actorDescription;

	@Temporal(TemporalType.DATE)
	@Column(name="ACTOR_EFF_DATE")
	private Date actorEffDate;

	@Temporal(TemporalType.DATE)
	@Column(name="ACTOR_EXP_DATE")
	private Date actorExpDate;

	@Column(name="ACTOR_FLAG")
	private String actorFlag;

	@Column(name="ACTOR_OTHERS")
	private String actorOthers;

	@Column(name="ACTOR_TYPE_ID")
	private BigDecimal actorTypeId;

	@Temporal(TemporalType.DATE)
	@Column(name="ACTOR_UPDATE_DATE")
	private Date actorUpdateDate;

	@Column(name="DISTRICT_ID")
	private BigDecimal districtId;

	@Column(name="UPPPER_ACTOR_ID")
	private long uppperActorId;

	public ActorInfo() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getActorCreateDate() {
		return this.actorCreateDate;
	}

	public void setActorCreateDate(Date actorCreateDate) {
		this.actorCreateDate = actorCreateDate;
	}

	public String getActorDescription() {
		return this.actorDescription;
	}

	public void setActorDescription(String actorDescription) {
		this.actorDescription = actorDescription;
	}

	public Date getActorEffDate() {
		return this.actorEffDate;
	}

	public void setActorEffDate(Date actorEffDate) {
		this.actorEffDate = actorEffDate;
	}

	public Date getActorExpDate() {
		return this.actorExpDate;
	}

	public void setActorExpDate(Date actorExpDate) {
		this.actorExpDate = actorExpDate;
	}

	public String getActorFlag() {
		return this.actorFlag;
	}

	public void setActorFlag(String actorFlag) {
		this.actorFlag = actorFlag;
	}

	public String getActorOthers() {
		return this.actorOthers;
	}

	public void setActorOthers(String actorOthers) {
		this.actorOthers = actorOthers;
	}

	public BigDecimal getActorTypeId() {
		return this.actorTypeId;
	}

	public void setActorTypeId(BigDecimal actorTypeId) {
		this.actorTypeId = actorTypeId;
	}

	public Date getActorUpdateDate() {
		return this.actorUpdateDate;
	}

	public void setActorUpdateDate(Date actorUpdateDate) {
		this.actorUpdateDate = actorUpdateDate;
	}

	public BigDecimal getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(BigDecimal districtId) {
		this.districtId = districtId;
	}

	public long getUppperActorId() {
		return this.uppperActorId;
	}

	public void setUppperActorId(long uppperActorId) {
		this.uppperActorId = uppperActorId;
	}

}