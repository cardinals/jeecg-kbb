package com.jeecg.offer.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;

import com.jeecg.offer.entity.WxBaseInfo;
import com.jeecg.offer.entity.WxBillNoRule;

@Repository
public interface WxBaseDao {

	@Sql("select id,fnumber,fname from t_base_project")
	List<WxBaseInfo> getProject();
	
	@Sql("select id,fnumber,fname from t_base_customer")
	List<WxBaseInfo> getCustomer();
	
	@Sql("select id,fnumber,fname from t_doors where fdoortype=:filter")
	List<WxBaseInfo> getDoors(@Param("filter") String filter);
	
	
	
	
}
