package com.jeecg.offer.entity;

import java.io.Serializable;

public class WxAttachment implements Serializable {
	private String id;
	private String fileid;
	private String path;
	private String filename;
	
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getFileid(){
		return this.fileid;
	}
	public void setFileid(String fileid){
		this.fileid=fileid;
	}
	public String getPath(){
		return this.path;
	}
	public void setPath(String path){
		this.path=path;
	}
	public String getFilename(){
		return this.filename;
	}
	public void setFilename(String filename){
		this.filename=filename;
	}
}
