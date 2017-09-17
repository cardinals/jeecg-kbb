package org.jeecgframework.web.offer.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class WxOffer2Entry implements Serializable{
	private String id;
	private String fofferid;
	private int findex;
	private String fitemid;
	private String fitemnumber;
	private String fitemname;
	private String fmodel;
	private String fbranch;
	private String funit;
	private BigDecimal fqty;
	private BigDecimal fprice;
	private BigDecimal famount;
	private String fremark;
	private String fdetail2json;
	private String ftype;
	public String getId() {
		return id;
	}
	public String getFofferid() {
		return fofferid;
	}
	public int getFindex() {
		return findex;
	}
	public String getFitemid() {
		return fitemid;
	}
	public String getFmodel() {
		return fmodel;
	}
	public String getFbranch() {
		return fbranch;
	}
	public String getFunit() {
		return funit;
	}
	public BigDecimal getFqty() {
		return fqty;
	}
	public BigDecimal getFprice() {
		return fprice;
	}
	public BigDecimal getFamount() {
		return famount;
	}
	public String getFremark() {
		return fremark;
	}
	public String getFdetail2json() {
		return fdetail2json;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setFofferid(String fofferid) {
		this.fofferid = fofferid;
	}
	public void setFindex(int findex) {
		this.findex = findex;
	}
	public void setFitemid(String fitemid) {
		this.fitemid = fitemid;
	}
	public void setFmodel(String fmodel) {
		this.fmodel = fmodel;
	}
	public void setFbranch(String fbranch) {
		this.fbranch = fbranch;
	}
	public void setFunit(String funit) {
		this.funit = funit;
	}
	public void setFqty(BigDecimal fqty) {
		this.fqty = fqty;
	}
	public String getFitemnumber() {
		return fitemnumber;
	}
	public String getFitemname() {
		return fitemname;
	}
	public void setFitemnumber(String fitemnumber) {
		this.fitemnumber = fitemnumber;
	}
	public void setFitemname(String fitemname) {
		this.fitemname = fitemname;
	}
	public void setFprice(BigDecimal fprice) {
		this.fprice = fprice;
	}
	public void setFamount(BigDecimal famount) {
		this.famount = famount;
	}
	public void setFremark(String fremark) {
		this.fremark = fremark;
	}
	public void setFdetail2json(String fdetail2json) {
		this.fdetail2json = fdetail2json;
	}
	public String getFtype() {
		return ftype;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	
}
