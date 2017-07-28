package com.jeecg.offer.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;
import com.jeecg.offer.entity.WxGroupInfos;



@Repository
public interface WxGroupInfosDao {
	/**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */	
	@Sql("select t2.id,t1.group_id,t1.findex,t2.model,t2.quantity,t2.price,t2.amount,t2.remark,t1.fname,t1.unit" 
			+" from t_offer_group_option t1 left join t_offer_options t2 on t1.group_id=t2.group_id and t1.findex=t2.findex" 
			+ " where t1.group_id=:group_id and (t2.id=:id OR IFNULL(t2.id,'')='') order by t1.findex")
	List<WxGroupInfos> get(@Param("group_id") String group_id,@Param("id") String id);

	/**
	 * 修改数据
	 * @param act
	 * @return
	 */
	int update(@Param("info") WxGroupInfos info);

	/**
	 * 插入数据
	 * @param act
	 */
	@Sql("INSERT INTO t_offer_options (id,group_id,findex,model,quantity,price,amount,remark) "
			+ "VALUES (:info.id,:info.group_id,:info.findex,:info.model,:info.quantity,:info.price,:info.amount,:info.remark);")
	void insert(@Param("info") WxGroupInfos info);
	

	@Sql("DELETE from t_offer_options WHERE ID = :id")
	public void delete(@Param("id") String id);
	
	@Sql("select * from t_offer_group_option where group_id=:group_id")
	public List<WxGroupInfos> getDefaultGroupInfos(@Param("group_id") String group_id);
}
