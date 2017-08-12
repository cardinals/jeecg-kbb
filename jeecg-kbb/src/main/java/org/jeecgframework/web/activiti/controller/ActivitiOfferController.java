package org.jeecgframework.web.activiti.controller;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.author.LoginUser;
import org.jeecgframework.p3.core.page.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.activiti.entity.ATaskEntity;
import org.jeecgframework.web.activiti.entity.WorkflowBean;
import org.jeecgframework.web.activiti.service.IBillService;
import org.jeecgframework.web.activiti.service.IWorkflowService;
import org.jeecgframework.web.door.entity.TDoorsEntity;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecg.offer.entity.WxOffer;


@Controller
@RequestMapping("/activitiOffer")
public class ActivitiOfferController extends BaseController {
	@Autowired
	private IWorkflowService workflowService;
	@Autowired
	private IBillService offerBillService;
	
	@RequestMapping(params="deploymentProcessDefinition",method = RequestMethod.GET)	
	public void deploymentProcessDefinition(){
		workflowService.deploymentProcessDefinition("activiti/offer/Offer");
	}

	
	@RequestMapping(params="submitTask",method = RequestMethod.POST)	
	public void submitTask(HttpServletRequest request, HttpServletResponse response){	
		PrintWriter  out = null;
		String text="";
		try{
			out=response.getWriter();
			String id=request.getParameter("id");
			WorkflowBean workflowBean=new WorkflowBean();
			workflowBean.setBillType("Offer");
			workflowBean.setId(id);
			workflowService.setBillService(offerBillService);
			String processInstanceId=workflowService.saveStartProcess(workflowBean);
			text="OK";
			
		}catch(Exception e){
			System.out.println(e.toString());
			text=e.getMessage();
		}finally{
			out.print(text);
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping(params="myTaskList",method = RequestMethod.GET)	
	public void myTaskList(@ModelAttribute TaskEntity query,HttpServletRequest request, HttpServletResponse response){	
		 try {
			 List<Task> listTask=workflowService.findTaskListByName(ResourceUtil.getSessionUserName().getRealName());
			 VelocityContext velocityContext = new VelocityContext();
			 velocityContext.put("task",query);
			 velocityContext.put("listTask",listTask);
			 String viewName = "activiti/task-list.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 详情
	 * */
	@RequestMapping(params="jumpToBill",method = RequestMethod.POST)	
	public void jumpToBill(HttpServletRequest request, HttpServletResponse response){	
		PrintWriter  out = null;
		String text="";
		try{
			out=response.getWriter();
			String id=request.getParameter("id");
			String buniness_key=this.workflowService.findBusinessKeyByTaskId(id);
			String businessId = "";
			if(StringUtils.isNotBlank(buniness_key)){
				//截取字符串，取buniness_key小数点的第2个值
				businessId = buniness_key.split("\\.")[1];
			}
			text="p3/wxOffer.do?toDetail&id="+businessId;
		}catch(Exception e){
			System.out.println(e.toString());
			text=e.getMessage();
		}finally{
			out.print(text);
			out.flush();
			out.close();
		}
	}
	/**
	 * 驳回
	 * */
	@RequestMapping(params="rejectTask",method = RequestMethod.POST)	
	public void rejectTask(HttpServletRequest request, HttpServletResponse response){	
		PrintWriter  out = null;
		String text="";
		try{
			out=response.getWriter();
			String id=request.getParameter("id");			
			workflowService.rejecttoPreTask(id);;
			text="OK";			
		}catch(Exception e){
			System.out.println(e.toString());
			text=e.getMessage();
		}finally{
			out.print(text);
			out.flush();
			out.close();
		}
	}
	/**
	 * 通过
	 * */
	@RequestMapping(params="adoptTask",method = RequestMethod.POST)	
	public void adoptTask(HttpServletRequest request, HttpServletResponse response){	
		PrintWriter  out = null;
		String text="";
		try{
			out=response.getWriter();
			String id=request.getParameter("id");
			WorkflowBean workflowBean=new WorkflowBean();
			workflowBean.setTaskId(id);
			String buniness_key=this.workflowService.findBusinessKeyByTaskId(id);			
			if(StringUtils.isNotBlank(buniness_key)){
				String[] binfo=buniness_key.split("\\.");
				workflowBean.setBillType(binfo[0]);
				workflowBean.setId(binfo[1]);
			}		
			String message=request.getParameter("message");
			workflowBean.setComment("同意。"+message);
			workflowBean.setOutcome("默认提交");			
			String nextprocessor=request.getParameter("nextprocessor");
			workflowBean.setNextprocessor(nextprocessor);
			workflowService.setBillService(offerBillService);
			workflowService.saveSubmitTask(workflowBean);
			text="OK";
			
		}catch(Exception e){
			System.out.println(e.toString());
			text=e.getMessage();
		}finally{
			out.print(text);
			out.flush();
			out.close();
		}
	}
}
