package org.jeecgframework.web.excel.entity;

import java.io.Serializable;
import org.jeecgframework.web.excel.entity.*;

import javax.persistence.Entity;
@Entity
@SuppressWarnings("serial")
public class ExcelOfferEntry implements Serializable {
	private OfferGroup offergroup;
	private OfferSubGroup offersubgroup;
	private Integer index;//-1	门型的基础信息		-2	门型一般参数		-3	表面处理
	private String number;//用来统一门型信息
	private String name;
	private String model;
	private String unit;
	private Double price;
	private Double amount;
	private Double quatity;
	private String remark;
	
	public void setOffergroup(OfferGroup offergroup)
	{
	    this.offergroup = offergroup;
	}
	public OfferGroup getOffergroup()
	{
	    return this.offergroup;
	}
	public void setOffersubgroup(OfferSubGroup offersubgroup)
	{
	    this.offersubgroup = offersubgroup;
	}
	public OfferSubGroup getOffersubgroup()
	{
	    return this.offersubgroup;
	}
	public void setIndex(Integer index)
	{
	    this.index = index;
	}
	public Integer getIndex()
	{
	    return this.index;
	}
	public void setNumber(String number)
	{
	    this.number = number;
	}
	public String getNumber()
	{
	    return this.number;
	}
	public void setName(String name)
	{
	    this.name = name;
	}
	public String getName()
	{
	    return this.name;
	}
	public void setModel(String model)
	{
	    this.model = model;
	}
	public String getModel()
	{
	    return this.model;
	}
	public void setUnit(String unit)
	{
	    this.unit = unit;
	}
	public String getUnit()
	{
	    return this.unit;
	}
	public void setPrice(Double price)
	{
	    this.price = price;
	}
	public Double getPrice()
	{
	    return this.price;
	}
	public void setAmount(Double amount)
	{
	    this.amount = amount;
	}
	public Double getAmount()
	{
	    return this.amount;
	}
	public void setQuatity(Double quatity)
	{
	    this.quatity = quatity;
	}
	public Double getQuatity()
	{
	    return this.quatity;
	}
	public void setRemark(String remark)
	{
	    this.remark = remark;
	}
	public String getRemark()
	{
	    return this.remark;
	}

}
