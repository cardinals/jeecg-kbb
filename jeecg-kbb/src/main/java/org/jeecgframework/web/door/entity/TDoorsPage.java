
package org.jeecgframework.web.door.entity;

import org.jeecgframework.web.cgform.entity.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @Description: 门型维护
 * @author onlineGenerator
 * @date 2017-08-01 18:23:31
 * @version V1.0   
 *
 */
public class TDoorsPage implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**代码*/
    @Excel(name="代码")
	private java.lang.String fnumber;
	/**名称*/
    @Excel(name="名称")
	private java.lang.String fname;
	/**类型*/
    @Excel(name="类型")
	private java.lang.String fdoortype;
	
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
	 *@return: java.lang.String  代码
	 */
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
	 *@return: java.lang.String  类型
	 */
	public java.lang.String getFdoortype(){
		return this.fdoortype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setFdoortype(java.lang.String fdoortype){
		this.fdoortype = fdoortype;
	}

	/**保存-型号*/
    @ExcelCollection(name="型号")
	private List<TDoorModelEntity> tDoorModelList = new ArrayList<TDoorModelEntity>();
		public List<TDoorModelEntity> getTDoorModelList() {
		return tDoorModelList;
		}
		public void setTDoorModelList(List<TDoorModelEntity> tDoorModelList) {
		this.tDoorModelList = tDoorModelList;
		}
	@ExcelCollection(name="型号扩展参数")	
	private Map<String,Map<String,String>> tDoorModelExMap = new HashMap<String,Map<String,String>>();
		public Map<String,Map<String,String>> getTDoorModelExMap() {
		return tDoorModelExMap;
		}
		public void setTDoorModelExMap(Map<String,Map<String,String>> tDoorModelExMap) {
		this.tDoorModelExMap= tDoorModelExMap;
		}
	/**保存-标准配件*/
    @ExcelCollection(name="标准配件")
	private List<TDoorStandardEntity> tDoorStandardList = new ArrayList<TDoorStandardEntity>();
		public List<TDoorStandardEntity> getTDoorStandardList() {
		return tDoorStandardList;
		}
		public void setTDoorStandardList(List<TDoorStandardEntity> tDoorStandardList) {
		this.tDoorStandardList = tDoorStandardList;
		}
	/**保存-表面处理*/
    @ExcelCollection(name="表面处理")
	private List<TDoorSurfaceEntity> tDoorSurfaceList = new ArrayList<TDoorSurfaceEntity>();
		public List<TDoorSurfaceEntity> getTDoorSurfaceList() {
		return tDoorSurfaceList;
		}
		public void setTDoorSurfaceList(List<TDoorSurfaceEntity> tDoorSurfaceList) {
		this.tDoorSurfaceList = tDoorSurfaceList;
		}
	/**保存-可选配件*/
    @ExcelCollection(name="可选配件")
	private List<TDoorOptionsEntity> tDoorOptionsList = new ArrayList<TDoorOptionsEntity>();
		public List<TDoorOptionsEntity> getTDoorOptionsList() {
		return tDoorOptionsList;
		}
		public void setTDoorOptionsList(List<TDoorOptionsEntity> tDoorOptionsList) {
		this.tDoorOptionsList = tDoorOptionsList;
		}
}
