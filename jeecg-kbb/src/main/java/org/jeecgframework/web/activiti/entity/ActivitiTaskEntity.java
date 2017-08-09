package org.jeecgframework.web.activiti.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ActivitiTaskEntity implements Serializable {
	private String id ;
	/*
	 * 批注
	 * */
	private String comment;
	/*
	 * 流程变量
	 * */
	private String message;
}
