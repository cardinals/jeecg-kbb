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
@Table(name = "t_base_params", schema = "")
@SuppressWarnings("serial")
public class BaseParamEntity implements Serializable {
	private String id;
	
	private String ffeildname;

	private String fcaption ;

	private String fremark;
	
	private String fdatatype;
	
	private String fisdbsynch;
	
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
	
	@Column(name ="FREMARK",nullable=true,length=200)
	public String getFremark() {
		return fremark;
	}
	public void setFremark(String fremark) {
		this.fremark = fremark;
	}
	public String getFfeildname() {
		return ffeildname;
	}
	public String getFcaption() {
		return fcaption;
	}
	public String getFdatatype() {
		return fdatatype;
	}
	public String getFisdbsynch() {
		return fisdbsynch;
	}
	public void setFfeildname(String ffeildname) {
		this.ffeildname = ffeildname;
	}
	public void setFcaption(String fcaption) {
		this.fcaption = fcaption;
	}
	public void setFdatatype(String fdatatype) {
		this.fdatatype = fdatatype;
	}
	public void setFisdbsynch(String fisdbsynch) {
		this.fisdbsynch = fisdbsynch;
	}
}
