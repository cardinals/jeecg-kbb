package org.jeecgframework.web.base.service.impl;

import java.io.Serializable;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.base.entity.BaseSurfaceEntity;
import org.jeecgframework.web.base.service.BaseSurfaceServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("baseSurfaceService")
@Transactional
public class BaseSurfaceServiceImpl  extends CommonServiceImpl  implements BaseSurfaceServiceI {

	@Override
	public void delete(BaseSurfaceEntity entity) throws Exception {
		// TODO Auto-generated method stub
		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
	}

	@Override
	public Serializable save(BaseSurfaceEntity entity) throws Exception {
		// TODO Auto-generated method stub
		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
	}

	@Override
	public void saveOrUpdate(BaseSurfaceEntity entity) throws Exception {
		// TODO Auto-generated method stub
		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
	}
	
	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BaseSurfaceEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(BaseSurfaceEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(BaseSurfaceEntity t) throws Exception{
 	}

}
