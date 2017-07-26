package com.jeecg.offer.entity;

import java.io.Serializable;
import java.util.Date;


import java.math.BigDecimal;

/*
 * 
 * */
public class WxGroupInfos implements Serializable {
	/*******组Id******/
	private  Integer  group_id;
	/*******序号******/
	private  Integer  index;
	/*************/
	private  String  name;
	/*************/
	private  String  unit;
	
	
	private String model;
	
	private BigDecimal quantity;
	
	private BigDecimal price;
	
	private BigDecimal amount;
	
	
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
	public void setQuantity( BigDecimal  quantity)
	{
	    this.quantity = quantity;
	}
	public  BigDecimal  getQuantity()
	{
	    return this.quantity;
	}
	public void setPrice( BigDecimal  price)
	{
	    this.price = price;
	}
	public  BigDecimal  getPrice()
	{
	    return this.price;
	}
	public void setAmount( BigDecimal  amount)
	{
	    this.amount = amount;
	}
	public  BigDecimal  getAmount()
	{
	    return this.amount;
	}
}

