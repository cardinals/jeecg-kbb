package org.jeecgframework.web.activiti.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
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
import org.jeecgframework.web.activiti.entity.HistoryEntity;
import org.jeecgframework.web.activiti.entity.ProcessorEntity;
import org.jeecgframework.web.activiti.entity.WorkflowBean;
import org.jeecgframework.web.activiti.service.IBillService;
import org.jeecgframework.web.activiti.service.IWorkflowService;
import org.jeecgframework.web.base.service.KBaseServiceI;
import org.jeecgframework.web.door.entity.TDoorsEntity;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.offer.entity.WxOffer;


@Controller
@RequestMapping("/activitiOffer")
public class ActivitiOfferController extends BaseController {
	@Autowired
	private IWorkflowService workflowService;
	@Autowired
	private IBillService offerBillService;	
	@Autowired
	private KBaseServiceI kBaseService;
	
	@RequestMapping(params="deploymentProcessDefinition",method = RequestMethod.GET)	
	public void deploymentProcessDefinition(){
		workflowService.deploymentProcessDefinition("activiti/offer/Offer","报价单申请");		
		workflowService.deploymentProcessDefinition("activiti/offer/Discount","报价单折扣申请");
	}

	
	@RequestMapping(params="submitTask",method = RequestMethod.POST)	
	public void submitTask(HttpServletRequest request, HttpServletResponse response){	
		PrintWriter  out = null;
		String text="";
		try{
			out=response.getWriter();
			String id=request.getParameter("id");
			WorkflowBean workflowBean=new WorkflowBean();
			String type=request.getParameter("type");
			if(type.equals("discount")){
				workflowBean.setBillType("Discount");
			}else{
				workflowBean.setBillType("Offer");
			}
			workflowBean.setId(id);
			workflowService.setBillService(offerBillService);
			String nextTaskId=workflowService.saveStartProcess(workflowBean);
			StringBuilder sbSql=new StringBuilder();
			sbSql.append("select tbu2.realname from t_s_base_user tbu1 ");
			sbSql.append("inner join t_s_user_org tuo1 on tbu1.id=tuo1.user_id  ");
			sbSql.append("inner join t_s_depart tdp1 on tdp1.ID=tuo1.org_id  ");
			sbSql.append("inner join t_s_depart tdp2 on tdp1.parentdepartid=tdp2.id ");
			sbSql.append("inner join t_s_user_org tuo2 on tdp2.id=tuo2.org_id  ");
			sbSql.append("inner join t_s_base_user tbu2 on tbu2.id=tuo2.user_id  ");
			sbSql.append("where tbu1.id='"+ResourceUtil.getSessionUserName().getId() +"'; ");		
			List<Map<String,Object>> lst=this.offerBillService.findForJdbc(sbSql.toString());
			String nextprocessor="";
			if(lst.size()>0){
				nextprocessor=lst.get(0).get("realname").toString();
			}			
			saveAdoptTask(nextTaskId,"默认提交",nextprocessor);
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
	
	@RequestMapping(params="myTaskList")	
	public void myTaskList(HttpServletRequest request, HttpServletResponse response){	
		 try {
			 List<Task> listTask=workflowService.findTaskListByName(ResourceUtil.getSessionUserName().getRealName());
			 
			 List<ATaskEntity> list=new ArrayList<ATaskEntity>();
			 Iterator<Task> it = listTask.iterator();
			 while(it.hasNext()) {
				Task task= it.next();
				ATaskEntity nTask=new ATaskEntity();
				nTask.setId(task.getId());
				String businessType = findBusinessKey(task.getId(),1);
				if(businessType.equals("Discount")){
					nTask.setFname("报价单折扣申请");
				}else{
					nTask.setFname("报价单申请");
				}				
				HistoricTaskInstance hti=workflowService.findLastSubmitInfo(task.getId());
				if(hti!=null){
					nTask.setFlastsubmitter(hti.getAssignee());
					nTask.setFlasttime(hti.getEndTime());
				}
				List<Comment> listComment=workflowService.findCommentByTaskId(task.getId());
				if(listComment.size()>0){
					nTask.setFlastremark(listComment.get(0).getFullMessage());
				}
				list.add(nTask);
			 }
			
			 VelocityContext velocityContext = new VelocityContext();
			 velocityContext.put("listTask",list);
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
			String businessId = findBusinessKey(id,1);
			text="p3/wxOffer.do?toDetail&id="+businessId+"&backUrl=myTaskList";
		}catch(Exception e){
			System.out.println(e.toString());
			text=e.getMessage();
		}finally{
			out.print(text);
			out.flush();
			out.close();
		}
	}
	
	String findBusinessKey(String taskId,int index){
		String buniness_key=this.workflowService.findBusinessKeyByTaskId(taskId);		
		if(StringUtils.isNotBlank(buniness_key)){
			//截取字符串，取buniness_key小数点的第2个值
			String[] businessKey = buniness_key.split("\\.");
			return businessKey[index];
		}
		return "";
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
			String message=request.getParameter("message");
			String nextprocessor=request.getParameter("nextprocessor");			
			saveAdoptTask(id,message,nextprocessor);
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
	void saveAdoptTask(String id,String message,String nextprocessor){		
		WorkflowBean workflowBean=new WorkflowBean();
		workflowBean.setTaskId(id);
		String buniness_key=this.workflowService.findBusinessKeyByTaskId(id);			
		if(StringUtils.isNotBlank(buniness_key)){
			String[] binfo=buniness_key.split("\\.");
			workflowBean.setBillType(binfo[0]);
			workflowBean.setId(binfo[1]);
		}
		workflowBean.setComment("同意。"+message);
		workflowBean.setOutcome("默认提交");	
		workflowBean.setNextprocessor(nextprocessor);
		Map<String,Object> variables=new HashMap<String,Object>();
		variables.put("branch", "normal");
		workflowBean.setVariables(variables);
		
		workflowService.setBillService(offerBillService);
		workflowService.saveSubmitTask(workflowBean);
	}
	
	
	@RequestMapping(params="showApproval")	
	public void showApproval(HttpServletRequest request, HttpServletResponse response){	
		 try {
			 
			 VelocityContext velocityContext = new VelocityContext();
			 velocityContext.put("taskId", request.getParameter("id"));
			 String viewName = "activiti/task-handle.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params="getNextProcessor")	
	@ResponseBody
	public Map<String,Object> getNextProcessor(HttpServletRequest request, HttpServletResponse response){	
		 Map<String,Object> result = new HashMap<String,Object>(); 
		 try {
			 //获取下一节点的TaskKey
			 String taskId=request.getParameter("id");
			 List<TaskDefinition> listTaskDefinition=this.workflowService.nextTaskDefinition(taskId);
			 Iterator<TaskDefinition> it = listTaskDefinition.iterator();
			 String userTask="";
			 while(it.hasNext()) {
				 TaskDefinition taskDefinition= it.next();
				 userTask=taskDefinition.getKey();
				 break;
			 }
			 //根据节点的TaskKey角色的
			 List<ProcessorEntity>  listProcessor=this.offerBillService.getNextprocessor(userTask);
			 result.put("rows",  listProcessor);
			 return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}
	
	@RequestMapping(params="showWorkflow",method = RequestMethod.GET)
	@ResponseBody
	public void showWorkflow(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			String businesskey=request.getParameter("businesskey");
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "activiti/workflow.vm";
			Task task=workflowService.findTaskByBusinesskey(businesskey);
			if(task!=null){
				velocityContext.put("acs", workflowService.findCoordingByTask(task.getId()));
			}
			List<HistoryEntity> historyList=workflowService.findHistoryByBusinesskey(businesskey);
			velocityContext.put("proc_def_id",historyList.get(0).getProcessdefinitionid());
			velocityContext.put("historyList", historyList);
			ViewVelocity.view(request,response,viewName,velocityContext);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * 展示流程详情
	 * */
	@RequestMapping(params="viewImage")	
	public void viewImage(HttpServletRequest request, HttpServletResponse response){
		String proc_def_id=request.getParameter("proc_def_id");
		String deploymentId = "";
		String imageName ="";
		List<ProcessDefinition> definitionList=workflowService.findProcessDefinitionList();
		Iterator<ProcessDefinition> it = definitionList.iterator();		
		 while(it.hasNext()) {
			 ProcessDefinition def=it.next();
			 if(def.getId().equals(proc_def_id)){
				 deploymentId=def.getDeploymentId();
				 imageName=def.getDiagramResourceName();
				 break;
			 }		 
		 }		
		//2：获取资源文件表（act_ge_bytearray）中资源图片输入流InputStream
		InputStream in = workflowService.findImageInputStream(deploymentId,imageName);
		//3：从response对象获取输出流
		OutputStream out;
		try {
			out = response.getOutputStream();
			//4：将输入流中的数据读取出来，写到输出流中
			for(int b=-1;(b=in.read())!=-1;){
				out.write(b);
			}	
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}
