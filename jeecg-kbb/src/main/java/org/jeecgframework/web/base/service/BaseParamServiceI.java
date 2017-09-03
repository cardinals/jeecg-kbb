package org.jeecgframework.web.base.service;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.base.entity.BaseParamEntity;

public interface BaseParamServiceI extends CommonService {
	public void delete(BaseParamEntity entity) throws Exception;
 	
 	public Serializable save(BaseParamEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaseParamEntity entity) throws Exception;
 	
}
