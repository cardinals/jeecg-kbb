package org.jeecgframework.web.activiti.entity;

import java.io.Serializable;
import java.util.Date;

public class ATaskEntity implements Serializable {
	private String id;
	
	private String fbusinesskey;
	private String fname;
	private String flastsubmitter;
	private String flasttime;
	private String flastremark;
	
	private String fbillno;
	/*历史*/
	private String finitiator;
	private String fstarttime;
	private String fendtime;
	
	/*历史*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setFbusinesskey(String fbusinesskey)
	{
	    this.fbusinesskey = fbusinesskey;
	}
	public String getFbusinesskey()
	{
	    return this.fbusinesskey;
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
	public void setFlasttime(String flasttime)
	{
	    this.flasttime = flasttime;
	}
	public String getFlasttime()
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
	public String getFbillno() {
		return fbillno;
	}
	public String getFinitiator() {
		return finitiator;
	}
	public String getFstarttime() {
		return fstarttime;
	}
	public String getFendtime() {
		return fendtime;
	}
	public void setFbillno(String fbillno) {
		this.fbillno = fbillno;
	}
	public void setFinitiator(String finitiator) {
		this.finitiator = finitiator;
	}
	public void setFstarttime(String fstarttime) {
		this.fstarttime = fstarttime;
	}
	public void setFendtime(String fendtime) {
		this.fendtime = fendtime;
	}
	
}
