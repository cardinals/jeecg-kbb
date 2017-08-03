package org.jeecgframework.web.door.service;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.door.entity.TDoorOptionsEntity;
import org.jeecgframework.web.door.entity.TDoorStandardEntity;
import org.jeecgframework.web.door.entity.TDoorSurfaceEntity;
import org.jeecgframework.web.door.entity.TDoorsEntity;
import org.jeecgframework.web.door.entity.TDoorModelEntity;
import org.jeecgframework.web.door.entity.TDoorModelExEntity;

import java.io.Serializable;

public interface TDoorsServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(TDoorsEntity tDoors,Map<String,Map<String,Object>> tDoorModelMap,
	        List<TDoorStandardEntity> tDoorStandardList,List<TDoorSurfaceEntity> tDoorSurfaceList,List<TDoorOptionsEntity> tDoorOptionsList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TDoorsEntity tDoors,Map<String,Map<String,Object>> tDoorModelMap,
	        List<TDoorStandardEntity> tDoorStandardList,List<TDoorSurfaceEntity> tDoorSurfaceList,List<TDoorOptionsEntity> tDoorOptionsList);
	public void delMain (TDoorsEntity tDoors);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TDoorsEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TDoorsEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TDoorsEntity t);
 	
 	public List<TDoorModelExEntity> buildDoorModelExEntityList();
 	public Map<String,Map<String,Object>> getDoorModelExMap(Object foreignid);
}
