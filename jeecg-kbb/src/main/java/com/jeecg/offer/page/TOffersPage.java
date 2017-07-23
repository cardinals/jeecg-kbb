
package com.jeecg.offer.page;
import com.jeecg.offer.entity.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

/**   
 * @Title: Entity
 * @Description: 报价单
 * @author onlineGenerator
 * @date 2017-07-23 21:36:23
 * @version V1.0   
 *
 */
public class TOffersPage implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**单号*/
    @Excel(name="单号")
	private java.lang.String fbillno;
	/**项目*/
    @Excel(name="项目")
	private java.lang.String fprojectid;
	/**客户*/
    @Excel(name="客户")
	private java.lang.String fcustid;
	/**金额*/
    @Excel(name="金额")
	private java.lang.Double famount;
	/**状态*/
    @Excel(name="状态")
	private java.lang.String fstatus;
	/**当前审批人*/
    @Excel(name="当前审批人")
	private java.lang.String fcurrentApprover;
	/**申请人*/
    @Excel(name="申请人")
	private java.lang.String fapplicant;
	/**申请时间*/
    @Excel(name="申请时间",format = "yyyy-MM-dd")
	private java.util.Date fapplicantDate;
	/**备注*/
    @Excel(name="备注")
	private java.lang.String fremark;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
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
	public java.lang.String getFcurrentApprover(){
		return this.fcurrentApprover;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当前审批人
	 */
	public void setFcurrentApprover(java.lang.String fcurrentApprover){
		this.fcurrentApprover = fcurrentApprover;
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
	public java.util.Date getFapplicantDate(){
		return this.fapplicantDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  申请时间
	 */
	public void setFapplicantDate(java.util.Date fapplicantDate){
		this.fapplicantDate = fapplicantDate;
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

	/**保存-报价单明细*/
    @ExcelCollection(name="报价单明细")
	private List<TOffersEntryEntity> tOffersEntryList = new ArrayList<TOffersEntryEntity>();
		public List<TOffersEntryEntity> getTOffersEntryList() {
		return tOffersEntryList;
		}
		public void setTOffersEntryList(List<TOffersEntryEntity> tOffersEntryList) {
		this.tOffersEntryList = tOffersEntryList;
		}
}
