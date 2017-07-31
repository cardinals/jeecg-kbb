package org.jeecgframework.web.base.Inter;

import java.util.Map;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.base.dao.BaseDbStructDao;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paramsDelFromDB")
public class ParamsDelFromDBServiceImpl implements CgformEnhanceJavaInter {
	@Autowired
	private CommonService commonService;
	@Override
	public void execute(String tableName, Map map) throws BusinessException {
		// TODO Auto-generated method stub
		Long iCount=commonService.getCountForJdbc("SELECT count(1) FROM information_schema.columns WHERE table_name = 't_doors_model_ex' AND column_name ='"+ map.get("ffeildname") +"'");
		if(iCount>0){
			iCount=commonService.getCountForJdbc("SELECT count(1) FROM t_doors_model_ex where "+ map.get("ffeildname") +" is not null");
			if(iCount==0)
				commonService.executeSql("ALTER TABLE t_doors_model_ex  DROP COLUMN `"+map.get("ffeildname")+"`; ");
			else
				throw new BusinessException("该字段已经使用，不能删除。如果需要删除，请把该字段值全部置为NULL。");
		}		
	}

}
