package org.jeecgframework.web.base.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;



@Entity
@Table(name = "t_base_standard", schema = "")
@SuppressWarnings("serial")
public class BaseStandardEntity implements Serializable {
	private String id;
	@Excel(name="代码")
	private String fnumber;
	@Excel(name="名称")
	private String fname ;
	@Excel(name="备注")
	private String fremark;
	@Excel(name="规格型号")
	private String fmodel ;
	@Excel(name="品牌")
	private String fbrand;
	@Excel(name="单位")
	private String funit;
	@Excel(name="价格")
	private Double fprice;
	@Excel(name="类型")
	private String ftype;
	
	public String getFtype() {
		return ftype;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	
	@Column(name ="ID",nullable=false,length=36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name ="FNUMBER",nullable=false,length=50)
	public String getFnumber() {
		return fnumber;
	}
	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}
	@Column(name ="FNAME",nullable=false,length=100)
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	@Column(name ="FREMARK",nullable=true,length=255)
	public String getFremark() {
		return fremark;
	}
	public void setFremark(String fremark) {
		this.fremark = fremark;
	}
	
	@Column(name ="FMODEL",nullable=true,length=100)
	public String getFmodel() {
		return fmodel;
	}
	public void setFmodel(String fmodel) {
		this.fmodel = fmodel;
	}
	
	@Column(name ="FBRAND",nullable=true,length=100)
	public String getFbrand() {
		return fbrand;
	}
	public void setFbrand(String fbrand) {
		this.fbrand = fbrand;
	}
	
	@Column(name ="FUNIT",nullable=true,length=100)
	public String getFunit() {
		return funit;
	}
	public void setFunit(String funit) {
		this.funit = funit;
	}
	
	@Column(name ="FPRICE",nullable=false)
	public Double getFprice() {
		return fprice;
	}
	public void setFprice(Double fprice) {
		this.fprice = fprice;
	}

}
