package org.jeecgframework.web.excel.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class ExcelOfferEntity implements Serializable {
	private String billno;
	private String custname;
	private String projectname;
	private String saleman;
	private String engineer;
	private String createdate;
	private Double totalamount;
	private Double discountrate;
	private Double afteramount;
	private List<ExcelOfferEntry> entryList;
	private List<ExcelOfferEntry> groupList;
	
	public void setBillno(String billno)
	{
	    this.billno = billno;
	}
	public String getBillno()
	{
	    return this.billno;
	}
	public void setCustname(String custname)
	{
	    this.custname = custname;
	}
	public String getCustname()
	{
	    return this.custname;
	}
	public void setProjectname(String projectname)
	{
	    this.projectname = projectname;
	}
	public String getProjectname()
	{
	    return this.projectname;
	}
	public void setSaleman(String saleman)
	{
	    this.saleman = saleman;
	}
	public String getSaleman()
	{
	    return this.saleman;
	}
	public void setEngineer(String engineer)
	{
	    this.engineer = engineer;
	}
	public String getEngineer()
	{
	    return this.engineer;
	}
	public void setCreatedate(String createdate)
	{
	    this.createdate = createdate;
	}
	public String getCreatedate()
	{
	    return this.createdate;
	}
	public void setTotalamount(Double totalamount)
	{
	    this.totalamount = totalamount;
	}
	public Double getTotalamount()
	{
	    return this.totalamount;
	}
	public void setDiscountrate(Double discountrate)
	{
	    this.discountrate = discountrate;
	}
	public Double getDiscountrate()
	{
	    return this.discountrate;
	}
	public void setAfteramount(Double afteramount)
	{
	    this.afteramount = afteramount;
	}
	public Double getAfteramount()
	{
	    return this.afteramount;
	}
	
	public List<ExcelOfferEntry> getEntryList()
	{
		return this.entryList;
	}
	
	public void setEntryList(List<ExcelOfferEntry> entryList)
	{
		this.entryList=entryList;
	}
	public List<ExcelOfferEntry> getGroupList()
	{
		return this.groupList;
	}
	
	public void setGroupList(List<ExcelOfferEntry> groupList)
	{
		this.groupList=groupList;
	}
}
