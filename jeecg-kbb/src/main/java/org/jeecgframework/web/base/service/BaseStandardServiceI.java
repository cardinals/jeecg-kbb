package org.jeecgframework.web.base.service;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.base.entity.BaseStandardEntity;
import org.jeecgframework.web.base.entity.BaseSurfaceEntity;

public interface BaseStandardServiceI extends CommonService {
	public void delete(BaseStandardEntity entity) throws Exception;
 	
 	public Serializable save(BaseStandardEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaseStandardEntity entity) throws Exception;
 	
}
