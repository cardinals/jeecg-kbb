package com.jeecg.offer.controller;
import com.jeecg.offer.entity.*;
import com.jeecg.offer.service.TOffersServiceI;
import com.jeecg.offer.page.TOffersPage;

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
 * @Description: 报价单
 * @author onlineGenerator
 * @date 2017-07-23 21:36:23
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tOffersController")
public class TOffersController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TOffersController.class);

	@Autowired
	private TOffersServiceI tOffersService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 报价单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/offer/tOffersList");
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
	public void datagrid(TOffersEntity tOffers,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TOffersEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tOffers);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tOffersService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除报价单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TOffersEntity tOffers, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tOffers = systemService.getEntity(TOffersEntity.class, tOffers.getId());
		String message = "报价单删除成功";
		try{
			tOffersService.delMain(tOffers);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "报价单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除报价单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "报价单删除成功";
		try{
			for(String id:ids.split(",")){
				TOffersEntity tOffers = systemService.getEntity(TOffersEntity.class,
				id
				);
				tOffersService.delMain(tOffers);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "报价单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加报价单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TOffersEntity tOffers,TOffersPage tOffersPage, HttpServletRequest request) {
		List<TOffersEntryEntity> tOffersEntryList =  tOffersPage.getTOffersEntryList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			tOffersService.addMain(tOffers, tOffersEntryList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "报价单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新报价单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TOffersEntity tOffers,TOffersPage tOffersPage, HttpServletRequest request) {
		List<TOffersEntryEntity> tOffersEntryList =  tOffersPage.getTOffersEntryList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			tOffersService.updateMain(tOffers, tOffersEntryList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新报价单失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 报价单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TOffersEntity tOffers, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tOffers.getId())) {
			tOffers = tOffersService.getEntity(TOffersEntity.class, tOffers.getId());
			req.setAttribute("tOffersPage", tOffers);
		}
		return new ModelAndView("com/jeecg/offer/tOffers-add");
	}
	
	/**
	 * 报价单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TOffersEntity tOffers, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tOffers.getId())) {
			tOffers = tOffersService.getEntity(TOffersEntity.class, tOffers.getId());
			req.setAttribute("tOffersPage", tOffers);
		}
		return new ModelAndView("com/jeecg/offer/tOffers-update");
	}
	
	
	/**
	 * 加载明细列表[报价单明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "tOffersEntryList")
	public ModelAndView tOffersEntryList(TOffersEntity tOffers, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = tOffers.getId();
		//===================================================================================
		//查询-报价单明细
	    String hql0 = "from TOffersEntryEntity where 1 = 1 AND fOREIGNID = ? ";
	    try{
	    	List<TOffersEntryEntity> tOffersEntryEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("tOffersEntryList", tOffersEntryEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/offer/tOffersEntryList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(TOffersEntity tOffers,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(TOffersEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tOffers);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<TOffersEntity> list=this.tOffersService.getListByCriteriaQuery(cq, false);
    	List<TOffersPage> pageList=new ArrayList<TOffersPage>();
        if(list!=null&&list.size()>0){
        	for(TOffersEntity entity:list){
        		try{
        		TOffersPage page=new TOffersPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from TOffersEntryEntity where 1 = 1 AND fOREIGNID = ? ";
        	        List<TOffersEntryEntity> tOffersEntryEntityList = systemService.findHql(hql0,id0);
            		page.setTOffersEntryList(tOffersEntryEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"报价单");
        map.put(NormalExcelConstants.CLASS,TOffersPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("报价单列表", "导出人:Jeecg",
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
				List<TOffersPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), TOffersPage.class, params);
				TOffersEntity entity1=null;
				for (TOffersPage page : list) {
					entity1=new TOffersEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            tOffersService.addMain(entity1, page.getTOffersEntryList());
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
		map.put(NormalExcelConstants.FILE_NAME,"报价单");
		map.put(NormalExcelConstants.CLASS,TOffersPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("报价单列表", "导出人:"+ ResourceUtil.getSessionUserName().getRealName(),
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
		req.setAttribute("controller_name", "tOffersController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TOffersEntity> list() {
		List<TOffersEntity> listTOfferss=tOffersService.getList(TOffersEntity.class);
		return listTOfferss;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TOffersEntity task = tOffersService.get(TOffersEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TOffersPage tOffersPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TOffersPage>> failures = validator.validate(tOffersPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<TOffersEntryEntity> tOffersEntryList =  tOffersPage.getTOffersEntryList();
		
		TOffersEntity tOffers = new TOffersEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(tOffers,tOffersPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		tOffersService.addMain(tOffers, tOffersEntryList);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = tOffersPage.getId();
		URI uri = uriBuilder.path("/rest/tOffersController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TOffersPage tOffersPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TOffersPage>> failures = validator.validate(tOffersPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		List<TOffersEntryEntity> tOffersEntryList =  tOffersPage.getTOffersEntryList();
		
		TOffersEntity tOffers = new TOffersEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(tOffers,tOffersPage);
		}catch(Exception e){
            logger.info(e.getMessage());
        }
		tOffersService.updateMain(tOffers, tOffersEntryList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		TOffersEntity tOffers = tOffersService.get(TOffersEntity.class, id);
		tOffersService.delMain(tOffers);
	}
}
