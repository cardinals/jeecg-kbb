package org.jeecgframework.web.base.service.impl;

import java.io.Serializable;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.base.entity.BaseParamEntity;
import org.jeecgframework.web.base.entity.BaseSurfaceEntity;
import org.jeecgframework.web.base.service.BaseParamServiceI;
import org.jeecgframework.web.base.service.BaseSurfaceServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jodd.util.StringUtil;
@Service("baseParamService")
@Transactional
public class BaseParamServiceImpl  extends CommonServiceImpl  implements BaseParamServiceI {

	@Override
	public void delete(BaseParamEntity entity) throws Exception {
		// TODO Auto-generated method stub
		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
	}

	@Override
	public Serializable save(BaseParamEntity entity) throws Exception {
		// TODO Auto-generated method stub
		CheckRepat(entity);
		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
	}

	@Override
	public void saveOrUpdate(BaseParamEntity entity) throws Exception {
		// TODO Auto-generated method stub
		CheckRepat(entity);
		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
	}
	
	void CheckRepat(BaseParamEntity entity) throws Exception{
		if(StringUtil.isBlank(entity.getId())){
			Long count=this.getCountForJdbc("select * from t_base_params where ffeildname='"
			+ entity.getFfeildname() +"' or fcaption='"+ entity.getFcaption() +"'");
			if(count>0){
				throw new Exception("已经存在字段名或描述。");
			}		
		}
	}
	
	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BaseParamEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(BaseParamEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(BaseParamEntity t) throws Exception{
 	}

}
