package org.jeecgframework.web.offer.dao;

import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;
import org.jeecgframework.web.base.entity.BaseStandardEntity;
import org.jeecgframework.web.offer.entity.*;

@Repository
public interface WxBaseDao {

	@Sql("select id,fnumber,fname from t_base_project")
	List<WxBaseInfo> getProject();
	
	@Sql("select id,fnumber,fname from t_base_customer")
	List<WxBaseInfo> getCustomer();
	
	@Sql("select id,fnumber,fname from t_doors where fdoortype=:filter")
	List<WxBaseInfo> getDoors(@Param("filter") String filter);
	
	@Sql("select * from t_base_standard")
	List<BaseStandardEntity> getStandards();
	
	@Sql("select t2.rolecode from t_s_role_user t1 inner join t_s_role t2 on t1.roleid=t2.id  where t1.userid=:userid")
	List<Map<String,Object>> getUserRole(@Param("userid") String userid);
	/*
	 * 获取下属用户
	 * */
	@Sql("select buser.*  from t_s_user_org org "
+ " inner join t_s_depart part on org.org_id=part.ID "
+ " inner join t_s_depart partp on part.parentdepartid=partp.id "
+ " inner join t_s_user_org orgp on orgp.org_id=partp.ID "
+ " inner join t_s_base_user buser on buser.ID=org.user_id "
+ " where orgp.user_id=:userid")
	List<Map<String,Object>> getSubordinateUser(@Param("userid") String userid);
}
