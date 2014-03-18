package com.genfu.reform.model;

import java.io.Serializable;

/**
 * Entity implementation class for Entity: OrderItem
 * 
 */
public class GenfuResultIdCell implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Object cell;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Object getCell() {
		return cell;
	}

	public void setCell(Object cell) {
		this.cell = cell;
	}

	public GenfuResultIdCell() {
		super();
	}

	public GenfuResultIdCell(long id) {
		super();
		this.id = id;
	}

	public GenfuResultIdCell(long id, Object cell) {
		super();
		this.id = id;
		this.cell = cell;
	}

}
