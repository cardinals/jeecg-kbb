package org.jeecgframework.web.base.controller;

import java.util.HashMap;
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
import org.jeecgframework.web.base.entity.BaseSurfaceEntity;
import org.jeecgframework.web.base.entity.BaseSurfacePage;
import org.jeecgframework.web.base.service.BaseSurfaceServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/baseSurfaceController")
public class BaseSurfaceController extends BaseController {
	@Autowired
	private BaseSurfaceServiceI baseSurfaceService;
	@Autowired
	private SystemService systemService;
	/**
	 * 行编辑列表
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("jeecg/base/base_surface");
	}
	
	@RequestMapping(params = "datagrid")
	public void datagrid(BaseSurfaceEntity jeecgDemo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaseSurfaceEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jeecgDemo, request.getParameterMap());
		
		cq.add();
		this.baseSurfaceService.getDataGridReturn(cq, true);
	
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 保存新增/更新的行数据
	 * @param page
	 * @return
	 */
	@RequestMapping(params = "saveRows")
	@ResponseBody
	public AjaxJson saveRows(BaseSurfacePage page){
		String message = null;
		List<BaseSurfaceEntity> demos=page.getDemos();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(demos)){
			for(BaseSurfaceEntity jeecgDemo:demos){
				if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
					BaseSurfaceEntity t =baseSurfaceService.get(BaseSurfaceEntity.class, jeecgDemo.getId());
					try {
					
						MyBeanUtils.copyBeanNotNull2Bean(jeecgDemo, t);
						baseSurfaceService.saveOrUpdate(t);
//						systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
					
						baseSurfaceService.save(jeecgDemo);
//						systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
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
				BaseSurfaceEntity jeecgDemo = systemService.getEntity(BaseSurfaceEntity.class, 
				id
				);
				baseSurfaceService.delete(jeecgDemo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
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
