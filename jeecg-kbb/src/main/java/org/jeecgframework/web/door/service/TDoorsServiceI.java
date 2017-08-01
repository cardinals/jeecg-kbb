package org.jeecgframework.web.door.service;
import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.door.entity.TDoorOptionsEntity;
import org.jeecgframework.web.door.entity.TDoorStandardEntity;
import org.jeecgframework.web.door.entity.TDoorSurfaceEntity;
import org.jeecgframework.web.door.entity.TDoorsEntity;
import org.jeecgframework.web.door.entity.TDoorsModelEntity;

import java.io.Serializable;

public interface TDoorsServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(TDoorsEntity tDoors,
	        List<TDoorsModelEntity> tDoorsModelList,List<TDoorStandardEntity> tDoorStandardList,List<TDoorSurfaceEntity> tDoorSurfaceList,List<TDoorOptionsEntity> tDoorOptionsList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TDoorsEntity tDoors,
	        List<TDoorsModelEntity> tDoorsModelList,List<TDoorStandardEntity> tDoorStandardList,List<TDoorSurfaceEntity> tDoorSurfaceList,List<TDoorOptionsEntity> tDoorOptionsList);
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
}
