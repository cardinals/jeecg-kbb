package org.jeecgframework.web.offer.dao;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;

import org.jeecgframework.web.offer.entity.*;

@Repository
public interface WxBillNoRuleDao {
	@Sql("select * from t_config_billno where ftablename=:tablename")
	WxBillNoRule get(@Param("tablename") String tablename);
	@Sql("update t_config_billno set fnum=fnum+1 where ftablename=:tablename")
	int update(@Param("tablename") String tablename);
}
