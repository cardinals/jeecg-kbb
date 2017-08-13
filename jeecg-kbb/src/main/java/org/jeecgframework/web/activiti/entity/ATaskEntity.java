package org.jeecgframework.web.activiti.entity;

import java.io.Serializable;
import java.util.Date;

public class ATaskEntity implements Serializable {
	private String id;
	private String fname;
	private String flastsubmitter;
	private Date flasttime;
	private String flastremark;
	
	public void setId(String id)
	{
	    this.id = id;
	}
	public String getId()
	{
	    return this.id;
	}
	public void setFname(String fname)
	{
	    this.fname = fname;
	}
	public String getFname()
	{
	    return this.fname;
	}
	public void setFlastsubmitter(String flastsubmitter)
	{
	    this.flastsubmitter = flastsubmitter;
	}
	public String getFlastsubmitter()
	{
	    return this.flastsubmitter;
	}
	public void setFlasttime(Date flasttime)
	{
	    this.flasttime = flasttime;
	}
	public Date getFlasttime()
	{
	    return this.flasttime;
	}
	
	public void setFlastremark(String flastremark)
	{
	    this.flastremark = flastremark;
	}
	public String getFlastremark()
	{
	    return this.flastremark;
	}
	
}
