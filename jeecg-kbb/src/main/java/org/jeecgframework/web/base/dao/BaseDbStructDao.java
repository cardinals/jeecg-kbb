package org.jeecgframework.web.base.dao;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;

@Repository("baseDbStructDao")
public interface BaseDbStructDao {
	@Arguments({"tableName","columnName","feildType","feildDesc"})
	public void alterTableAddColumn(String tableName,String columnName,String feildType,String feildDesc);
	@Arguments({"tableName","columnName"})
	public void alterTableDropColumn(String tableName,String columnName);
}


