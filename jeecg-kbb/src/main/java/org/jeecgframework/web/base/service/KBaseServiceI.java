package org.jeecgframework.web.base.service;


public interface KBaseServiceI{
	
	/*
	 *获取单号 
	 * */
	String getBillNo(String tableName);
	/*
	 * 发送消息
	 * */
	void sendMessage(String user,String msgType,String msg);
}
