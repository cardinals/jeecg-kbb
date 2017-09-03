package org.jeecgframework.web.offer.entity;

import java.io.Serializable;
import java.util.Date;

/*
 * 
 * */
public class WxGroupInfos implements Serializable {
	/***单据id***/
	private String id;	
	/*******组Id******/
	private  Integer  group_id;
	/*******序号******/
	private  String  findex;
	/*************/
	private  String  fname;
	/*************/
	private  String  unit;
	
	private String remark;
	
	private String model;
	
	private String brand;
	private String standard;
	
	private Double quantity;
	
	private Double price;
	
	private Double amount;
	
	public void setId( String  id)
	{
	    this.id = id;
	}
	public  String  getId()
	{
	    return this.id;
	}
	public void setGroup_id( Integer  group_id)
	{
	    this.group_id = group_id;
	}
	public  Integer  getGroup_id()
	{
	    return this.group_id;
	}
	public void setFindex( String  findex)
	{
	    this.findex = findex;
	}
	public  String  getFindex()
	{
	    return this.findex;
	}
	public void setFname( String  fname)
	{
	    this.fname = fname;
	}
	public  String  getFname()
	{
	    return this.fname;
	}
	public void setUnit( String  unit)
	{
	    this.unit = unit;
	}
	public  String  getUnit()
	{
	    return this.unit;
	}
	public void setModel( String  model)
	{
	    this.model = model;
	}
	public  String  getModel()
	{
	    return this.model;
	}
	public void setQuantity( Double  quantity)
	{
	    this.quantity = quantity;
	}
	public  Double  getQuantity()
	{
		
			return this.quantity;
	}
	public void setPrice( Double  price)
	{
	    this.price = price;
	}
	public  Double  getPrice()
	{
		
	    return this.price;
	}
	public void setAmount( Double  amount)
	{
		
	    this.amount = amount;
	}
	public  Double  getAmount(){
		
		    return this.amount;
	}
	public void setRemark( String  remark)
	{
	    this.remark = remark;
	}
	public  String  getRemark()
	{
	    return this.remark;
	}
	public String getBrand() {
		return brand;
	}
	public String getStandard() {
		return standard;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
}

