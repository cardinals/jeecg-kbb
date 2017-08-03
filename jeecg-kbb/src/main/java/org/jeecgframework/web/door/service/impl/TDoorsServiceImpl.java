package org.jeecgframework.web.door.service.impl;
import org.jeecgframework.web.door.entity.*;
import org.jeecgframework.web.door.service.TDoorsServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	
	public void addMain(TDoorsEntity tDoors,Map<String,Map<String,Object>> tDoorModelMap,
			List<TDoorStandardEntity> tDoorStandardList,List<TDoorSurfaceEntity> tDoorSurfaceList,
			List<TDoorOptionsEntity> tDoorOptionsList,List<TDoorParamsEntity> tDoorParamsList){
			//保存主信息
			this.save(tDoors);			
//			Map<String,Map<String,String>> tDoorModelMap=JSON.parseObject(tDoorModelExMapJson,Map.class);
			Map<Long,Map<String,Object>> tDoorModelMapNew=new HashMap<Long,Map<String,Object>>();
			List<TDoorModelEntity> tDoorModelList=new ArrayList<TDoorModelEntity>();
			SplitModelMap(tDoorModelMap,tDoorModelList,tDoorModelMapNew);
			/**保存-型号*/		
			for(TDoorModelEntity tDoorsModel:tDoorModelList){		
				String uuid=UUID.randomUUID().toString().replaceAll("-", "");
				tDoorsModel.setId(uuid);
				//外键设置
				tDoorsModel.setForeignid(tDoors.getId());
				this.save(tDoorsModel);
				Map<String,Object> mapEx=tDoorModelMapNew.get(tDoorsModel.getFindex());				
				saveModelEx(uuid,tDoors.getId(),mapEx);
			}			
			/**保存-标准配件*/
			Long iIndex=(long) 1;
			for(TDoorStandardEntity tDoorStandard:tDoorStandardList){
				//外键设置
				tDoorStandard.setForeignid(tDoors.getId());
				tDoorStandard.setFindex(iIndex);
				this.save(tDoorStandard);
				iIndex++;
			}
			/**保存-表面处理*/
			iIndex=(long) 1;
			for(TDoorSurfaceEntity tDoorSurface:tDoorSurfaceList){
				//外键设置
				tDoorSurface.setForeignid(tDoors.getId());
				tDoorSurface.setFindex(iIndex);
				this.save(tDoorSurface);
				iIndex++;
			}
			/**保存-可选配件*/
			iIndex=(long) 1;
			for(TDoorOptionsEntity tDoorOptions:tDoorOptionsList){
				//外键设置
				tDoorOptions.setForeignid(tDoors.getId());
				tDoorOptions.setFindex(iIndex);
				this.save(tDoorOptions);
				iIndex++;
			}
			saveParams(tDoors,tDoorParamsList);
			//执行新增操作配置的sql增强
 			this.doAddSql(tDoors);
	}
	
	
	void saveModelEx(String id,String foreignid,Map<String,Object> mapEx){
		long lCount=this.getCountForJdbc("select count(1) from t_doors_model_ex where id='"+ id +"'");
		if(lCount==0){//新增
			StringBuilder sbFeild=new StringBuilder();
			StringBuilder sbVal=new StringBuilder();
			Iterator<Map.Entry<String,Object>> maps = mapEx.entrySet().iterator(); 
			while (maps.hasNext()) {  
				 Map.Entry<String,Object> map = maps.next();  
				 sbFeild.append(map.getKey()+",");
				 sbVal.append("'"+map.getValue()+"',");
			}
			sbFeild.append("id,foreignid");
			sbVal.append("'"+id+"','"+ foreignid+ "'");
			
			String sql=String.format("insert into t_doors_model_ex (%s) values (%s) ",sbFeild.toString(),sbVal.toString());
			this.executeSql(sql);
		}else{//更新
			StringBuilder sb=new StringBuilder();
		
			Iterator<Map.Entry<String,Object>> maps = mapEx.entrySet().iterator(); 
			while (maps.hasNext()) {  
				 Map.Entry<String,Object> map = maps.next();  
				 sb.append(map.getKey()+"='"+map.getValue()+"',");
			}
			
			String sql=String.format("update t_doors_model_ex set %s where id=?",StringUtils.substringBeforeLast(sb.toString(),","));
			this.executeSql(sql,id);
		}
	}

	void SplitModelMap(Map<String,Map<String,Object>> tDoorModelExMap,
			List<TDoorModelEntity> tDoorModelList,Map<Long,Map<String,Object>> tDoorModelExMapNew){
		Iterator<Map.Entry<String,Map<String,Object>>> entries = tDoorModelExMap.entrySet().iterator();  
		while (entries.hasNext()) {  
		    Map.Entry<String,Map<String,Object>> entry = entries.next();  		    
		    TDoorModelEntity entity=new TDoorModelEntity();
		    entity.setFindex(Long.parseLong(entry.getKey()));
		    Map<String,Object> mapEx=new HashMap<String,Object>();
		    mapEx.put("findex", entry.getKey());
			Iterator<Map.Entry<String,Object>> maps = entry.getValue().entrySet().iterator(); 
			while (maps.hasNext()) {  
			    Map.Entry<String,Object> map = maps.next();  
			    String fkey=map.getKey();
			    if(fkey.equals("id")){
					 entity.setId(map.getValue().toString());
				 }
				 if(fkey.equals("fnumber")){
					 entity.setFnumber(map.getValue().toString());
				 }else if(fkey.equals("fname")){
					 entity.setFname(map.getValue().toString());
				 }else if(fkey.equals("fmodel")){
					 entity.setFmodel(map.getValue().toString());
				 }else if(fkey.equals("fremark")){
					 entity.setFremark(map.getValue().toString());
				 }else if(fkey.equals("fprice")){
					 entity.setFprice(Double.parseDouble(map.getValue().toString()));
				 }else{
					 mapEx.put(map.getKey(), map.getValue());
				 }
			}			
			tDoorModelExMapNew.put(Long.parseLong(entry.getKey()), mapEx);
			tDoorModelList.add(entity);
		}
	}
	
	
	void saveParams(TDoorsEntity tDoors,List<TDoorParamsEntity> tDoorParamsList){
		
		executeSql("delete from t_door_params where fOREIGNID='"+tDoors.getId()+"';");
		for(TDoorParamsEntity entity :tDoorParamsList){
			
			String sql="insert into t_door_params (foreignid,fparamsid,fshow) ";
			String fShow="";
			if(entity.getFshow()!=null && entity.getFshow()){fShow="Y";}
			sql=sql+String.format("values ( '%s','%s','%s');",tDoors.getId(),entity.getFparamsid(),fShow);
			executeSql(sql);
		}
	}
	
	
	
	public void updateMain(TDoorsEntity tDoors,Map<String,Map<String,Object>> tDoorModelMap,
			List<TDoorStandardEntity> tDoorStandardList,List<TDoorSurfaceEntity> tDoorSurfaceList,
			List<TDoorOptionsEntity> tDoorOptionsList,List<TDoorParamsEntity> tDoorParamsList) {
		//保存主表信息
		this.saveOrUpdate(tDoors);
//		Map<String,Map<String,String>> tDoorModelMap=JSON.parseObject(tDoorModelExMapJson,Map.class);
		Map<Long,Map<String,Object>> tDoorModelMapNew=new HashMap<Long,Map<String,Object>>();
		List<TDoorModelEntity> tDoorModelList=new ArrayList<TDoorModelEntity>();
		SplitModelMap(tDoorModelMap,tDoorModelList,tDoorModelMapNew);
		//===================================================================================
		//获取参数
		Object id0 = tDoors.getId();
		Object id1 = tDoors.getId();
		Object id2 = tDoors.getId();
		Object id3 = tDoors.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-型号
	    String hql0 = "from TDoorModelEntity where 1 = 1 AND fOREIGNID = ? ";
	    List<TDoorModelEntity> tDoorModelOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-型号
		if(tDoorModelList!=null&&tDoorModelList.size()>0){
			for(TDoorModelEntity oldE:tDoorModelOldList){
				boolean isUpdate = false;
				for(TDoorModelEntity sendE:tDoorModelList){
					//需要更新的明细数据-型号
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
							saveModelEx(oldE.getId(),oldE.getForeignid(),tDoorModelMapNew.get(oldE.getFindex()));
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
		    		this.executeSql("delete from t_doors_model_ex where id=?", oldE.getId());
	    		}
			}
			//3.持久化新增的数据-型号
			for(TDoorModelEntity tDoorsModel:tDoorModelList){
				if(oConvertUtils.isEmpty(tDoorsModel.getId())){
					String uuid=UUID.randomUUID().toString().replaceAll("-", "");
					tDoorsModel.setId(uuid);
					//外键设置
					tDoorsModel.setForeignid(tDoors.getId());
					this.save(tDoorsModel);
					saveModelEx(uuid,tDoors.getId(),tDoorModelMapNew.get(tDoorsModel.getFindex()));
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
			Long iIndex=this.commonDao.getCountForJdbc("select ifnull(max(findex),0) from t_door_standard where foreignid='"+id1+"'");
			//3.持久化新增的数据-标准配件
			for(TDoorStandardEntity tDoorStandard:tDoorStandardList){
				if(oConvertUtils.isEmpty(tDoorStandard.getId())){
					//外键设置
					tDoorStandard.setForeignid(tDoors.getId());
					iIndex++;
					tDoorStandard.setFindex(iIndex);
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
		 Long iIndex=this.commonDao.getCountForJdbc("select ifnull(max(findex),0) from t_door_surface where foreignid='"+id2+"'");
			for(TDoorSurfaceEntity tDoorSurface:tDoorSurfaceList){
				if(oConvertUtils.isEmpty(tDoorSurface.getId())){
					//外键设置
					tDoorSurface.setForeignid(tDoors.getId());
					iIndex++;
					tDoorSurface.setFindex(iIndex);
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
		 Long iIndex=this.commonDao.getCountForJdbc("select ifnull(max(findex),0) from t_door_options where foreignid='"+id3+"'");
			for(TDoorOptionsEntity tDoorOptions:tDoorOptionsList){
				if(oConvertUtils.isEmpty(tDoorOptions.getId())){
					//外键设置
					tDoorOptions.setForeignid(tDoors.getId());
					iIndex++;
					tDoorOptions.setFindex(iIndex);
					this.save(tDoorOptions);
				}
			}
		}
		saveParams(tDoors,tDoorParamsList);
		//执行更新操作配置的sql增强
 		this.doUpdateSql(tDoors);
	}
	
	
	
	

	
	public void delMain(TDoorsEntity tDoors) {		
		//===================================================================================
		//获取参数
		Object id0 = tDoors.getId();
		Object id1 = tDoors.getId();
		Object id2 = tDoors.getId();
		Object id3 = tDoors.getId();
		//===================================================================================
		//删除-型号
	    String hql0 = "from TDoorModelEntity where 1 = 1 AND fOREIGNID = ? ";
	    List<TDoorModelEntity> tDoorsModelOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(tDoorsModelOldList);
		
		this.executeSql("delete from t_doors_model_ex where fOREIGNID=?",id0);
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
		
		this.executeSql("delete from t_doors_params where fOREIGNID=?",id0);
		//删除主表信息
		this.delete(tDoors);
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
 	
 	
 	public List<TDoorModelExEntity> buildDoorModelExEntityList(){
 		List<TDoorModelExEntity> tDoorModelListCaption=new ArrayList<TDoorModelExEntity>();
		tDoorModelListCaption.add(buildDoorModelExEntity("fnumber","代码"));
		tDoorModelListCaption.add(buildDoorModelExEntity("fname","名称"));
		tDoorModelListCaption.add(buildDoorModelExEntity("fmodel","规格型号"));
		
		List<Map<String,Object>> rs=this.commonDao.findForJdbc("select ffeildname,fcaption from t_base_params where fisdbsynch='已同步'");
		for(Map<String,Object> map:rs){
			tDoorModelListCaption.add(buildDoorModelExEntity(map.get("ffeildname").toString(),map.get("fcaption").toString()));
		}
		tDoorModelListCaption.add(buildDoorModelExEntity("fprice","价格"));
		tDoorModelListCaption.add(buildDoorModelExEntity("fremark","备注"));
		return tDoorModelListCaption;
 	}
 	
 	TDoorModelExEntity buildDoorModelExEntity(String fkey,String fcaption){
		TDoorModelExEntity exEntity=new TDoorModelExEntity();
		exEntity.setFkey(fkey);
		exEntity.setFcaption(fcaption);
		exEntity.setFvalue("");		
		return exEntity;
	}
 	
 	public Map<String,Map<String,Object>> getDoorModelExMap(Object foreignid){
 		List<Map<String,Object>> rsEx=this.commonDao.findForJdbc("select * from t_doors_model_ex where foreignid='"+ foreignid +"' order by findex");
 		List<Map<String,Object>> rs=this.commonDao.findForJdbc("select * from t_doors_model where foreignid='"+ foreignid +"' order by findex");
 		Map<String,Map<String,Object>> mapEx=new HashMap<String,Map<String,Object>>();
 		Integer iIndex=0;
		for(Map<String,Object> map:rs){
			map.putAll(rsEx.get(iIndex));
			mapEx.put(map.get("id").toString(), map);
			iIndex++;
		}
		return mapEx;
 	}
 	public List<TDoorParamsEntity> getTDoorParamsEntityList(Object foreignid){ 		
 		List<TDoorParamsEntity> tDoorParamsEnityList=new ArrayList<TDoorParamsEntity>();
 		StringBuilder sbSql=new StringBuilder();
 		sbSql.append("select t2.id,t2.fcaption,t2.ffeildname,t2.fremark,t1.fshow ");
 		sbSql.append("from t_door_params  t1 inner join t_base_params  t2 on t1.fparamsid=t2.id  ");
 		sbSql.append("where t2.fisdbsynch='已同步' and t1.foreignid=? ");
 		List<Map<String,Object>> rs=this.commonDao.findForJdbc(sbSql.toString(), foreignid);
 		if(rs.size()==0){
 			rs=this.commonDao.findForJdbc("select t2.id,t2.fcaption,t2.ffeildname,ifnull(t2.fremark,'') fremark,'' as fshow from t_base_params  t2 where t2.fisdbsynch='已同步'");
 		}
 		for(Map<String,Object> map:rs){
 			TDoorParamsEntity entity=new TDoorParamsEntity();
 			entity.setFparamsid(map.get("id").toString());
 			entity.setFcaption(map.get("fcaption").toString());
 			entity.setFfeildname(map.get("ffeildname").toString());
 			String fremark="";
 			if(map.get("fremark")!=null){fremark=map.get("fremark").toString();}
 			entity.setFremark(fremark);
 			entity.setFshow(map.get("fshow").toString().equals("Y"));
 			tDoorParamsEnityList.add(entity);
		}
 		return tDoorParamsEnityList;
 	}
 
 	
}