package org.jeecgframework.web.offer.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.web.offer.entity.*;

public class WxOfferMainPage implements Serializable{



	private List<WxGroupInfos> groupInfo3s = new ArrayList<WxGroupInfos>();

	private List<WxGroupInfos> groupInfo4s = new ArrayList<WxGroupInfos>();
	
	private List<WxGroupInfos> groupInfo5s = new ArrayList<WxGroupInfos>();
	
	private List<WxRevolutionDoor> revolutionDoor = new ArrayList<WxRevolutionDoor>();
	private List<WxRevolutionDoor> smoothDoor = new ArrayList<WxRevolutionDoor>();
	
	private List<WxAttachment> attachment=new ArrayList<WxAttachment>();
	

	public List<WxGroupInfos> getGroupInfo3s() {
		return groupInfo3s;
	}

	public void setGroupInfo3s(List<WxGroupInfos> wxGroupInfo3s) {
		this.groupInfo3s = wxGroupInfo3s;
	}

	public List<WxGroupInfos> getGroupInfo4s() {
		return groupInfo4s;
	}

	public void setGroupInfo4s(List<WxGroupInfos> wxGroupInfo4s) {
		this.groupInfo4s = wxGroupInfo4s;
	}

	public List<WxGroupInfos> getGroupInfo5s() {
		return groupInfo5s;
	}

	public void setGroupInfo5s(List<WxGroupInfos> wxGroupInfo5s) {
		this.groupInfo5s = wxGroupInfo5s;
	}

	public List<WxRevolutionDoor> getRevolutionDoor() {
		return revolutionDoor;
	}

	public void setRevolutionDoor(List<WxRevolutionDoor> revolutionDoor) {
		this.revolutionDoor = revolutionDoor;
	}
	
	public List<WxRevolutionDoor> getSmoothDoor() {
		return smoothDoor;
	}

	public void setSmoothDoor(List<WxRevolutionDoor> smoothDoor) {
		this.smoothDoor = smoothDoor;
	}
	
	public List<WxAttachment> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<WxAttachment> attachment) {
		this.attachment = attachment;
	}
	
}
