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

@Entity
@Table(name = "FILE_INFOS")
public class FileInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FILE_INFO_ID", insertable = true, updatable = false, unique = true, nullable = true)
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@Column(name = "FILE_NAME")
	private String name;

	@Column(name = "FILE_SIZE")
	private long size;

	@Column(name = "FILE_URL")
	private String url;

	@Column(name = "THUMBNAIL_URL")
	private String thumbnailUrl;

	@Column(name = "DELETE_URL")
	private String deleteUrl;

	@Column(name = "DELETE_TYPE")
	private String deleteType;

	@Column(name = "UPLOAD_IP")
	private String uploadIP;

	@Column(name = "UPLOAD_USER_ID")
	private long uploadUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_AT")
	private Date updatedAt;

	public FileInfo() {
		super();
	}

	public FileInfo(long fileId, String name, long size, String url,
			String thumbnailUrl, String deleteUrl, String deleteType) {
		super();
		this.id = fileId;
		this.name = name;
		this.size = size;
		this.url = url;
		this.thumbnailUrl = thumbnailUrl;
		this.deleteUrl = deleteUrl;
		this.deleteType = deleteType;
	}

	public long getId() {
		return id;
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

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

	public String getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}

	public String getUploadIP() {
		return uploadIP;
	}

	public void setUploadIP(String uploadIP) {
		this.uploadIP = uploadIP;
	}

	public long getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(long uploadUser) {
		this.uploadUser = uploadUser;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
