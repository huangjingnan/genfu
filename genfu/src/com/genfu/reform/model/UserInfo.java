package com.genfu.reform.model;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the USER_INFO database table.
 * 
 */
@Entity
@Table(name = "USER_INFO")
// @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "userPassword"})
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "DEPARTMENT_ID")
	private long departmentId;

	@Column(name = "DISTRICT_ID")
	private long districtId;

	@Column(name = "USER_UPPER_ID")
	private long userUpperId;

	@Column(name = "USER_CODE")
	private String userCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_CREATE_DATE")
	private java.util.Date userCreateDate;

	@Column(name = "USER_DESCRIPTION")
	private String userDescription;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_EFF_DATE")
	private java.util.Date userEffDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_EXP_DATE")
	private java.util.Date userExpDate;

	@Column(name = "USER_FLAG")
	private String userFlag;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "SALT")
	private String salt;

	@Column(name = "REMEMBER_TOKEN")
	private String rememberToken;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REMEMBER_TOKEN_EXPIRES_AT")
	private java.util.Date rememberTokenExpiresAt;

	@Column(name = "USER_OTHERS")
	private String userOthers;

	@Column(name = "USER_PASSWORD")
	private String userPassword;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_UPDATE_DATE")
	private java.util.Date userUpdateDate;

	@ManyToMany(cascade = { REFRESH, MERGE, PERSIST }, fetch = EAGER)
	@JoinTable(name = "USER_INFO_ROLE_INFOS", joinColumns = @JoinColumn(name = "USER_USER_ID", referencedColumnName = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ROLE_ID", referencedColumnName = "ROLE_ID"))
	private Set<RoleInfo> roleInfos;

	public UserInfo() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}

	public long getUserUpperId() {
		return userUpperId;
	}

	public void setUserUpperId(long userUpperId) {
		this.userUpperId = userUpperId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public java.util.Date getUserCreateDate() {
		return userCreateDate;
	}

	public void setUserCreateDate(java.util.Date userCreateDate) {
		this.userCreateDate = userCreateDate;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public java.util.Date getUserEffDate() {
		return userEffDate;
	}

	public void setUserEffDate(java.util.Date userEffDate) {
		this.userEffDate = userEffDate;
	}

	public java.util.Date getUserExpDate() {
		return userExpDate;
	}

	public void setUserExpDate(java.util.Date userExpDate) {
		this.userExpDate = userExpDate;
	}

	public String getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRememberToken() {
		return rememberToken;
	}

	public void setRememberToken(String rememberToken) {
		this.rememberToken = rememberToken;
	}

	public java.util.Date getRememberTokenExpiresAt() {
		return rememberTokenExpiresAt;
	}

	public void setRememberTokenExpiresAt(java.util.Date rememberTokenExpiresAt) {
		this.rememberTokenExpiresAt = rememberTokenExpiresAt;
	}

	public String getUserOthers() {
		return userOthers;
	}

	public void setUserOthers(String userOthers) {
		this.userOthers = userOthers;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public java.util.Date getUserUpdateDate() {
		return userUpdateDate;
	}

	public void setUserUpdateDate(java.util.Date userUpdateDate) {
		this.userUpdateDate = userUpdateDate;
	}

	public Set<RoleInfo> getRoleInfos() {
		return roleInfos;
	}

	public void setRoleInfos(Set<RoleInfo> roleInfos) {
		this.roleInfos = roleInfos;
	}

	// public Set<NavigationNode> getNavis() {
	//
	// Set<NavigationNode> navis = new HashSet<NavigationNode>();
	// if (null != this.roleInfos && this.roleInfos.size() > 0) {
	// java.util.Date now = new java.util.Date();
	// for (RoleInfo tempRI : this.roleInfos) {
	// if (now.after(tempRI.getRoleEffDate())
	// && now.before(tempRI.getRoleExpDate())) {
	// navis.addAll(tempRI.getNavigationNodes());
	// }
	// }
	// Set<NavigationNode> tempNs = new HashSet<NavigationNode>();
	// for (NavigationNode tempNn : navis) {
	//
	// if (now.before(tempNn.getNaviEffDate())
	// || now.after(tempNn.getNaviExpDate())) {
	// tempNs.add(tempNn);
	// //navis.remove(tempNn);
	// }
	// }
	// navis.removeAll(tempNs);
	// }
	//
	// return navis;
	// }

}