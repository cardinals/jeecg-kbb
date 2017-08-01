package org.jeecgframework.web.cgform.controller.door;
import org.jeecgframework.web.cgform.entity.door.TDoorOptionsEntity;
import org.jeecgframework.web.cgform.entity.door.TDoorStandardEntity;
import org.jeecgframework.web.cgform.entity.door.TDoorSurfaceEntity;
import org.jeecgframework.web.cgform.entity.door.TDoorsEntity;
import org.jeecgframework.web.cgform.entity.door.TDoorsModelEntity;
import org.jeecgframework.web.cgform.entity.door.TDoorsPage;
import org.jeecgframework.web.cgform.service.door.TDoorsServiceI;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 门型维护
 * @author onlineGenerator
 * @date 2017-08-01 18:23:31
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tDoorsController")
public class TDoorsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TDoorsController.class);

	@Autowired
	private TDoorsServiceI tDoorsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 门型维护列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("jeecg/cgform/door/tDoorsList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TDoorsEntity tDoors,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TDoorsEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tDoors);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tDoorsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除门型维护
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TDoorsEntity tDoors, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tDoors = systemService.getEntity(TDoorsEntity.class, tDoors.getId());
		String message = "门型维护删除成功";
		try{
			tDoorsService.delMain(tDoors);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "门型维护删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除门型维护
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "门型维护删除成功";
		try{
			for(String id:ids.split(",")){
				TDoorsEntity tDoors = systemService.getEntity(TDoorsEntity.class,
				id
				);
				tDoorsService.delMain(tDoors);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "门型维护删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加门型维护
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TDoorsEntity tDoors,TDoorsPage tDoorsPage, HttpServletRequest request) {
		List<TDoorsModelEntity> tDoorsModelList =  tDoorsPage.getTDoorsModelList();
		List<TDoorStandardEntity> tDoorStandardList =  tDoorsPage.getTDoorStandardList();
		List<TDoorSurfaceEntity> tDoorSurfaceList =  tDoorsPage.getTDoorSurfaceList();
		List<TDoorOptionsEntity> tDoorOptionsList =  tDoorsPage.getTDoorOptionsList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			tDoorsService.addMain(tDoors, tDoorsModelList,tDoorStandardList,tDoorSurfaceList,tDoorOptionsList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "门型维护添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新门型维护
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TDoorsEntity tDoors,TDoorsPage tDoorsPage, HttpServletRequest request) {
		List<TDoorsModelEntity> tDoorsModelList =  tDoorsPage.getTDoorsModelList();
		List<TDoorStandardEntity> tDoorStandardList =  tDoorsPage.getTDoorStandardList();
		List<TDoorSurfaceEntity> tDoorSurfaceList =  tDoorsPage.getTDoorSurfaceList();
		List<TDoorOptionsEntity> tDoorOptionsList =  tDoorsPage.getTDoorOptionsList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			tDoorsService.updateMain(tDoors, tDoorsModelList,tDoorStandardList,tDoorSurfaceList,tDoorOptionsList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新门型维护失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 门型维护新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TDoorsEntity tDoors, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tDoors.getId())) {
			tDoors = tDoorsService.getEntity(TDoorsEntity.class, tDoors.getId());
			req.setAttribute("tDoorsPage", tDoors);
		}
		return new ModelAndView("jeecg/cgform/door/tDoors-add");
	}
	
	/**
	 * 门型维护编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TDoorsEntity tDoors, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tDoors.getId())) {
			tDoors = tDoorsService.getEntity(TDoorsEntity.class, tDoors.getId());
			req.setAttribute("tDoorsPage", tDoors);
		}
		return new ModelAndView("jeecg/cgform/door/tDoors-update");
	}
	
	
	/**
	 * 加载明细列表[型号]
	 * 
	 * @return
	 */
	@RequestMapping(params = "tDoorsModelList")
	public ModelAndView tDoorsModelList(TDoorsEntity tDoors, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = tDoors.getId();
		//===================================================================================
		//查询-型号
	    String hql0 = "from TDoorsModelEntity where 1 = 1 AND fOREIGNID = ? ";
	    try{
	    	List<TDoorsModelEntity> tDoorsModelEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("tDoorsModelList", tDoorsModelEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("jeecg/cgform/door/tDoorsModelList");
	}
	/**
	 * 加载明细列表[标准配件]
	 * 
	 * @return
	 */
	@RequestMapping(params = "tDoorStandardList")
	public ModelAndView tDoorStandardList(TDoorsEntity tDoors, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id1 = tDoors.getId();
		//===================================================================================
		//查询-标准配件
	    String hql1 = "from TDoorStandardEntity where 1 = 1 AND fOREIGNID = ? ";
	    try{
	    	List<TDoorStandardEntity> tDoorStandardEntityList = systemService.findHql(hql1,id1);
			req.setAttribute("tDoorStandardList", tDoorStandardEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("jeecg/cgform/door/tDoorStandardList");
	}
	/**
	 * 加载明细列表[表面处理]
	 * 
	 * @return
	 */
	@RequestMapping(params = "tDoorSurfaceList")
	public ModelAndView tDoorSurfaceList(TDoorsEntity tDoors, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id2 = tDoors.getId();
		//===================================================================================
		//查询-表面处理
	    String hql2 = "from TDoorSurfaceEntity where 1 = 1 AND fOREIGNID = ? ";
	    try{
	    	List<TDoorSurfaceEntity> tDoorSurfaceEntityList = systemService.findHql(hql2,id2);
			req.setAttribute("tDoorSurfaceList", tDoorSurfaceEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("jeecg/cgform/door/tDoorSurfaceList");
	}
	/**
	 * 加载明细列表[可选配件]
	 * 
	 * @return
	 */
	@RequestMapping(params = "tDoorOptionsList")
	public ModelAndView tDoorOptionsList(TDoorsEntity tDoors, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id3 = tDoors.getId();
		//===================================================================================
		//查询-可选配件
	    String hql3 = "from TDoorOptionsEntity where 1 = 1 AND fOREIGNID = ? ";
	    try{
	    	List<TDoorOptionsEntity> tDoorOptionsEntityList = systemService.findHql(hql3,id3);
			req.setAttribute("tDoorOptionsList", tDoorOptionsEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("jeecg/cgform/door/tDoorOptionsList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(TDoorsEntity tDoors,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(TDoorsEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tDoors);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<TDoorsEntity> list=this.tDoorsService.getListByCriteriaQuery(cq, false);
    	List<TDoorsPage> pageList=new ArrayList<TDoorsPage>();
        if(list!=null&&list.size()>0){
        	for(TDoorsEntity entity:list){
        		try{
        		TDoorsPage page=new TDoorsPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from TDoorsModelEntity where 1 = 1 AND fOREIGNID = ? ";
        	        List<TDoorsModelEntity> tDoorsModelEntityList = systemService.findHql(hql0,id0);
            		page.setTDoorsModelList(tDoorsModelEntityList);
            	    Object id1 = entity.getId();
				    String hql1 = "from TDoorStandardEntity where 1 = 1 AND fOREIGNID = ? ";
        	        List<TDoorStandardEntity> tDoorStandardEntityList = systemService.findHql(hql1,id1);
            		page.setTDoorStandardList(tDoorStandardEntityList);
            	    Object id2 = entity.getId();
				    String hql2 = "from TDoorSurfaceEntity where 1 = 1 AND fOREIGNID = ? ";
        	        List<TDoorSurfaceEntity> tDoorSurfaceEntityList = systemService.findHql(hql2,id2);
            		page.setTDoorSurfaceList(tDoorSurfaceEntityList);
            	    Object id3 = entity.getId();
				    String hql3 = "from TDoorOptionsEntity where 1 = 1 AND fOREIGNID = ? ";
        	        List<TDoorOptionsEntity> tDoorOptionsEntityList = systemService.findHql(hql3,id3);
            		page.setTDoorOptionsList(tDoorOptionsEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"门型维护");
        map.put(NormalExcelConstants.CLASS,TDoorsPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("门型维护列表", "导出人:Jeecg",
            "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * 通过excel导入数据
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<TDoorsPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), TDoorsPage.class, params);
				TDoorsEntity entity1=null;
				for (TDoorsPage page : list) {
					entity1=new TDoorsEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            tDoorsService.addMain(entity1, page.getTDoorsModelList(),page.getTDoorStandardList(),page.getTDoorSurfaceList(),page.getTDoorOptionsList());
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}
			return j;
	}
	/**
	* 导出excel 使模板
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"门型维护");
		map.put(NormalExcelConstants.CLASS,TDoorsPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("门型维护列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
		"导出信息"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* 导入功能跳转
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "tDoorsController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TDoorsEntity> list() {
		List<TDoorsEntity> listTDoorss=tDoorsService.getList(TDoorsEntity.class);
		return listTDoorss;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TDoorsEntity task = tDoorsService.get(TDoorsEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TDoorsPage tDoorsPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TDoorsPage>> failures = validator.validate(tDoorsPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<TDoorsModelEntity> tDoorsModelList =  tDoorsPage.getTDoorsModelList();
		List<TDoorStandardEntity> tDoorStandardList =  tDoorsPage.getTDoorStandardList();
		List<TDoorSurfaceEntity> tDoorSurfaceList =  tDoorsPage.getTDoorSurfaceList();
		List<TDoorOptionsEntity> tDoorOptionsList =  tDoorsPage.getTDoorOptionsList();
		
		TDoorsEntity tDoors = new TDoorsEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(tDoors,tDoorsPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		tDoorsService.addMain(tDoors, tDoorsModelList,tDoorStandardList,tDoorSurfaceList,tDoorOptionsList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = tDoorsPage.getId();
		URI uri = uriBuilder.path("/rest/tDoorsController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TDoorsPage tDoorsPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TDoorsPage>> failures = validator.validate(tDoorsPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<TDoorsModelEntity> tDoorsModelList =  tDoorsPage.getTDoorsModelList();
		List<TDoorStandardEntity> tDoorStandardList =  tDoorsPage.getTDoorStandardList();
		List<TDoorSurfaceEntity> tDoorSurfaceList =  tDoorsPage.getTDoorSurfaceList();
		List<TDoorOptionsEntity> tDoorOptionsList =  tDoorsPage.getTDoorOptionsList();
		
		TDoorsEntity tDoors = new TDoorsEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(tDoors,tDoorsPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		tDoorsService.updateMain(tDoors, tDoorsModelList,tDoorStandardList,tDoorSurfaceList,tDoorOptionsList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		TDoorsEntity tDoors = tDoorsService.get(TDoorsEntity.class, id);
		tDoorsService.delMain(tDoors);
	}
}
