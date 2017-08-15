package com.jeecg.offer.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.offer.dao.WxBaseDao;
import com.jeecg.offer.entity.WxBaseInfo;

@Controller
@RequestMapping("/p3/wxBase")
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
			String doorType=request.getParameter("doortype");
			String filter="XZM";
			if(doorType.equals("smoothDoor")){
				filter="PHM";
			}
			List<WxBaseInfo> lstBaseInfo=wxBaseDao.getDoors(filter);
			return lstBaseInfo;
		} catch (Exception e) {
			e.printStackTrace();						
		}
		return new ArrayList<WxBaseInfo>();
	}
	
}
