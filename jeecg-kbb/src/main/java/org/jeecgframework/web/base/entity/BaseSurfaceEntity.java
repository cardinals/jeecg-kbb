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
@Table(name = "t_base_surface", schema = "")
@SuppressWarnings("serial")
public class BaseSurfaceEntity implements Serializable {
	private String id;
	@Excel(name="代码")
	private String fnumber;
	@Excel(name="名称")
	private String fname ;
	@Excel(name="备注")
	private String fremark;
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
	@Column(name ="FREMARK",nullable=true,length=200)
	public String getFremark() {
		return fremark;
	}
	public void setFremark(String fremark) {
		this.fremark = fremark;
	}
}
