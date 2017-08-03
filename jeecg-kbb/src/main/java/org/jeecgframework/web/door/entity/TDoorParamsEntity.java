package org.jeecgframework.web.door.entity;



@SuppressWarnings("serial")
public class TDoorParamsEntity implements java.io.Serializable {
	private String foreignid;
	
	private String fparamsid;
	
	private String ffeildname;
	
	private String fcaption;
	
	private String fremark;
	
	private Boolean fshow;

	public String getForeignid(){
		return this.foreignid;
	}
	public void setForeignid(String foreignid){
		this.foreignid=foreignid;
	}
	
	
	public String getFparamsid(){
		return this.fparamsid;
	}
	public void setFparamsid(String fparamsid){
		this.fparamsid=fparamsid;
	}
	public String getFcaption(){
		return this.fcaption;
	}
	public void setFfeildname(String ffeildname){
		this.ffeildname=ffeildname;
	}
	public String getFfeildname(){
		return this.ffeildname;
	}
	public void setFcaption(String fcaption){
		this.fcaption=fcaption;
	}

	public String getFremark(){
		return this.fremark;
	}
	public void setFremark(String fremark){
		this.fremark=fremark;
	}
	
	public Boolean getFshow(){
		return this.fshow;
	}
	public void setFshow(Boolean fshow){
		this.fshow=fshow;
	}
}
