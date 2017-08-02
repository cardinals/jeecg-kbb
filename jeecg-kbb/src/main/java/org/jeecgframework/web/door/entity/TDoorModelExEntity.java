package org.jeecgframework.web.door.entity;

@SuppressWarnings("serial")
public class TDoorModelExEntity implements java.io.Serializable {
	private java.lang.String fkey;
	private java.lang.String fcaption;
	private java.lang.String fvalue;
	
	
	public java.lang.String getFkey(){
		return this.fkey;
	}
	
	public void setFkey(java.lang.String fkey){
		this.fkey = fkey;
	}
	public java.lang.String getFcaption(){
		return this.fcaption;
	}
	
	public void setFcaption(java.lang.String fcaption){
		this.fcaption = fcaption;
	}
	public java.lang.String getFvalue(){
		return this.fvalue;
	}
	
	public void setFvalue(java.lang.String fvalue){
		this.fvalue = fvalue;
	}
}
