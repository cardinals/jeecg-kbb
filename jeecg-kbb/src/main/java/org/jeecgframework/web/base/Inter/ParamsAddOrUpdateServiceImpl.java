package org.jeecgframework.web.base.Inter;

import java.util.Map;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.jeecgframework.web.base.dao.BaseDbStructDao;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.cgform.service.autolist.CgTableServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paramsAddOrUpdate")
public class ParamsAddOrUpdateServiceImpl implements CgformEnhanceJavaInter {
	@Autowired
	private CommonService commonService;
	@Override
	public void execute(String tableName, Map map) throws BusinessException {		
		Long iCount=commonService.getCountForJdbc("SELECT count(1) FROM t_base_params where ffeildname='"
				+ map.get("ffeildname") +"' OR fcaption ='"+ map.get("fcaption") +"'");
		if(iCount>1){
			throw new BusinessException("已经存在该字段名或描述。");
		}		
	}

}
