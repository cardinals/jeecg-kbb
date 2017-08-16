package com.jeecg.offer.dao;

import java.util.List;
import java.util.Map;

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
	@Sql("select t1.findex,t1.id,t1.item_id,t1.quantity,t1.price,t1.amount,t1.remark,t2.fnumber as item_number,t2.fname as item_name "
			+ ",t1.wholerate,t1.partsrate,t1.detail2json ,t1.doortype  "
			+ "from t_offers_entry t1 inner join t_doors t2 on t1.item_id=t2.id "
			+ "where t1.id=:id order by t1.findex")
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
	@Sql("INSERT INTO t_offers_entry (id,findex, item_id, quantity, price, amount, remark,wholerate,partsrate,detail2json,doortype)"
			+ " VALUES (:door.id,:door.findex, :door.item_id,  :door.quantity,  :door.price,  :door.amount,  :door.remark,:door.wholerate,:door.partsrate,:door.detail2json,:door.doortype);")
	void insert(@Param("door") WxRevolutionDoor door);
	

	@Sql("DELETE from t_offers_entry WHERE ID = :id")
	void delete(@Param("id") String id);
	
	/**
	 * 获取当前门型的一般参数
	 * @param item_id
	 */
	@Sql("select t2.ffeildname,t2.fcaption from t_door_params t1 inner join t_base_params t2 "
	 +"on t1.fparamsid=t2.id where t1.fshow ='Y'  and t1.foreignid=:item_id")
	List<Map> getDetail2FeildInfo(@Param("item_id") String item_id);
	/**
	 * 获取当前门型的门型信息
	 * @param id
	 */
	@Sql("select * from t_doors_model t1 inner join  t_doors_model_ex t2 on t1.foreignid=t2.foreignid and t1.id=t2.id where t1.foreignid=:item_id  order by t1.findex")
	List<Map> getDetail2ModelInfo(@Param("item_id") String item_id);
}
