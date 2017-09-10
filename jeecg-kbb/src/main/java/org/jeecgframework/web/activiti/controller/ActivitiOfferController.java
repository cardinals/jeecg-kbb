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

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.web.activiti.entity.ATaskEntity;
import org.jeecgframework.web.activiti.entity.HistoryEntity;
import org.jeecgframework.web.activiti.entity.ProcessorEntity;
import org.jeecgframework.web.activiti.entity.WorkflowBean;
import org.jeecgframework.web.activiti.service.IBillService;
import org.jeecgframework.web.activiti.service.IWorkflowService;
import org.jeecgframework.web.base.service.KBaseServiceI;
import org.jeecgframework.web.system.util.DbReaderUtil;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;


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
//		workflowService.deploymentProcessDefinition("activiti/offer/Discount","报价单折扣申请");
	}

	
	@RequestMapping(params="submitTask",method = RequestMethod.POST)	
	public void submitTask(HttpServletRequest request, HttpServletResponse response){	
		PrintWriter  out = null;
		String text="";
		try{
			out=response.getWriter();
			String id=request.getParameter("id");			
			String type=request.getParameter("type");
			doStartAddSubmitFirstStep(id,type,"");			
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
	
	void doStartAddSubmitFirstStep(String id,String type,String message) throws Exception{	
		WorkflowBean workflowBean=new WorkflowBean();
		if(type.equals("discount")){
			workflowBean.setBillType("Discount");
		}else{
			workflowBean.setBillType("Offer");
		}
		workflowBean.setId(id);
		workflowService.setBillService(offerBillService);
		String nextTaskId=workflowService.saveStartProcess(workflowBean);
		String nextprocessor=getAreaMangerRealNameBySaleMan();
	    Map<String,Object> variable=new HashMap<String,Object>();
	    variable.put("initiator", ResourceUtil.getSessionUserName().getRealName());
		saveAdoptTask(nextTaskId,message,nextprocessor,"",variable);
	}
	
	String getAreaMangerRealNameBySaleMan(){
		//旨在找到该销售员的大区经理
		//1、找到该销售员的组织机构
		String org_id=this.offerBillService.findUniqueValue("select org_id from t_s_user_org where user_id=?", 
				ResourceUtil.getSessionUserName().getId());
		//2、找到经理角色ID
	    String role_id=this.offerBillService.findUniqueValue("select id from t_s_role where rolecode=? ", "areamanager");
	    //3、通过组织机构ID和角色ID找到用户ID
	    String user_id=this.offerBillService.findUniqueValue("select org.user_id from t_s_user_org org "
	    		+ "inner join t_s_role_user role on org.user_id =role.userid "
	    		+ "where org.org_id=? and role.roleid=?", org_id,role_id);
	    //4、获得用户realname
	    String realname=this.offerBillService.findUniqueValue("select realname from t_s_base_user where id=? ", user_id);	
	    return realname;
	}
	@RequestMapping(params="myTaskList")	
	public void myTaskList(HttpServletRequest request, HttpServletResponse response){	
		 try {
			 VelocityContext velocityContext=new VelocityContext();
			 ViewVelocity.view(request,response,"activiti/mytask-list.vm",velocityContext);
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }	
	}
	
	@RequestMapping(params="myWaitTaskList")	
	public void myWaitTaskList(HttpServletRequest request, HttpServletResponse response){	
		 try {
			 List<Task> listTask=workflowService.findTaskListByName(ResourceUtil.getSessionUserName().getRealName());
			 
			 List<ATaskEntity> list=new ArrayList<ATaskEntity>();
			 Iterator<Task> it = listTask.iterator();
			 while(it.hasNext()) {
				Task task= it.next();
				ATaskEntity nTask=new ATaskEntity();
				nTask.setFname(task.getName());
				nTask.setId(task.getId());
				String businessKey=this.workflowService.findBusinessKeyByTaskId(task.getId());
				nTask.setFbusinesskey(businessKey);	
				if(!StringUtil.isBlank(businessKey)){
					nTask.setFbillno(this.offerBillService.getBillNo(businessKey.split("\\.")[1]));
				}
				HistoricTaskInstance hti=workflowService.findLastSubmitInfo(task.getId());
				if(hti!=null){
					nTask.setFlastsubmitter(hti.getAssignee());
					nTask.setFlasttime(DbReaderUtil.readDateTime(hti.getEndTime()));
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
			String businessId =request.getParameter("businesskey");
			if(StringUtil.isBlank(businessId)){
				String taskId=request.getParameter("id");
				businessId = findBusinessKey(taskId,1);
			}else {
				businessId=businessId.split("\\.")[1];
			}
			String roleCode=this.kBaseService.getUserRole(ResourceUtil.getSessionUserName().getId());			
			text="wxOffer.do?toDetail&id="+businessId+"&backUrl="+ request.getParameter("backurl")+"&roleCode="+roleCode;
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
	 * 通过
	 * */
	@RequestMapping(params="adoptTask",method = RequestMethod.POST)	
	public void adoptTask(HttpServletRequest request, HttpServletResponse response){	
		PrintWriter  out = null;
		String text="";
		try{
			out=response.getWriter();
			String taskId=request.getParameter("id");
			String message=request.getParameter("message");
			String nextprocessor=request.getParameter("nextprocessor");
			String branch=request.getParameter("branch");			
			if(branch==null){
				branch="";
			}
			String businessType=request.getParameter("businessType");
			if(businessType.equals("discount")){
				nextprocessor=getAreaMangerRealNameBySaleMan();			
				//处理业务数据
				kBaseService.executeSql("update t_offers set fdiscountrate=?,fafteramount=? where id=?", 
						request.getParameter("businessPackage[discountrate]"),
						request.getParameter("businessPackage[afteramount]"),
						findBusinessKey(taskId,1));	
			}else if(businessType.equals("outerprice")){
				nextprocessor=workflowService.findVariableValue(taskId,"initiator");
				kBaseService.executeSql("update t_offers set fisoutsource='是',fouterprice=? where id=?", 
						request.getParameter("businessPackage[fouterprice]"),
						findBusinessKey(taskId,1));	
			}
			Map<String,Object> variables=new HashMap<String,Object>();
			//根据入参做不同的业务处理
			variables.put("definitionid", request.getParameter("definitionid"));			
			saveAdoptTask(taskId,message,nextprocessor,branch,variables);
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
	void saveAdoptTask(String taskId,String message,String nextprocessor,String branch,Map<String,Object> variables){		
		WorkflowBean workflowBean=new WorkflowBean();
		workflowBean.setTaskId(taskId);
		String buniness_key=this.workflowService.findBusinessKeyByTaskId(taskId);			
		if(StringUtils.isNotBlank(buniness_key)){
			String[] binfo=buniness_key.split("\\.");
			workflowBean.setBillType(binfo[0]);
			workflowBean.setId(binfo[1]);
		}
		workflowBean.setMessage(message);
		workflowBean.setBreanch(branch);
		if("驳回".equals(branch)){
			nextprocessor=workflowService.findVariableValue(taskId,"lastassignee");
		}
		if(variables.containsKey("definitionid")){
			//技术总监审核后返回给发起人
			if("saleman".equals(variables.get("definitionid").toString())){
				nextprocessor=workflowService.findVariableValue(taskId,"initiator");
			}
		}
		workflowBean.setNextprocessor(nextprocessor);
		workflowService.setBillService(offerBillService);
		workflowService.saveSubmitTask(workflowBean);
	}
	
	
	@RequestMapping(params="showApproval")	
	public void showApproval(HttpServletRequest request, HttpServletResponse response){	
		 try {
			 String taskId=request.getParameter("id").toString();
			 VelocityContext velocityContext = new VelocityContext();
			 velocityContext.put("taskId", taskId);
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
			 String userTask=request.getParameter("definitionid");
			 if(StringUtil.isBlank(userTask)){
				 while(it.hasNext()) {
					 TaskDefinition taskDefinition= it.next();
					 userTask=taskDefinition.getKey();
					 break;
				 }
			 }
			 //根据节点的TaskKey角色的
			 List<ProcessorEntity>  listProcessor=this.offerBillService.getNextprocessor(userTask);
			 result.put("total", listProcessor.size());
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
			if("true".equals(request.getParameter("viewImage"))){
				velocityContext.put("viewImage",true );
			}
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
	@RequestMapping(params="toDiscount",method = RequestMethod.GET)
	@ResponseBody
	public void toDiscount(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			String businesskey=request.getParameter("businesskey");
			if(!StringUtil.isBlank(businesskey)){
				String[] arr=businesskey.split("\\.");
				String billId=arr[1];
				VelocityContext velocityContext = new VelocityContext();
				String viewName = "activiti/discount.vm";			
				Map<String,Object> dr=this.offerBillService.getBillFieldValue(billId, "famount","fdiscountrate","fafteramount");
				velocityContext.put("totalamount",dr.get("famount"));
				velocityContext.put("billId", billId);			  
				velocityContext.put("fdiscountrate", dr.get("fdiscountrate"));
				velocityContext.put("fafteramount", dr.get("fafteramount"));				
				ViewVelocity.view(request,response,viewName,velocityContext);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	@RequestMapping(params="toOuterprice",method = RequestMethod.GET)
	@ResponseBody
	public void toOuterprice(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			String businesskey=request.getParameter("businesskey");
			if(!StringUtil.isBlank(businesskey)){
				String[] arr=businesskey.split("\\.");
				String billId=arr[1];
				VelocityContext velocityContext = new VelocityContext();
				String viewName = "activiti/outsource.vm";			
				Map<String,Object> dr=this.offerBillService.getBillFieldValue(billId, "fouterprice");
				velocityContext.put("fouterprice",dr.get("fouterprice"));
				velocityContext.put("billId", billId);
				ViewVelocity.view(request,response,viewName,velocityContext);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	/*
	 * return Map
	 * success:true/false
	 * businesskey:
	 * formKey:
	 * errdesc:
	*/
	@RequestMapping(params="getTaskHandle")	
	public void getTaskHandle(HttpServletRequest request, HttpServletResponse response){	
		PrintWriter  out = null;
		Map<String,String> result=new HashMap<String,String>();
		result.put("success", "true");
		result.put("businesskey", "");
		result.put("formKey", "");
		result.put("errdesc", "");
		try{
			String taskId=request.getParameter("id");			
			List<TaskDefinition> defList =workflowService.nextTaskDefinition(taskId);			
			if(defList.size()==0){
				result.put("errdesc", "end");
			}
			 String businessKey=workflowService.findBusinessKeyByTaskId(taskId);
			 if(StringUtils.isNotBlank(businessKey)){
				 result.put("businesskey", businessKey);
			 }
			 String formKey= this.workflowService.findTaskFormKeyByTaskId(taskId);
			 if(StringUtils.isNotBlank(formKey)){
				 result.put("formKey", formKey);
			 }
			 out=response.getWriter();			
		}catch(Exception e){
			System.out.println(e.toString());			
			result.put("success", "false");
			result.put("errdesc", e.getMessage());			
		}finally{			
			out.print(JSON.toJSONString(result));
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping(params="myDoneTaskList")	
	public void myDoneTaskList(HttpServletRequest request, HttpServletResponse response){	
		 try {
			 workflowService.setBillService(offerBillService);
			 List<ATaskEntity> listTask=workflowService.findHistoryTask(ResourceUtil.getSessionUserName().getRealName());			
			 VelocityContext velocityContext = new VelocityContext();
			 velocityContext.put("listTask",listTask);
			 String viewName = "activiti/task-donelist.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
