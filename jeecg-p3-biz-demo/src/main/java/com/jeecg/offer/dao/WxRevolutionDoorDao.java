package com.jeecg.offer.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;
import com.jeecg.offer.entity.*;

@Repository
public interface WxRevolutionDoorDao {
	/**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */	
	@Sql("select t1.id,t1.item_id,t1.quantity,t1.price,t1.amount,t1.remark,t2.fnumber as item_number,t2.fname as item_name "
			+ "from t_offers_entry t1 inner join t_doors t2 on t1.item_id=t2.id "
			+ "where t1.id=:id")
	List<WxRevolutionDoor> get(@Param("id") String id);

	/**
	 * 修改数据
	 * @param act
	 * @return
	 */
	int update(@Param("door") WxRevolutionDoor door);

	/**
	 * 插入数据
	 * @param act
	 */
	@Sql("INSERT INTO t_offers_entry (id, item_id, quantity, price, amount, remark)"
			+ " VALUES (:door.id, :door.item_id,  :door.quantity,  :door.price,  :door.amount,  :door.remark);")
	void insert(@Param("door") WxRevolutionDoor door);
	

	@Sql("DELETE from t_offers_entry WHERE ID = :id")
	public void delete(@Param("id") String id);
}
