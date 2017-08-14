package org.jeecgframework.web.base.entity;

import java.io.Serializable;

public class BillRuleEntity  implements Serializable {
	private String frule;
	private Integer fnum;
	
	public void setFrule(String frule){
		this.frule=frule;
	}
	public String getFrule(){
		
		return this.frule;
	}
	public void setFnum(Integer fnum){
		this.fnum=fnum;
	}
	public Integer getFnum(){
		
		return this.fnum;
	}
}
