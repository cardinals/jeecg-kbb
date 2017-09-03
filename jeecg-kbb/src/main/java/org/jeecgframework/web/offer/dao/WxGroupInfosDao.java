package org.jeecgframework.web.offer.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;
import org.jeecgframework.web.offer.entity.*;



@Repository
public interface WxGroupInfosDao {
	/**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */	
	@Sql("select * from t_offer_options where id=:id order by findex")
	List<WxGroupInfos> get(@Param("id") String id);

	/**
	 * 修改数据
	 * @param act
	 * @return
	 */
//	int update(@Param("info") WxGroupInfos info);

	/**
	 * 插入数据
	 * @param act
	 */
	@Sql("INSERT INTO t_offer_options (id,group_id,findex,model,quantity,price,amount,remark,brand,standard) "
			+ "VALUES (:info.id,:info.group_id,:info.findex,:info.model,:info.quantity,:info.price,:info.amount,:info.remark,:info.brand,:info.standard);")
	void insert(@Param("info") WxGroupInfos info);
	

	@Sql("DELETE from t_offer_options WHERE ID = :id")
	public void delete(@Param("id") String id);
	
	@Sql("select * from t_offer_group_option")
	public List<WxGroupInfos> getDefaultGroupInfos();
}
