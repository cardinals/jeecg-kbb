package org.jeecgframework.web.base.service;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

public interface KBaseServiceI extends CommonService{
	


	void addNotice(String userid, String title, String content);

	String getUserIdbyRealName(String realName);

	String getUserRole(String userId);

	String getBillNo(String tableName, boolean bAutoIncrement);

	void incrementBillNo(String tableName);
}
