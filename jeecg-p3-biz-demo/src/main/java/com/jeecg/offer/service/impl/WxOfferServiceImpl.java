package com.jeecg.offer.service.impl;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.author.LoginUser;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecg.offer.dao.WxBaseDao;
import com.jeecg.offer.dao.WxOfferDao;
import com.jeecg.offer.entity.WxRevolutionDoor;
import com.jeecg.offer.service.WxOfferService;

@Service("wxOfferService")
public class WxOfferServiceImpl implements WxOfferService {
	
	@Autowired
	private WxBaseDao wxBaseDao;
	/*
	 * 拼出脚本放入WxOfferDao_condition.sql
	 * */
	@Override
	public String getAuthorityFilter(){	
		 LoginUser u = ContextHolderUtils.getLoginSessionUser();
		 List<Map<String,Object>> rs= wxBaseDao.getUserRole(u.getId());
		 String rolecode="";
		 if(rs!=null && rs.size()>0){
			 rolecode=rs.get(0).get("rolecode").toString();
		 }else{
			 return "";
		 }
		 String filter="";
		 switch(rolecode){
		 case "salesperson":
			 filter="'"+ u.getRealName() +"'";
			 break;
		 case "engineer":
			 
			 break;
		 case "areamanager":
			 List<Map<String,Object>> mapList=wxBaseDao.getSubordinateUser(u.getId());
			 Iterator<Map<String,Object>> it = mapList.iterator();	
			
			 while(it.hasNext()) {
				 Map<String,Object> user= it.next();
				 if(StringUtil.isEmpty(filter)){
					 filter="'" + user.get("realname")+"'";
				 }else{
					 filter+=",'" + user.get("realname")+"'";
				 }
			 }			
			 break;
		 }
		 return filter;
	}
}
