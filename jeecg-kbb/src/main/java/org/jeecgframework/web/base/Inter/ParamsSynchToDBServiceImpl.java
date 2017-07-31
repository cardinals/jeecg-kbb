package org.jeecgframework.web.base.Inter;

import java.util.Map;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.base.dao.BaseDbStructDao;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.cgform.service.autolist.CgTableServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paramsSynchToDB")
public class ParamsSynchToDBServiceImpl implements CgformEnhanceJavaInter {
	@Autowired
	private CommonService commonService;
	@Override
	public void execute(String tableName, Map map) throws BusinessException {
		// TODO Auto-generated method stub
		Long iCount=commonService.getCountForJdbc("SELECT count(1) FROM information_schema.columns WHERE table_name = 't_doors_model_ex' AND column_name ='"+ map.get("ffeildname") +"'");
		if(iCount==0){
			commonService.executeSql("ALTER TABLE t_doors_model_ex  ADD COLUMN `"+map.get("ffeildname")+"` nvarchar(100); ");
		}
		commonService.executeSql("update t_base_params set fisdbsynch ='已同步' where id=?",map.get("id"));
	}

}
