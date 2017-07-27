package com.jeecg.offer.entity;

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
	private  Integer  index;
	/*************/
	private  String  name;
	/*************/
	private  String  unit;
	
	private String remark;
	
	private String model;
	
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
	public void setIndex( Integer  index)
	{
	    this.index = index;
	}
	public  Integer  getIndex()
	{
	    return this.index;
	}
	public void setName( String  name)
	{
	    this.name = name;
	}
	public  String  getName()
	{
	    return this.name;
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
}

