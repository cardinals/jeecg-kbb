package com.jeecg.offer.entity;

import java.io.Serializable;
import java.util.Date;


import java.math.BigDecimal;

/**
 * 描述：</b>WxActCommoninvite:活动表<br>
 * 实体定义规则
 * 字段不允许存在基本类型，必须都是包装类型(因为基本类型有默认值)
 * 基本数据类型  包装类 byte Byte boolean Boolean short Short char Character int Integer long Long float Float double  Double 
 * @author Administrator
 *
 */
public class WxOffer implements Serializable {
	private static final long serialVersionUID = 1L;
		/**	 *ID	 */	private String id;	
	/**单号*/

	private String fbillno;
	/**项目*/

	private String fprojectid;
	/**客户*/

	private String fcustid;
	/**金额*/

	private Double famount;
	/**状态*/

	private String fstatus;
	/**当前审批人*/

	private String fcurrent_approver;
	/**申请人*/

	private String fapplicant;
	/**申请时间*/

	private Date fapplicant_date;
	/**备注*/

	private String fremark;	/**	 *入口地址	 */	private String hdurl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单号
	 */
	public java.lang.String getFbillno(){
		return this.fbillno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单号
	 */
	public void setFbillno(java.lang.String fbillno){
		this.fbillno = fbillno;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目
	 */
	public java.lang.String getFprojectid(){
		return this.fprojectid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目
	 */
	public void setFprojectid(java.lang.String fprojectid){
		this.fprojectid = fprojectid;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户
	 */	
	public java.lang.String getFcustid(){
		return this.fcustid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户
	 */
	public void setFcustid(java.lang.String fcustid){
		this.fcustid = fcustid;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  金额
	 */	
	public java.lang.Double getFamount(){
		return this.famount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  金额
	 */
	public void setFamount(java.lang.Double famount){
		this.famount = famount;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	public java.lang.String getFstatus(){
		return this.fstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setFstatus(java.lang.String fstatus){
		this.fstatus = fstatus;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当前审批人
	 */
	public java.lang.String getFcurrent_Approver(){
		return this.fcurrent_approver;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当前审批人
	 */
	public void setFcurrentApprover(java.lang.String fcurrent_approver){
		this.fcurrent_approver = fcurrent_approver;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请人
	 */
	
	public java.lang.String getFapplicant(){
		return this.fapplicant;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请人
	 */
	public void setFapplicant(java.lang.String fapplicant){
		this.fapplicant = fapplicant;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  申请时间
	 */
	
	public java.util.Date getfapplicant_date(){
		return this.fapplicant_date;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  申请时间
	 */
	public void setfapplicant_date(java.util.Date fapplicant_date){
		this.fapplicant_date = fapplicant_date;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	public java.lang.String getFremark(){
		return this.fremark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setFremark(java.lang.String fremark){
		this.fremark = fremark;
	}
	public String getHdurl() {
		return hdurl;
	}
	public void setHdurl(String hdurl) {
		this.hdurl = hdurl;
	}
}

