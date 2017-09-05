package org.jeecgframework.web.offer.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.web.offer.dao.*;
import org.jeecgframework.web.offer.entity.WxOffer;
import org.jeecgframework.web.offer.service.*;
import org.jeecgframework.web.system.pojo.base.TSUser;

@Service("wxOfferService")
public class WxOfferServiceImpl extends CommonServiceImpl implements WxOfferService {
	@Autowired
	private WxOfferDao wxOfferDao;
	@Autowired
	private WxBaseDao wxBaseDao;
	
	
	@Override
	@ResultType(WxOffer.class)
	public MiniDaoPage<WxOffer> getAll(WxOffer act,  int page,int rows){
		 TSUser u = ResourceUtil.getSessionUserName();
		 List<Map<String,Object>> rs= wxBaseDao.getUserRole(u.getId());
		 String rolecode="";
		 if(rs!=null && rs.size()>0){
			 rolecode=rs.get(0).get("rolecode").toString();
		 }else{
			 return wxOfferDao.getAll(act, page, rows);
		 }		
		 switch(rolecode){
		 case "salesperson":
			 act.setFapplicant(u.getRealName());
			 return wxOfferDao.getAll(act, page, rows);
		 case "engineer":
			 
			 break;
		 case "areamanager":
			 List<Map<String,Object>> mapList=wxBaseDao.getSubordinateUser(u.getId());
			 Iterator<Map<String,Object>> it = mapList.iterator();
			 String filter="";
			 while(it.hasNext()) {
				 Map<String,Object> user= it.next();
				 if(StringUtil.isEmpty(filter)){
					 filter="N'" + user.get("realname")+"'";
				 }else{
					 filter+=",N'" + user.get("realname")+"'";
				 }
			 }		
			 String sql=buildSQL(act,filter);
			 List<WxOffer> list=this.findObjForJdbc(sql, page, rows, WxOffer.class);
			 MiniDaoPage<WxOffer> result=new MiniDaoPage<WxOffer>();
			 result.setPage(page);
			 result.setRows(rows);
			 result.setTotal(list.size());
			 result.setPages((int)Math.ceil(list.size()/rows));
			 result.setResults(list);
			 return result;
		 }
		 return wxOfferDao.getAll(act, page, rows);
	}
	String buildSQL(WxOffer act,String filter){
		 StringBuilder sb=new StringBuilder();
		 sb.append(" SELECT t0.id,t0.fbillno,t0.fcustid,t0.fprojectid,t2.fname as fcust_name, ");
		 sb.append(" t0.fremark,t0.famount,t0.fstatus,t0.fapplicant,t0.fapplicant_date,t0.fcurrent_approver ,"
		 		+ "t0.fdiscountrate,t0.fafteramount ,t0.fisoutsource,t0.fouterprice ");
		sb.append(" FROM t_offers t0  ");
		sb.append(" inner join t_base_customer t2 on t0.fcustid=t2.id ");
		sb.append(" where 1=1 ");
		buildWhere(sb,"t0.fbillno",act.getFbillno());
		buildWhere(sb,"t2.fname",act.getFcust_name());
		buildWhere(sb,"t0.fprojectid",act.getFprojectid());
		buildWhere(sb,"t0.fremark",act.getFremark());
		buildWhere(sb,"t0.fstatus",act.getFstatus());
		if(act.getFapplicant_date_begin()!=null){
			sb.append(" and t0.fapplicant_date >= '"+ formateDate(act.getFapplicant_date_begin()) +"'");
		}
		if(act.getFapplicant_date_end()!=null){
			sb.append(" and t0.fapplicant_date >= '"+ formateDate(act.getFapplicant_date_end()) +"'");
		}
		if(StringUtil.isNotEmpty(filter)){
			sb.append(" and t0.fapplicant in("+ filter +")");
		}
		
		sb.append(" order by fbillno desc ");
		return sb.toString();
	}

	void buildWhere(StringBuilder sb,String fieldname,String fieldvalue){
		if(StringUtil.isNotEmpty(fieldvalue) && fieldvalue!="empty"){
			sb.append(" and "+fieldname + "like '%"+ fieldvalue + "%'");
		}
	}
	
	public static String formateDate(Date date) {
		String strFormat="yyyy-MM-dd";
	   SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
	   return formatter.format(date);
	}
	/**
	 * 返回true表示有权限
	 * */
	@Override
	public boolean isEnableViewProject(){
		//1、获得本人的roleId
		String roleId=getSqlOneValue("select roleid  from t_s_role_user where userid=? ",ResourceUtil.getSessionUserName().getId());
		//2、获得functionid
		String functionId=getSqlOneValue("select id from t_s_function where functionname='menu.offer' ");
		//3、获得datarule
		String datarule=getSqlOneValue("select id from t_s_data_rule where rule_column='enableviewproject' ");
		
		String datarule2=getSqlOneValue("select datarule from t_s_role_function where functionid=? and roleid=?",functionId,roleId);
		
		return datarule2.indexOf(datarule)!=-1;
	} 
	
	String getSqlOneValue(String sql,Object...parmas){
		List<Map<String,Object>> rs=this.commonDao.findForJdbc(sql, parmas);
		if(rs.size()>0){
			Map<String, Object> dr=rs.get(0);
			Iterator<Map.Entry<String,Object>> v=dr.entrySet().iterator();
			if(v.hasNext()){
				Map.Entry<String, Object> entity=v.next();
				if(entity.getValue()==null){
					return "";
				}
				return entity.getValue().toString();
			}else{
				return "";
			}
		}else{
			return "";
		}
	}
	
}
