package org.jeecgframework.web.door.service.impl;
import org.jeecgframework.web.door.entity.TDoorOptionsEntity;
import org.jeecgframework.web.door.entity.TDoorStandardEntity;
import org.jeecgframework.web.door.entity.TDoorSurfaceEntity;
import org.jeecgframework.web.door.entity.TDoorsEntity;
import org.jeecgframework.web.door.entity.TDoorsModelEntity;
import org.jeecgframework.web.door.service.TDoorsServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("tDoorsService")
@Transactional
public class TDoorsServiceImpl extends CommonServiceImpl implements TDoorsServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((TDoorsEntity)entity);
 	}
	
	public void addMain(TDoorsEntity tDoors,
	        List<TDoorsModelEntity> tDoorsModelList,List<TDoorStandardEntity> tDoorStandardList,List<TDoorSurfaceEntity> tDoorSurfaceList,List<TDoorOptionsEntity> tDoorOptionsList){
			//保存主信息
			this.save(tDoors);
		
			/**保存-型号*/
			for(TDoorsModelEntity tDoorsModel:tDoorsModelList){
				//外键设置
				tDoorsModel.setForeignid(tDoors.getId());
				this.save(tDoorsModel);
			}
			/**保存-标准配件*/
			for(TDoorStandardEntity tDoorStandard:tDoorStandardList){
				//外键设置
				tDoorStandard.setForeignid(tDoors.getId());
				this.save(tDoorStandard);
			}
			/**保存-表面处理*/
			for(TDoorSurfaceEntity tDoorSurface:tDoorSurfaceList){
				//外键设置
				tDoorSurface.setForeignid(tDoors.getId());
				this.save(tDoorSurface);
			}
			/**保存-可选配件*/
			for(TDoorOptionsEntity tDoorOptions:tDoorOptionsList){
				//外键设置
				tDoorOptions.setForeignid(tDoors.getId());
				this.save(tDoorOptions);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(tDoors);
	}

	
	public void updateMain(TDoorsEntity tDoors,
	        List<TDoorsModelEntity> tDoorsModelList,List<TDoorStandardEntity> tDoorStandardList,List<TDoorSurfaceEntity> tDoorSurfaceList,List<TDoorOptionsEntity> tDoorOptionsList) {
		//保存主表信息
		this.saveOrUpdate(tDoors);
		//===================================================================================
		//获取参数
		Object id0 = tDoors.getId();
		Object id1 = tDoors.getId();
		Object id2 = tDoors.getId();
		Object id3 = tDoors.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-型号
	    String hql0 = "from TDoorsModelEntity where 1 = 1 AND fOREIGNID = ? ";
	    List<TDoorsModelEntity> tDoorsModelOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-型号
		if(tDoorsModelList!=null&&tDoorsModelList.size()>0){
		for(TDoorsModelEntity oldE:tDoorsModelOldList){
			boolean isUpdate = false;
				for(TDoorsModelEntity sendE:tDoorsModelList){
					//需要更新的明细数据-型号
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-型号
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-型号
			for(TDoorsModelEntity tDoorsModel:tDoorsModelList){
				if(oConvertUtils.isEmpty(tDoorsModel.getId())){
					//外键设置
					tDoorsModel.setForeignid(tDoors.getId());
					this.save(tDoorsModel);
				}
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-标准配件
	    String hql1 = "from TDoorStandardEntity where 1 = 1 AND fOREIGNID = ? ";
	    List<TDoorStandardEntity> tDoorStandardOldList = this.findHql(hql1,id1);
		//2.筛选更新明细数据-标准配件
		if(tDoorStandardList!=null&&tDoorStandardList.size()>0){
		for(TDoorStandardEntity oldE:tDoorStandardOldList){
			boolean isUpdate = false;
				for(TDoorStandardEntity sendE:tDoorStandardList){
					//需要更新的明细数据-标准配件
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-标准配件
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-标准配件
			for(TDoorStandardEntity tDoorStandard:tDoorStandardList){
				if(oConvertUtils.isEmpty(tDoorStandard.getId())){
					//外键设置
					tDoorStandard.setForeignid(tDoors.getId());
					this.save(tDoorStandard);
				}
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-表面处理
	    String hql2 = "from TDoorSurfaceEntity where 1 = 1 AND fOREIGNID = ? ";
	    List<TDoorSurfaceEntity> tDoorSurfaceOldList = this.findHql(hql2,id2);
		//2.筛选更新明细数据-表面处理
		if(tDoorSurfaceList!=null&&tDoorSurfaceList.size()>0){
		for(TDoorSurfaceEntity oldE:tDoorSurfaceOldList){
			boolean isUpdate = false;
				for(TDoorSurfaceEntity sendE:tDoorSurfaceList){
					//需要更新的明细数据-表面处理
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-表面处理
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-表面处理
			for(TDoorSurfaceEntity tDoorSurface:tDoorSurfaceList){
				if(oConvertUtils.isEmpty(tDoorSurface.getId())){
					//外键设置
					tDoorSurface.setForeignid(tDoors.getId());
					this.save(tDoorSurface);
				}
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-可选配件
	    String hql3 = "from TDoorOptionsEntity where 1 = 1 AND fOREIGNID = ? ";
	    List<TDoorOptionsEntity> tDoorOptionsOldList = this.findHql(hql3,id3);
		//2.筛选更新明细数据-可选配件
		if(tDoorOptionsList!=null&&tDoorOptionsList.size()>0){
		for(TDoorOptionsEntity oldE:tDoorOptionsOldList){
			boolean isUpdate = false;
				for(TDoorOptionsEntity sendE:tDoorOptionsList){
					//需要更新的明细数据-可选配件
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-可选配件
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-可选配件
			for(TDoorOptionsEntity tDoorOptions:tDoorOptionsList){
				if(oConvertUtils.isEmpty(tDoorOptions.getId())){
					//外键设置
					tDoorOptions.setForeignid(tDoors.getId());
					this.save(tDoorOptions);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(tDoors);
	}

	
	public void delMain(TDoorsEntity tDoors) {
		//删除主表信息
		this.delete(tDoors);
		//===================================================================================
		//获取参数
		Object id0 = tDoors.getId();
		Object id1 = tDoors.getId();
		Object id2 = tDoors.getId();
		Object id3 = tDoors.getId();
		//===================================================================================
		//删除-型号
	    String hql0 = "from TDoorsModelEntity where 1 = 1 AND fOREIGNID = ? ";
	    List<TDoorsModelEntity> tDoorsModelOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(tDoorsModelOldList);
		//===================================================================================
		//删除-标准配件
	    String hql1 = "from TDoorStandardEntity where 1 = 1 AND fOREIGNID = ? ";
	    List<TDoorStandardEntity> tDoorStandardOldList = this.findHql(hql1,id1);
		this.deleteAllEntitie(tDoorStandardOldList);
		//===================================================================================
		//删除-表面处理
	    String hql2 = "from TDoorSurfaceEntity where 1 = 1 AND fOREIGNID = ? ";
	    List<TDoorSurfaceEntity> tDoorSurfaceOldList = this.findHql(hql2,id2);
		this.deleteAllEntitie(tDoorSurfaceOldList);
		//===================================================================================
		//删除-可选配件
	    String hql3 = "from TDoorOptionsEntity where 1 = 1 AND fOREIGNID = ? ";
	    List<TDoorOptionsEntity> tDoorOptionsOldList = this.findHql(hql3,id3);
		this.deleteAllEntitie(tDoorOptionsOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TDoorsEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TDoorsEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TDoorsEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,TDoorsEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{fnumber}",String.valueOf(t.getFnumber()));
 		sql  = sql.replace("#{fname}",String.valueOf(t.getFname()));
 		sql  = sql.replace("#{fdoortype}",String.valueOf(t.getFdoortype()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}