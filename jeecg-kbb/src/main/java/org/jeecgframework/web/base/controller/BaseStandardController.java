package org.jeecgframework.web.base.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.base.entity.BaseStandardEntity;
import org.jeecgframework.web.base.entity.BaseStandardPage;
import org.jeecgframework.web.base.service.BaseStandardServiceI;
import org.jeecgframework.web.offer.entity.WxRevolutionDoor;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/baseStandardController")
public class BaseStandardController extends BaseController {
	@Autowired
	private BaseStandardServiceI baseService;
	@Autowired
	private SystemService systemService;
	

	
	/**
	 * 行编辑列表
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("jeecg/base/base_standard");
	}
	
	@RequestMapping(params = "datagrid")
	public void datagrid(BaseStandardEntity jeecgDemo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaseStandardEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jeecgDemo, request.getParameterMap());
		
		cq.add();
		this.baseService.getDataGridReturn(cq, true);
		Map<String,String> typeMap=getStandardType();
		
		List<BaseStandardEntity> list=dataGrid.getResults();
		Iterator<BaseStandardEntity> it = list.iterator();		
		 while(it.hasNext()) {
			 BaseStandardEntity entity= it.next();
			 entity.setFtype(typeMap.get(entity.getFtype()));
		 }
		TagUtil.datagrid(response, dataGrid);
	}
	
	Map<String,String> getStandardType(){
		List<Map<String,Object>> list=baseService.getStandardType();
		Map<String,String> result=new HashMap<String,String>();
		Iterator<Map<String,Object>> it = list.iterator();		
		 while(it.hasNext()) {
			 Map<String,Object> map= it.next();
			result.put(map.get("value").toString(), map.get("text").toString());
		 }
		 return result;
	}
	
	@RequestMapping(params = "getstandardtype")
	@ResponseBody
	public List<Map<String,Object>> getstandardtype(){
		return baseService.getStandardType();
	}
	
	/**
	 * 保存新增/更新的行数据
	 * @param page
	 * @return
	 */
	@RequestMapping(params = "saveRows")
	@ResponseBody
	public AjaxJson saveRows(BaseStandardPage page){
		String message = null;
		List<BaseStandardEntity> demos=page.getDemos();
		AjaxJson j = new AjaxJson();
		try {
			if(CollectionUtils.isNotEmpty(demos)){
				for(BaseStandardEntity jeecgDemo:demos){
					if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
						BaseStandardEntity t =baseService.get(BaseStandardEntity.class, jeecgDemo.getId());
						
						
							MyBeanUtils.copyBeanNotNull2Bean(jeecgDemo, t);
							baseService.saveOrUpdate(t);
//							systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
						
					} else {
							baseService.save(jeecgDemo);
//							systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 批量删除jeecg_demo
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "删除成功";
		try{
			for(String id:ids.split(",")){
				BaseStandardEntity jeecgDemo = systemService.getEntity(BaseStandardEntity.class, 
				id
				);
				baseService.delete(jeecgDemo);
//				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
}
