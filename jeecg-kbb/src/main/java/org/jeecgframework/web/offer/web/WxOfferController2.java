package org.jeecgframework.web.offer.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.author.LoginUser;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.jeecgframework.p3.core.page.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.web.base.entity.BaseStandardEntity;
import org.jeecgframework.web.offer.dao.*;
import org.jeecgframework.web.offer.entity.*;
import org.jeecgframework.web.offer.page.WxOfferMainPage;
import org.jeecgframework.web.offer.service.*;



 /**
 * 描述：</b>BackController<br>系统欢迎页
 * @author Alex
 * @since：2015年12月23日 12时04分42秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/wxOffer2")
public class WxOfferController2 extends BaseController{
	private static final Logger logger = Logger.getLogger(WxOfferController2.class);
	
	@Autowired
	private WxOfferDao wxOfferDao;
	@Autowired
	private WxGroupInfosDao wxGroupInfosDao;
	@Autowired
	private WxRevolutionDoorDao wxRevolutionDoorDao;
	@Autowired
	private WxBillNoRuleDao wxBillNoRuleDao;
	@Autowired
	private WxOffer2Service wxOffer2Service;
	
	@Autowired
	private WxOfferService wxOfferService;
	@RequestMapping(params = "datagrid")
	public void datagrid(BaseStandardEntity jeecgDemo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String filterkey=request.getParameter("filterkey");
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute WxOffer query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		 try {
			 MiniDaoPage<WxOffer> list =  wxOfferService.getAll(query, pageNo, pageSize);
			 List<WxOffer> result=list.getResults();
			 String realName=ContextHolderUtils.getLoginSessionUser().getRealName();
			 result.stream().forEach(o->{
				 if(o.getFapplicant().equals(realName)){
					 o.setFisself(true);
				 }
			 });
			 
			 VelocityContext velocityContext = new VelocityContext();
			 velocityContext.put("operationRight",wxOfferService.getOperationRight());
			 velocityContext.put("wxOffer",query);
			 velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
			 String viewName = "offer/wxOffer-list.vm";
			 
			 ViewVelocity.view(request,response,viewName,velocityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toAdd(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setAttribute("backUrl", "wxOffer.do?list");
		request.setAttribute("action", "add");
		return new ModelAndView("jeecg/offer-v2/offer_add");
	}
	
	
	public static String formateDate(String strFormat) {
		 Date currentTime = new Date();
		   SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
		   return formatter.format(currentTime);
		}
	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute WxOffer wxOffer,@ModelAttribute WxOfferMainPage offerMainPage){
		AjaxJson j = new AjaxJson();
		String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		try {
	
			wxOfferDao.insert(wxOffer);
			j.setMsg("保存成功");
		} catch (Exception e) {
			wxGroupInfosDao.delete(id);
			wxRevolutionDoorDao.delete(id);
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		return j;
	}
	
	/**********************华丽分割线**************************/
	@RequestMapping(params="getEntryList",method = RequestMethod.GET)
	@ResponseBody
	public List<WxOffer2Entry> getEntryList(HttpServletRequest request){
		try {
			String offerId=request.getParameter("offerId");			
			String type=request.getParameter("type");			
			List<WxOffer2Entry> lstBaseInfo=wxOffer2Service.getOffer2Entry(offerId, type);
			return lstBaseInfo;
		} catch (Exception e) {
			e.printStackTrace();						
		}
		return new ArrayList<WxOffer2Entry>();
	}
	@RequestMapping(params="getBillNo",method = RequestMethod.GET)
	@ResponseBody
	public String getBillNo(){
		WxBillNoRule wxBillNoRule=wxBillNoRuleDao.get("offer");
		String strRnt="";
		if(wxBillNoRule.getFrule()!=null){
			String strRule=wxBillNoRule.getFrule();
			if(strRule.indexOf("[")>-1 && strRule.indexOf("]")>-1){
				String strFormat=strRule.substring(strRule.indexOf("[")+1, strRule.indexOf("]"));
				strRnt=strRule.replace("["+strFormat+"]", formateDate(strFormat));
			}
			String strNum=wxBillNoRule.getFnum().toString();
			strRnt=strRnt.substring(0, strRnt.length()-strNum.length())+strNum;
		}else{
			strRnt=wxBillNoRule.getFnum().toString();
		}
		wxBillNoRuleDao.update("offer");
		return strRnt;
	}
	
}

