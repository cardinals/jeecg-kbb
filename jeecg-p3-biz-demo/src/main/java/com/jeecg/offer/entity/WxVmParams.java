package com.jeecg.offer.entity;

import java.io.Serializable;

public class WxVmParams implements Serializable {
	private String fcode;
	private String fcaption;
	private String ftag;
	private String fvalue;
	
	public void setFcode(String fcode)
	{
	    this.fcode = fcode;
	}
	public String getFcode()
	{
	    return this.fcode;
	}
	public void setFcaption(String fcaption)
	{
	    this.fcaption = fcaption;
	}
	public String getFcaption()
	{
	    return this.fcaption;
	}
	public void setFtag(String ftag)
	{
	    this.ftag = ftag;
	}
	public String getFtag()
	{
	    return this.ftag;
	}
	public void setFvalue(String fvalue)
	{
	    this.fvalue = fvalue;
	}
	public String getFvalue()
	{
	    return this.fvalue;
	}

}
