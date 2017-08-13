package org.jeecgframework.web.activiti.entity;

import java.io.Serializable;

public class ProcessorEntity implements Serializable {
	private String id;
	private String fname;
	
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	
	public void setFname(String fname){
		this.fname=fname;
	}
	public String getFname(){
		return fname;
	}
}
