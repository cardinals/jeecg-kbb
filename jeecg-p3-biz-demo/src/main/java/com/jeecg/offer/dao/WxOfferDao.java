package com.jeecg.offer.dao;

import java.util.List;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.offer.entity.WxGroupInfos;
import com.jeecg.offer.entity.WxOffer;


@Repository
public interface WxOfferDao {

	/**
	 * 查询返回Java对象
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM t_offers WHERE ID = :id")
	WxOffer get(@Param("id") String id);


	/**
	 * 修改数据
	 * @param act
	 * @return
	 */
	int update(@Param("act") WxOffer act);

	/**
	 * 插入数据
	 * @param act
	 */
	void insert(@Param("act") WxOffer act);
	
	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * @param act
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(WxOffer.class)
	public MiniDaoPage<WxOffer> getAll(@Param("act") WxOffer act,@Param("page")  int page,@Param("rows") int rows);
	
	@Sql("DELETE from t_offers WHERE ID = :act.id")
	public void delete(@Param("act") WxOffer act);
	
	@Sql("SELECT * FROM t_offer_group_option where group_id=:group_id")
	public List<WxGroupInfos> getGroupInfos(@Param("group_id") String group_id);
}
