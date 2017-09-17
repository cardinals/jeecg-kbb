package org.jeecgframework.web.offer.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.web.base.entity.BaseStandardEntity;
import org.jeecgframework.web.offer.dao.*;
import org.jeecgframework.web.offer.entity.*;

@Controller
@RequestMapping("/wxBase")
public class WxBaseController  extends BaseController{

	@Autowired
	private WxBaseDao wxBaseDao;
	
	
	@RequestMapping(params="getBaseProject",method = RequestMethod.GET)
	@ResponseBody
	public List<WxBaseInfo> getBaseProject(){
		try {
			List<WxBaseInfo> lstBaseInfo=wxBaseDao.getProject();
			return lstBaseInfo;
		} catch (Exception e) {
			e.printStackTrace();						
		}
		return new ArrayList<WxBaseInfo>();
	}
	@RequestMapping(params="getBaseCustomer",method = RequestMethod.GET)
	@ResponseBody
	public List<WxBaseInfo> getBaseCustomer(){
		try {
			List<WxBaseInfo> lstBaseInfo=wxBaseDao.getCustomer();
			return lstBaseInfo;
		} catch (Exception e) {
			e.printStackTrace();						
		}
		return new ArrayList<WxBaseInfo>();
	}
	@RequestMapping(params="getBaseDoors",method = RequestMethod.GET)
	@ResponseBody
	public List<WxBaseInfo> getBaseDoors(HttpServletRequest request){
		try {
			String filter=request.getParameter("doortype");			
			List<WxBaseInfo> lstBaseInfo=wxBaseDao.getDoors(filter);
			return lstBaseInfo;
		} catch (Exception e) {
			e.printStackTrace();						
		}
		return new ArrayList<WxBaseInfo>();
	}
	@RequestMapping(params="getBaseStandard",method = RequestMethod.GET)
	@ResponseBody
	public List<BaseStandardEntity> getBaseStandard(HttpServletRequest request){
		try {			
			String ftype=request.getParameter("standardtype");
			List<BaseStandardEntity> lstBaseInfo=wxBaseDao.getStandards(ftype);
			return lstBaseInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<BaseStandardEntity>();
	}
	@RequestMapping(params="getBaseUnits",method = RequestMethod.GET)
	@ResponseBody
	public List<WxBaseInfo> getBaseUnits(HttpServletRequest request){
		try {				
			List<WxBaseInfo> lstBaseInfo=wxBaseDao.getUnits();
			return lstBaseInfo;
		} catch (Exception e) {
			e.printStackTrace();						
		}
		return new ArrayList<WxBaseInfo>();
	}
	
	
}
