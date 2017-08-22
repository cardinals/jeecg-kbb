package org.jeecgframework.web.activiti.entity;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class HistoryEntity implements Serializable {
	String name;
	String start_date;
	String end_date;
	String assigee;
	String message;
	String fullmessage;
	String processdefinitionid;
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){		
		return this.name;
	}
	
	public void setStart_date(String start_date){
		this.start_date=start_date;
	}
	public String getStart_date(){		
		return this.start_date;
	}
	
	public void setEnd_date(String end_date){
		this.end_date=end_date;
	}
	public String getEnd_date(){		
		return this.end_date;
	}
	
	public void setAssigee(String assigee){
		this.assigee=assigee;
	}
	public String getAssigee(){		
		return this.assigee;
	}
	public void setMessage(String message){
		this.message=message;
	}
	public String getMessage(){		
		return this.message;
	}
	public void setFullmessage(String fullmessage){
		this.fullmessage=fullmessage;
	}
	public String getFullmessage(){		
		return this.fullmessage;
	}
	public void setProcessdefinitionid(String processdefinitionid){
		this.processdefinitionid=processdefinitionid;
	}
	public String getProcessdefinitionid(){		
		return this.processdefinitionid;
	}
}
