package com.jeecg.offer.web;

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
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.author.LoginUser;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.jeecgframework.p3.core.page.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.offer.dao.*;
import com.jeecg.offer.entity.WxAttachment;
import com.jeecg.offer.entity.WxBillNoRule;
import com.jeecg.offer.entity.WxGroupInfos;
import com.jeecg.offer.entity.WxOffer;
import com.jeecg.offer.entity.WxRevolutionDoor;
import com.jeecg.offer.entity.WxVmParams;
import com.jeecg.offer.page.WxOfferMainPage;



 /**
 * 描述：</b>BackController<br>系统欢迎页
 * @author Alex
 * @since：2015年12月23日 12时04分42秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/p3/wxOffer")
public class WxOfferController extends BaseController{
	private static final Logger logger = Logger.getLogger(WxOfferController.class);
	
	@Autowired
	private WxOfferDao wxOfferDao;
	@Autowired
	private WxGroupInfosDao wxGroupInfosDao;
	@Autowired
	private WxRevolutionDoorDao wxRevolutionDoorDao;
	@Autowired
	private WxBillNoRuleDao wxBillNoRuleDao;
	
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute WxOffer query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		 try {
			 LOG.info(request, " back list");
			 	//分页数据
			 MiniDaoPage<WxOffer> list =  wxOfferDao.getAll(query,pageNo,pageSize);
			 VelocityContext velocityContext = new VelocityContext();
			 velocityContext.put("wxOffer",query);
			 velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
			 String viewName = "offer/wxOffer-list.vm";
			 
			 LoginUser u = ContextHolderUtils.getLoginSessionUser();
			 logger.info(" -- test -- "+ u.getRealName());
			 
			 
			 ViewVelocity.view(request,response,viewName,velocityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 /**
	  * 详情
	  * @return
	  */
	@RequestMapping(params="toDetail",method = RequestMethod.GET)
	public void toDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "offer/wxOffer-detail.vm";
			WxOffer wxOffer = wxOfferDao.get(id);
			List<WxGroupInfos> defaultGroupInfos=wxGroupInfosDao.getDefaultGroupInfos();
			List<WxGroupInfos> groupInfos=wxGroupInfosDao.get(id);
			velocityContext.put("wxOffer",wxOffer);
//			 velocityContext.put("groupInfo2s", getGroupInfo(this.findGroupInfo(defaultGroupInfos, 2),this.findGroupInfo(groupInfos, 2)));
			 velocityContext.put("groupInfo3s", getGroupInfo(this.findGroupInfo(defaultGroupInfos, 3),this.findGroupInfo(groupInfos, 3)));
			 velocityContext.put("groupInfo4s", getGroupInfo(this.findGroupInfo(defaultGroupInfos, 4),this.findGroupInfo(groupInfos, 4)));
			 velocityContext.put("groupInfo5s", getGroupInfo(this.findGroupInfo(defaultGroupInfos, 5),this.findGroupInfo(groupInfos, 5)));			 
			 List<WxRevolutionDoor> listDoors=wxRevolutionDoorDao.get(id);
			 List<WxRevolutionDoor> revolutionDoor=new ArrayList<WxRevolutionDoor>();
			 List<WxRevolutionDoor> smoothDoor=new ArrayList<WxRevolutionDoor>();
			 getSplitDoors(listDoors,revolutionDoor,smoothDoor);
			 velocityContext.put("revolutionDoor", revolutionDoor);	
			 velocityContext.put("smoothDoor", smoothDoor);
			 List<WxAttachment> attachmentList=wxOfferDao.getAttachementList(id);
			 velocityContext.put("attachmentList", attachmentList);
			 String backUrl="p3/wxOffer.do?list";
			 String reqBackUrl=request.getParameter("backUrl");
			 if(reqBackUrl!=null){
				 if(reqBackUrl.equals("myTaskList")){
					 backUrl="activitiOffer.do?myTaskList";
				 }
				 String roleCode=request.getParameter("roleCode");
				 if(roleCode!=null && roleCode.equals("engineer")){
					 velocityContext.put("toEdit", true);
				 }
			 }
			 velocityContext.put("backUrl", backUrl);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}
	
	void getSplitDoors(List<WxRevolutionDoor> source,List<WxRevolutionDoor> revolution,List<WxRevolutionDoor> smooth){
		Iterator<WxRevolutionDoor> it = source.iterator();		
		 while(it.hasNext()) {
			 WxRevolutionDoor door= it.next();
			 if(door.getDoortype().equals("XZM")){
				 revolution.add(door);
			 }else{
				 smooth.add(door);
			 }
		 }
	}
	
	private List<WxGroupInfos> getGroupInfo(List<WxGroupInfos> defaultGroupInfos ,List<WxGroupInfos> source){
		List<WxGroupInfos> lst=new ArrayList<WxGroupInfos>();
		if(source.size()>0){
			for(WxGroupInfos info:defaultGroupInfos){
				for(WxGroupInfos data:source){
					if(info.getFindex().equals(data.getFindex()))
					{
						info.setModel(data.getModel());
						info.setPrice(data.getPrice());
						info.setQuantity(data.getQuantity());
						info.setAmount(data.getAmount());
						info.setRemark(data.getRemark());
						break;
					}
				}
			}
		}
		return defaultGroupInfos;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();	
		 List<WxGroupInfos> groupInfos=wxGroupInfosDao.getDefaultGroupInfos();
		 velocityContext.put("groupInfo3s", findGroupInfo(groupInfos,3));
		 velocityContext.put("groupInfo4s", findGroupInfo(groupInfos,4));
		 velocityContext.put("groupInfo5s", findGroupInfo(groupInfos,5));
		 WxOffer wxOffer=new WxOffer(); 
		 wxOffer.setFbillno(getBillNo("offer"));	
		 velocityContext.put("wxOffer", wxOffer);
		 String viewName = "offer/wxOffer-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}
	
	private List<WxGroupInfos> findGroupInfo(List<WxGroupInfos> source ,final Integer groupId){
		List<WxGroupInfos> lst=new ArrayList<WxGroupInfos>();
		for(WxGroupInfos info:source){
			if(info.getGroup_id().equals(groupId))
			{
				lst.add(info);
			}
		}
		return lst;
	}
	
	public String getBillNo(String tableName){
		WxBillNoRule wxBillNoRule=wxBillNoRuleDao.get(tableName);
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
		wxBillNoRuleDao.update(tableName);
		return strRnt;
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
	
			List<WxGroupInfos> groupInfo3s = offerMainPage.getGroupInfo3s();
			List<WxGroupInfos> groupInfo4s = offerMainPage.getGroupInfo4s();
			List<WxGroupInfos> groupInfo5s = offerMainPage.getGroupInfo5s();
			List<WxRevolutionDoor> revolutionDoor = offerMainPage.getRevolutionDoor();
			List<WxRevolutionDoor> smoothDoor = offerMainPage.getSmoothDoor();
			List<WxAttachment> attachment=offerMainPage.getAttachment();
			
			saveGroupInfos(id,3,groupInfo3s);
			saveGroupInfos(id,4,groupInfo4s);
			saveGroupInfos(id,5,groupInfo5s);
			saveWxRevolutionDoor(id,revolutionDoor,"XZM");
			saveWxRevolutionDoor(id,smoothDoor,"PHM");	
			saveWxAttachment(id,attachment);
			
			LoginUser u = ContextHolderUtils.getLoginSessionUser();
			wxOffer.setFapplicant(u.getRealName());
			wxOffer.setId(id);
			wxOffer.setFapplicant_date(new Date());
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
	private void saveWxAttachment(String id,List<WxAttachment> attachmentList){
		wxOfferDao.deleteAttachement(id);
		for (WxAttachment attachment : attachmentList) {
			if(!StringUtils.isEmpty(attachment.getFileid())){
				attachment.setId(id);
				wxOfferDao.insertAttachement(attachment);
			}			
		}
	}
	
	@RequestMapping(params = "delAttachment",method ={ RequestMethod.POST})
	@ResponseBody
	public AjaxJson delAttachment(@ModelAttribute String id,@ModelAttribute String fileid){
		AjaxJson j = new AjaxJson();		
		try {
			wxOfferDao.deleteAttachement(id,fileid);
			j.setSuccess(true);
		} catch (Exception e) {			
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	private void saveWxRevolutionDoor(String id,List<WxRevolutionDoor> doorList,String doorType){
		for (WxRevolutionDoor door : doorList) {
			if(door.getQuantity()!=null)
			{
				door.setPrice(isNull(door.getPrice(),0.00));
				door.setAmount(isNull(door.getAmount(),0.00));
				door.setId(id);	
				door.setDoortype(doorType);
				wxRevolutionDoorDao.insert(door);
			}
		}
	}
	
	private void saveGroupInfos(String id,Integer group_id,List<WxGroupInfos> wxGroupInfos){		
		for (WxGroupInfos info : wxGroupInfos) {
			if(group_id.equals(5) && info.getAmount()!=null){
				info.setAmount(isNull(info.getAmount(),0.00));
				info.setPrice(isNull(info.getPrice(),0.00));
				info.setQuantity(isNull(info.getQuantity(),0.00));
				info.setId(id);
				info.setGroup_id(group_id);
				wxGroupInfosDao.insert(info);
			}
			else if(info.getQuantity()!=null){
				info.setAmount(isNull(info.getAmount(),0.00));
				info.setPrice(isNull(info.getPrice(),0.00));
				info.setId(id);
				info.setGroup_id(group_id);
				wxGroupInfosDao.insert(info);
			}
		}
	}
	
	private Double isNull(Double val,Double defaultVal){
		if(val==null)
			return defaultVal;
		else
			return val;
	}
	
	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	@RequestMapping(params="toEdit",method = RequestMethod.GET)
	public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
			 VelocityContext velocityContext = new VelocityContext();
			 WxOffer wxOffer = wxOfferDao.get(id);		
			 String viewName = "offer/wxOffer-edit.vm";
				List<WxGroupInfos> defaultGroupInfos=wxGroupInfosDao.getDefaultGroupInfos();
				List<WxGroupInfos> groupInfos=wxGroupInfosDao.get(id);
				velocityContext.put("wxOffer",wxOffer);
//				 velocityContext.put("groupInfo2s", getGroupInfo(this.findGroupInfo(defaultGroupInfos, 2),this.findGroupInfo(groupInfos, 2)));
				 velocityContext.put("groupInfo3s", getGroupInfo(this.findGroupInfo(defaultGroupInfos, 3),this.findGroupInfo(groupInfos, 3)));
				 velocityContext.put("groupInfo4s", getGroupInfo(this.findGroupInfo(defaultGroupInfos, 4),this.findGroupInfo(groupInfos, 4)));
				 velocityContext.put("groupInfo5s", getGroupInfo(this.findGroupInfo(defaultGroupInfos, 5),this.findGroupInfo(groupInfos, 5)));
				 List<WxRevolutionDoor> listDoors=wxRevolutionDoorDao.get(id);
				 List<WxRevolutionDoor> revolutionDoor=new ArrayList<WxRevolutionDoor>();
				 List<WxRevolutionDoor> smoothDoor=new ArrayList<WxRevolutionDoor>();
				 getSplitDoors(listDoors,revolutionDoor,smoothDoor);
				 String backUrl=request.getParameter("backUrl");
				 if(StringUtil.notEmptyNull(backUrl) && backUrl.equals("myTaskList")){					 
					 velocityContext.put("backUrl", "activitiOffer.do?myTaskList");
				 }				 
				 velocityContext.put("revolutionDoor", revolutionDoor);	
				 velocityContext.put("smoothDoor", smoothDoor);	
				 List<WxAttachment> attachmentList=wxOfferDao.getAttachementList(id);
				 velocityContext.put("attachmentList", attachmentList);
				ViewVelocity.view(request,response,viewName,velocityContext);
	}
	
	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute WxOffer wxOffer,@ModelAttribute WxOfferMainPage offerMainPage){
		AjaxJson j = new AjaxJson();
		try {
			String id=wxOffer.getId();
			wxGroupInfosDao.delete(id);
			wxRevolutionDoorDao.delete(id);
			
			List<WxGroupInfos> groupInfo3s = offerMainPage.getGroupInfo3s();
			List<WxGroupInfos> groupInfo4s = offerMainPage.getGroupInfo4s();
			List<WxGroupInfos> groupInfo5s = offerMainPage.getGroupInfo5s();
			List<WxRevolutionDoor> revolutionDoor = offerMainPage.getRevolutionDoor();
			List<WxRevolutionDoor> smoothDoor = offerMainPage.getSmoothDoor();
			saveGroupInfos(id,3,groupInfo3s);
			saveGroupInfos(id,4,groupInfo4s);
			saveGroupInfos(id,5,groupInfo5s);
			saveWxRevolutionDoor(id,revolutionDoor,"XZM");
			saveWxRevolutionDoor(id,smoothDoor,"PHM");	
			
			List<WxAttachment> attachment=offerMainPage.getAttachment();
			saveWxAttachment(id,attachment);
			
			wxOfferDao.update(wxOffer);
			j.setMsg("编辑成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("编辑失败");
		}
		return j;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(params="doDelete",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id){
			AjaxJson j = new AjaxJson();
			try {			
				wxOfferDao.delete(id);
				wxRevolutionDoorDao.delete(id);
				wxGroupInfosDao.delete(id);
				wxOfferDao.deleteAttachement(id);
				
				
				j.setMsg("删除成功");
			} catch (Exception e) {
				e.printStackTrace();
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}
	
	
	/**
	 * 明细
	 * @throws Exception 
	 * 
	 */	 
	@RequestMapping(params="goDetail2",method = RequestMethod.GET)
	@ResponseBody
	public void goDetail2(@RequestParam(required = true, value = "id") 
			String id,@RequestParam(value = "item_id") String item_id,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "offer/wxOffer-detail2.vm";
			setDetail2Model(velocityContext,id,item_id);
			setDetail2GroupInfo(velocityContext,id,item_id,"p2Standard");
			setDetail2GroupInfo(velocityContext,id,item_id,"p3Option");
			setDetail2GroupInfo(velocityContext,id,item_id,"p4Surface");
			ViewVelocity.view(request,response,viewName,velocityContext);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//要求序列，使用List
	void setDetail2Model(VelocityContext velocityContext,String id,String item_id){
		List<Map> fInfo=wxRevolutionDoorDao.getDetail2FeildInfo(item_id);
		List<Map> mInfo=wxRevolutionDoorDao.getDetail2ModelInfo(item_id);
		
		
		List<String> lst0=new ArrayList<String>();	
		List<String> lstH=new ArrayList<String>();
		lst0.add("id");	
		lst0.add("fprice");
		lst0.add("fnumber");lstH.add("编号");
		lst0.add("fname");lstH.add("名称");
		lst0.add("fmodel");lstH.add("规格型号");
		for(Map<String,String> mapfInfo:fInfo){
			lst0.add(mapfInfo.get("ffeildname").toString());
			lstH.add(mapfInfo.get("fcaption").toString());
		}
		lst0.add("fremark");
		lstH.add("备注");
		
		velocityContext.put("p1ModelH",lstH);
		
		List<List<WxVmParams>> rntList=new ArrayList<List<WxVmParams>>();
		for(Map mapmInfo:mInfo){
			List<WxVmParams> lst=new ArrayList<WxVmParams>();
			for(String kv:lst0){
				WxVmParams para=new WxVmParams();
				para.setFcode(kv);
				para.setFvalue(mapmInfo.get(kv).toString());
				para.setFtag("");
				if(kv.equals("id") || kv.equals("fprice")){
					para.setFtag("hidden");
				}
				lst.add(para);
			}
			rntList.add(lst);
		}
		velocityContext.put("p1ModelE",rntList);
	}
	
	WxVmParams getVmParams(String kvalue,String kcaption,String ktag){
		WxVmParams para=new WxVmParams();	
		para.setFvalue(kvalue);
		para.setFcaption(kcaption);
		para.setFtag(ktag);
		
		return para;
	}
	
	void setDetail2GroupInfo(VelocityContext velocityContext,String id,String item_id,String group_name){
		List<WxVmParams> rntList=new ArrayList<WxVmParams>();
		List<Map> mInfo=null;
		if(group_name.equals("p2Standard")){
			mInfo=wxOfferDao.getDetail2StandardInfo(item_id);
		}else if(group_name.equals("p3Option")){
			mInfo=wxOfferDao.getDetail2OptionInfo(item_id);
		}else if(group_name.equals("p4Surface")){
			mInfo=wxOfferDao.getDetail2SurfaceInfo(item_id);
		}
		if(mInfo!=null){
			for(Map mapmInfo:mInfo){
				WxVmParams para=null;
				if(group_name.equals("p4Surface")){
					para=getVmParams(mapmInfo.get("id").toString(),mapmInfo.get("fname").toString(),mapmInfo.get("fratio").toString());
				}else{				
					para=getVmParams(mapmInfo.get("id").toString(),mapmInfo.get("fname").toString(),mapmInfo.get("famount").toString());
				}
				rntList.add(para);
			}
		}
		velocityContext.put(group_name,rntList);
	}
	
	
	 /**
	  * 导出
	  * @return
	  */
	@RequestMapping(params="toExport",method = RequestMethod.GET)
	public void toExport(@RequestParam(required = true, value = "id" ) String id,
			HttpServletResponse response,HttpServletRequest request)throws Exception{
			
	}
}

