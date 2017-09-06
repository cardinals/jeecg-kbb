package org.jeecgframework.web.offer.entity;

import java.io.Serializable;

public class OperationRight implements Serializable {
	private boolean add;
	private boolean edit;
	private boolean delete;
	private boolean export;
	
	public boolean isAdd() {
		return add;
	}
	public boolean isEdit() {
		return edit;
	}
	public boolean isDelete() {
		return delete;
	}
	public boolean isExport() {
		return export;
	}
	public void setAdd(boolean add) {
		this.add = add;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	public void setExport(boolean export) {
		this.export = export;
	}
	public static final String ADD="enableNewOffer";
	public static final String EDIT="enableEditOffer";
	public static final String DELETE="enableDeleteOffer";
	public static final String EXPORT="enableExportOffer";
}
