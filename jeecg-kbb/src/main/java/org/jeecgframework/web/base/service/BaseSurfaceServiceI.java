package org.jeecgframework.web.base.service;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.base.entity.BaseSurfaceEntity;

public interface BaseSurfaceServiceI extends CommonService {
	public void delete(BaseSurfaceEntity entity) throws Exception;
 	
 	public Serializable save(BaseSurfaceEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaseSurfaceEntity entity) throws Exception;
 	
}
