package com.jeecg.offer.entity;
import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 报价单明细
 * @author onlineGenerator
 * @date 2017-07-23 21:36:23
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_offers_entry", schema = "")
@SuppressWarnings("serial")
public class TOffersEntryEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**代码*/
    @Excel(name="代码")
	private java.lang.String fnumber;
	/**名称*/
    @Excel(name="名称")
	private java.lang.String fname;
	/**门型型号*/
    @Excel(name="门型型号")
	private java.lang.String fmodel;
	/**标准配件*/
    @Excel(name="标准配件")
	private java.lang.String fstandard;
	/**可选配件*/
    @Excel(name="可选配件")
	private java.lang.String foptions;
	/**表面处理*/
    @Excel(name="表面处理")
	private java.lang.String fsurface;
	/**系数*/
    @Excel(name="系数")
	private java.lang.Double fratio;
	/**价格*/
    @Excel(name="价格")
	private java.lang.Double fprice;
	/**金额*/
    @Excel(name="金额")
	private java.lang.Double famount;
	/**备注*/
    @Excel(name="备注")
	private java.lang.String fremark;
	/**外键*/
	private java.lang.String foreignid;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	
	@Column(name ="ID",nullable=false,length=36)
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
	 *@return: java.lang.String  代码
	 */
	
	@Column(name ="FNUMBER",nullable=true,length=50)
	public java.lang.String getFnumber(){
		return this.fnumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  代码
	 */
	public void setFnumber(java.lang.String fnumber){
		this.fnumber = fnumber;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  名称
	 */
	
	@Column(name ="FNAME",nullable=true,length=50)
	public java.lang.String getFname(){
		return this.fname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setFname(java.lang.String fname){
		this.fname = fname;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  门型型号
	 */
	
	@Column(name ="FMODEL",nullable=true,length=50)
	public java.lang.String getFmodel(){
		return this.fmodel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  门型型号
	 */
	public void setFmodel(java.lang.String fmodel){
		this.fmodel = fmodel;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标准配件
	 */
	
	@Column(name ="FSTANDARD",nullable=true,length=50)
	public java.lang.String getFstandard(){
		return this.fstandard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标准配件
	 */
	public void setFstandard(java.lang.String fstandard){
		this.fstandard = fstandard;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  可选配件
	 */
	
	@Column(name ="FOPTIONS",nullable=true,length=50)
	public java.lang.String getFoptions(){
		return this.foptions;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  可选配件
	 */
	public void setFoptions(java.lang.String foptions){
		this.foptions = foptions;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  表面处理
	 */
	
	@Column(name ="FSURFACE",nullable=true,length=50)
	public java.lang.String getFsurface(){
		return this.fsurface;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  表面处理
	 */
	public void setFsurface(java.lang.String fsurface){
		this.fsurface = fsurface;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  系数
	 */
	
	@Column(name ="FRATIO",nullable=true,length=50)
	public java.lang.Double getFratio(){
		return this.fratio;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  系数
	 */
	public void setFratio(java.lang.Double fratio){
		this.fratio = fratio;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  价格
	 */
	
	@Column(name ="FPRICE",nullable=true,length=20)
	public java.lang.Double getFprice(){
		return this.fprice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  价格
	 */
	public void setFprice(java.lang.Double fprice){
		this.fprice = fprice;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  金额
	 */
	
	@Column(name ="FAMOUNT",nullable=true,length=50)
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
	 *@return: java.lang.String  备注
	 */
	
	@Column(name ="FREMARK",nullable=true,length=200)
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
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外键
	 */
	
	@Column(name ="FOREIGNID",nullable=true,length=36)
	public java.lang.String getForeignid(){
		return this.foreignid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外键
	 */
	public void setForeignid(java.lang.String foreignid){
		this.foreignid = foreignid;
	}
	
}
