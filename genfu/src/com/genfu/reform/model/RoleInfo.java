package com.genfu.reform.model;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: RoleInfo
 * 
 */
@Entity
@Table(name = "ROLE_INFO")
public class RoleInfo implements Serializable {

	@Id
	@Column(name = "ROLE_ID")
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "ROLE_NAME")
	private String roleName;

	@Column(name = "ROLE_DESCRIPTION")
	private String roleDescription;

	@Column(name = "ROLE_FLAG")
	private String roleFlag;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ROLE_EFF_DATE")
	private Date roleEffDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ROLE_EXP_DATE")
	private Date roleExpDate;

	@ManyToMany(cascade = { REFRESH, MERGE, PERSIST })
	@JoinTable(name = "ROLE_INFO_NAVIGATION_NODES", joinColumns = @JoinColumn(name = "ROLE_ROLE_ID", referencedColumnName = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "NODE_NAVI_ID", referencedColumnName = "NAVI_ID"))
	@OrderBy("lft ASC")
	private Set<NavigationNode> navigationNodes;

	private static final long serialVersionUID = 1L;

	public RoleInfo() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleFlag() {
		return roleFlag;
	}

	public void setRoleFlag(String roleFlag) {
		this.roleFlag = roleFlag;
	}

	public Date getRoleEffDate() {
		return roleEffDate;
	}

	public void setRoleEffDate(Date roleEffDate) {
		this.roleEffDate = roleEffDate;
	}

	public Date getRoleExpDate() {
		return roleExpDate;
	}

	public void setRoleExpDate(Date roleExpDate) {
		this.roleExpDate = roleExpDate;
	}

	public Set<NavigationNode> getNavigationNodes() {
		return navigationNodes;
	}

	public void setNavigationNodes(Set<NavigationNode> navigationNodes) {
		this.navigationNodes = navigationNodes;
	}

}
