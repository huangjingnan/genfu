package com.genfu.reform.model;

public class GenfuCommonResult {

	public int page;
	public int total;
	public int records;
	public int id;
	public Object model;

	public GenfuCommonResult() {
		super();
	}

	public GenfuCommonResult(int page, Object model) {
		super();
		this.page = page;
		this.model = model;
	}

	public GenfuCommonResult(int page, int total, int records) {
		super();
		this.page = page;
		this.total = total;
		this.records = records;
	}

	public GenfuCommonResult(int page, int total, int records, Object model) {
		super();
		this.page = page;
		this.total = total;
		this.records = records;
		this.model = model;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}

}
