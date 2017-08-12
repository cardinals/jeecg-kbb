package org.jeecgframework.web.activiti.entity;

import java.io.Serializable;
import java.util.Date;

public class ATaskEntity implements Serializable {
	private String id;
	private String fname;
	private String fcreater;
	private Date fcreatetime;
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
	public void setFcreater(String fcreater)
	{
	    this.fcreater = fcreater;
	}
	public String getFcreater()
	{
	    return this.fcreater;
	}
	public void setFcreatetime(Date fcreatetime)
	{
	    this.fcreatetime = fcreatetime;
	}
	public Date getFcreatetime()
	{
	    return this.fcreatetime;
	}

}
