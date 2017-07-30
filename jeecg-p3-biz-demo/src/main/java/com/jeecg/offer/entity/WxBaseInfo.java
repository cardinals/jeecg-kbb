package com.jeecg.offer.entity;

import java.io.Serializable;

public class WxBaseInfo  implements Serializable {
	private String id;
	private String fnumber;
	private String fname;
	
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		
		return this.id;
	}
	public void setFnumber(String fnumber){
		this.fnumber=fnumber;
	}
	public String getFnumber(){
		
		return this.fnumber;
	}
	public void setFname(String fname){
		this.fname=fname;
	}
	public String getFname(){
		
		return this.fname;
	}
}
