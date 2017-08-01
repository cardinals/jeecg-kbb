package org.jeecgframework.web.door.entity;
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
 * @Description: 型号
 * @author onlineGenerator
 * @date 2017-08-01 18:23:31
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_doors_model", schema = "")
@SuppressWarnings("serial")
public class TDoorsModelEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**代码*/
    @Excel(name="代码")
	private java.lang.String fnumber;
	/**名称*/
    @Excel(name="名称")
	private java.lang.String fname;
	/**规格型号*/
    @Excel(name="规格型号")
	private java.lang.String fmodel;
	/**备注*/
    @Excel(name="备注")
	private java.lang.String fremark;
	/**价格*/
    @Excel(name="价格")
	private java.lang.Double fprice;
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
	
	@Column(name ="FNUMBER",nullable=false,length=50)
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
	
	@Column(name ="FNAME",nullable=false,length=100)
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
	 *@return: java.lang.String  规格型号
	 */
	
	@Column(name ="FMODEL",nullable=true,length=20)
	public java.lang.String getFmodel(){
		return this.fmodel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格型号
	 */
	public void setFmodel(java.lang.String fmodel){
		this.fmodel = fmodel;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	
	@Column(name ="FREMARK",nullable=true,length=50)
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  价格
	 */
	
	@Column(name ="FPRICE",nullable=true,length=50)
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外键
	 */
	
	@Column(name ="FOREIGNID",nullable=false,length=32)
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
