package com.jeecg.offer.service.impl;
import com.jeecg.offer.service.TOffersServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.offer.entity.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("tOffersService")
@Transactional
public class TOffersServiceImpl extends CommonServiceImpl implements TOffersServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((TOffersEntity)entity);
 	}
	
	public void addMain(TOffersEntity tOffers,
	        List<TOffersEntryEntity> tOffersEntryList){
			//保存主信息
			this.save(tOffers);
		
			/**保存-报价单明细*/
			for(TOffersEntryEntity tOffersEntry:tOffersEntryList){
				//外键设置
				tOffersEntry.setForeignid(tOffers.getId());
				this.save(tOffersEntry);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(tOffers);
	}

	
	public void updateMain(TOffersEntity tOffers,
	        List<TOffersEntryEntity> tOffersEntryList) {
		//保存主表信息
		this.saveOrUpdate(tOffers);
		//===================================================================================
		//获取参数
		Object id0 = tOffers.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-报价单明细
	    String hql0 = "from TOffersEntryEntity where 1 = 1 AND fOREIGNID = ? ";
	    List<TOffersEntryEntity> tOffersEntryOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-报价单明细
		if(tOffersEntryList!=null&&tOffersEntryList.size()>0){
		for(TOffersEntryEntity oldE:tOffersEntryOldList){
			boolean isUpdate = false;
				for(TOffersEntryEntity sendE:tOffersEntryList){
					//需要更新的明细数据-报价单明细
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-报价单明细
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-报价单明细
			for(TOffersEntryEntity tOffersEntry:tOffersEntryList){
				if(oConvertUtils.isEmpty(tOffersEntry.getId())){
					//外键设置
					tOffersEntry.setForeignid(tOffers.getId());
					this.save(tOffersEntry);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(tOffers);
	}

	
	public void delMain(TOffersEntity tOffers) {
		//删除主表信息
		this.delete(tOffers);
		//===================================================================================
		//获取参数
		Object id0 = tOffers.getId();
		//===================================================================================
		//删除-报价单明细
	    String hql0 = "from TOffersEntryEntity where 1 = 1 AND fOREIGNID = ? ";
	    List<TOffersEntryEntity> tOffersEntryOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(tOffersEntryOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TOffersEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TOffersEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TOffersEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,TOffersEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{fbillno}",String.valueOf(t.getFbillno()));
 		sql  = sql.replace("#{fprojectid}",String.valueOf(t.getFprojectid()));
 		sql  = sql.replace("#{fcustid}",String.valueOf(t.getFcustid()));
 		sql  = sql.replace("#{famount}",String.valueOf(t.getFamount()));
 		sql  = sql.replace("#{fstatus}",String.valueOf(t.getFstatus()));
 		sql  = sql.replace("#{fcurrent_approver}",String.valueOf(t.getFcurrentApprover()));
 		sql  = sql.replace("#{fapplicant}",String.valueOf(t.getFapplicant()));
 		sql  = sql.replace("#{fapplicant_date}",String.valueOf(t.getFapplicantDate()));
 		sql  = sql.replace("#{fremark}",String.valueOf(t.getFremark()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}