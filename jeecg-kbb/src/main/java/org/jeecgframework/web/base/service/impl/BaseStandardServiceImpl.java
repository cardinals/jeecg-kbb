package org.jeecgframework.web.base.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.base.entity.BaseStandardEntity;
import org.jeecgframework.web.base.entity.BaseSurfaceEntity;
import org.jeecgframework.web.base.service.BaseStandardServiceI;
import org.jeecgframework.web.base.service.BaseSurfaceServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("baseStandardService")
@Transactional
public class BaseStandardServiceImpl  extends CommonServiceImpl  implements BaseStandardServiceI {

	@Override
	public void delete(BaseStandardEntity entity) throws Exception {
		// TODO Auto-generated method stub
		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
	}

	@Override
	public Serializable save(BaseStandardEntity entity) throws Exception {
		// TODO Auto-generated method stub
		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
	}

	@Override
	public void saveOrUpdate(BaseStandardEntity entity) throws Exception {
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
	private void doAddBus(BaseStandardEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(BaseStandardEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(BaseStandardEntity t) throws Exception{
 	}
	@Override
	public List<Map<String, Object>> getStandardType(){
		String sql="select t1.id as value,t1.typename as text from t_s_type t1 inner join t_s_typegroup t2 on t1.typegroupid=t2.id where t2.typegroupcode=?";
		return this.commonDao.findForJdbc(sql, "standardtype");
	}
}
