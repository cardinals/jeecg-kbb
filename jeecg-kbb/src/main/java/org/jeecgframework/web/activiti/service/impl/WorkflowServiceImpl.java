package org.jeecgframework.web.activiti.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.jeecgframework.core.common.dao.ICommonDao;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.DataGridReturn;
import org.jeecgframework.core.common.model.json.ImportFile;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.jeecgframework.tag.vo.easyui.Autocomplete;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.activiti.entity.HistoryEntity;
import org.jeecgframework.web.activiti.entity.WorkflowBean;
import org.jeecgframework.web.activiti.service.IBillService;
import org.jeecgframework.web.activiti.service.IWorkflowService;
import org.jeecgframework.web.base.service.KBaseServiceI;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.sms.entity.TSSmsEntity;
import org.jeecgframework.web.system.sms.service.TSSmsServiceI;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service("workflowService")
@Transactional
public class WorkflowServiceImpl extends CommonServiceImpl implements IWorkflowService {	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private FormService formService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private KBaseServiceI kBaseService;
	private IBillService billService;
	
	
	
	@Override
	public void setBillService(IBillService billService) {
		this.billService = billService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
	
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
	@Override
	public void deploymentProcessDefinition(String fileUrl,String name){		
		this.repositoryService.createDeployment()//创建一个部署对象
				.name(name)//添加部署的名称
				.addClasspathResource(fileUrl+".bpmn")//从classpath的资源中加载，一次只能加载一个文件
				.addClasspathResource(fileUrl+".png")//从classpath的资源中加载，一次只能加载一个文件
				.deploy();//完成部署
				
	}
	
	/**部署流程定义*/
	@Override
	public void saveNewDeploye(File file, String filename) {
		try {
			//2：将File类型的文件转化成ZipInputStream流
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
			repositoryService.createDeployment()//创建部署对象
							.name(filename)//添加部署名称
							.addZipInputStream(zipInputStream)//
							.deploy();//完成部署
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**查询部署对象信息，对应表（act_re_deployment）*/
	@Override
	public List<Deployment> findDeploymentList() {
		List<Deployment> list = repositoryService.createDeploymentQuery()//创建部署对象查询
							.orderByDeploymenTime().asc()//
							.list();
		return list;
	}
	
	/**查询流程定义的信息，对应表（act_re_procdef）*/
	@Override
	public List<ProcessDefinition> findProcessDefinitionList() {
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()//创建流程定义查询
							.orderByProcessDefinitionVersion().asc()//
							.list();
		return list;
	}
	
	/**使用部署对象ID和资源图片名称，获取图片的输入流*/
	@Override
	public InputStream findImageInputStream(String deploymentId,
			String imageName) {
		return repositoryService.getResourceAsStream(deploymentId, imageName);
	}	
	
	/**使用部署对象ID，删除流程定义*/
	@Override
	public void deleteProcessDefinitionByDeploymentId(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}
	
	/**更新请假状态，启动流程实例，让启动的流程实例关联业务
	 * @throws Exception */
	@Override
	public String saveStartProcess(WorkflowBean workflowBean) throws Exception {
		//1：获取请假单ID，使用请假单ID，查询请假单的对象LeaveBill
		String id = workflowBean.getId();
		String key=workflowBean.getBillType();
		//2：更新请假单的请假状态从0变成1（初始录入-->审核中）
		if(workflowBean.getBillType().equals("Discount")){
			billService.setBillStatus(id, "折扣申请");
		}else{
			billService.setBillStatus(id, "审批中");
		}
		//3：使用当前对象获取到流程定义的key（对象的名称就是流程定义的key）
		//String key = leaveBill.getClass().getSimpleName();
		/**
		 * 4：从Session中获取当前任务的办理人，使用流程变量设置下一个任务的办理人
			    * inputUser是流程变量的名称，
			    * 获取的办理人是流程变量的值
		 */
	
		Map<String, Object> variables = new HashMap<String,Object>();
		variables.put("processor", ResourceUtil.getSessionUserName().getRealName());//表示惟一用户
		/**
		 * 5：	(1)使用流程变量设置字符串（格式：LeaveBill.id的形式），通过设置，让启动的流程（流程实例）关联业务
   				(2)使用正在执行对象表中的一个字段BUSINESS_KEY（Activiti提供的一个字段），让启动的流程（流程实例）关联业务
		 */
		//格式：LeaveBill.id的形式（使用流程变量）
		String objId = key+"."+id;
		variables.put("objId", objId);
		//6：使用流程定义的key，启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
		Authentication.setAuthenticatedUserId(ResourceUtil.getSessionUserName().getRealName());
		
		ProcessInstance pi= runtimeService.startProcessInstanceByKey(key,objId,variables);
		List<Task> taskList = taskService.createTaskQuery().processInstanceId(pi.getId())  
				.orderByTaskCreateTime()
				.desc()
				.list();
		Task task=taskList.get(0);//取最新的一个
		return task.getId();
	}
	
	
	
	/**2：使用当前用户名查询正在执行的任务表，获取当前任务的集合List<Task>*/
	@Override
	public List<Task> findTaskListByName(String name) {
		List<Task> list = taskService.createTaskQuery()//
					.taskAssignee(name)//指定个人任务查询
					.orderByTaskCreateTime().asc()//
					.list();
		return list;
	}
	
	/**使用任务ID，获取当前任务节点中对应的Form key中的连接的值*/
	@Override
	public String findTaskFormKeyByTaskId(String taskId) {
		TaskFormData formData = formService.getTaskFormData(taskId);
		//获取Form key的值
		String url = formData.getFormKey();
		return url;
	}
	
	/**一：使用任务ID，查找请假单ID，从而获取请假单信息*/
	@Override
	public String findBusinessKeyByTaskId(String taskId) {
		//1：使用任务ID，查询任务对象Task
		Task task = taskService.createTaskQuery()//
						.taskId(taskId)//使用任务ID查询
						.singleResult();
		//2：使用任务对象Task获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		//3：使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
						.processInstanceId(processInstanceId)//使用流程实例ID查询
						.singleResult();
		//4：使用流程实例对象获取BUSINESS_KEY
		String buniness_key = pi.getBusinessKey();
		
		return buniness_key;
	}
	
	/**二：已知任务ID，查询ProcessDefinitionEntiy对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中*/
	@Override
	public List<String> findOutComeListByTaskId(String taskId) {
		//返回存放连线的名称集合
		List<String> list = new ArrayList<String>();
		//1:使用任务ID，查询任务对象
		Task task = taskService.createTaskQuery()//
					.taskId(taskId)//使用任务ID查询
					.singleResult();
		//2：获取流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		//3：查询ProcessDefinitionEntiy对象
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
		//使用任务对象Task获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		//使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
					.processInstanceId(processInstanceId)//使用流程实例ID查询
					.singleResult();
		//获取当前活动的id
		String activityId = pi.getActivityId();
		//4：获取当前的活动
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
		//5：获取当前活动完成之后连线的名称
		List<PvmTransition> pvmList = activityImpl.getOutgoingTransitions();
		if(pvmList!=null && pvmList.size()>0){
			for(PvmTransition pvm:pvmList){
				String name = (String) pvm.getProperty("name");
				if(StringUtils.isNotBlank(name)){
					list.add(name);
				}
				else{
					list.add("默认提交");
				}
			}
		}
		return list;
	}
	
	/**指定连线的名称完成任务*/
	@Override
	public String saveSubmitTask(WorkflowBean workflowBean) {
		//获取任务ID
		String taskId = workflowBean.getTaskId();
		//获取连线的名称
		String outcome = workflowBean.getOutcome();
		//批注信息
		String message = workflowBean.getComment();
		//获取请假单ID
		String id = workflowBean.getId();
		
		/**
		 * 1：在完成之前，添加一个批注信息，向act_hi_comment表中添加数据，用于记录对当前申请人的一些审核信息
		 */
		//使用任务ID，查询任务对象，获取流程流程实例ID
		Task task = taskService.createTaskQuery()//
						.taskId(taskId)//使用任务ID查询
						.singleResult();
		//获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		String billType="";
		String billId="";
		String businessKey=this.findBusinessKeyByTaskId(taskId);
		if(!StringUtil.isBlank(businessKey)){
			billType=businessKey.split("\\.")[0];
			billId=businessKey.split("\\.")[1];
		}
		/**
		 * 注意：添加批注的时候，由于Activiti底层代码是使用：
		 * 		String userId = Authentication.getAuthenticatedUserId();
			    CommentEntity comment = new CommentEntity();
			    comment.setUserId(userId);
			  所有需要从Session中获取当前登录人，作为该任务的办理人（审核人），对应act_hi_comment表中的User_ID的字段，不过不添加审核人，该字段为null
			 所以要求，添加配置执行使用Authentication.setAuthenticatedUserId();添加当前任务的审核人
		 * */
		Authentication.setAuthenticatedUserId(ResourceUtil.getSessionUserName().getRealName());
		taskService.addComment(taskId, processInstanceId, message);
		/**
		 * 2：如果连线的名称是“同意”，那么就不需要设置，如果不是，就需要设置流程变量
		 * 在完成任务之前，设置流程变量，按照连线的名称，去完成任务
				 流程变量的名称：outcome
				 流程变量的值：连线的名称
		 */
		Map<String, Object> variables = new HashMap<String,Object>();
		String nextProcessor=workflowBean.getNextprocessor();
		nextProcessor=nextProcessor==null?"":nextProcessor;
		variables.put("processor", nextProcessor);
		if(outcome!=null && !outcome.equals("默认提交")){
			variables.put("outcome", outcome);
			Map<String,Object> map=workflowBean.getVariables();
			if(map!=null){
				variables.putAll(map);
			}
		}

		//3：使用任务ID，完成当前人的个人任务，同时流程变量
		HistoricProcessInstance hi = historyService.createHistoricProcessInstanceQuery()
				.processInstanceBusinessKey(businessKey).singleResult();
		taskService.complete(taskId, variables);
		billService.setBillCurrentApprover(id,nextProcessor);
		this.kBaseService.addNotice(hi.getStartUserId(), 
				ResourceUtil.getSessionUserName().getRealName() + "审批了您的"+(billType.equals("Discount")?"折扣申请":"报价单")
				+"："+billService.getBillNo(billId), 
				task.getName() + "\r\n"+ ResourceUtil.getSessionUserName().getRealName() +"批示：" + outcome + message);
		//4：当任务完成之后，需要指定下一个任务的办理人（使用类）-----已经开发完成
		
		/**
		 * 5：在完成任务之后，判断流程是否结束
   			如果流程结束了，更新请假单表的状态从1变成2（审核中-->审核完成）
		 */
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
						.processInstanceId(processInstanceId)//使用流程实例ID查询
						.singleResult();
		
		String nextTaskId="";
		//流程结束了
		if(pi==null){
			if(billType.equals("Offer")){
			//更新请假单表的状态从1变成2（审核中-->审核完成）
				billService.setBillStatus(id, "完成");
			}else if(billType.equals("Discount")){
				billService.setBillStatus(id, "关闭");
			}
		}else{
			Task ntask = taskService.createTaskQuery().processInstanceId(pi.getId())  
	                .singleResult();  
		
			nextTaskId=ntask.getId();
		}
		return nextTaskId;
	}
	

	/*
	 * 获取最新的审批记录
	 * */
	public HistoricTaskInstance findLastSubmitInfo(String taskId){
		Task task = taskService.createTaskQuery()//
				.taskId(taskId)//使用任务ID查询
				.singleResult();
		String processInstanceId = task.getProcessInstanceId();
		List<HistoricTaskInstance> htiList= historyService.createHistoricTaskInstanceQuery()//历史任务表查询
				.processInstanceId(processInstanceId)//使用流程实例ID查询
				.orderByHistoricTaskInstanceEndTime()
				.desc()
				.list();
		if(htiList.size()>0){
			return htiList.get(0);
		}else{
			return null;
		}
			
	}

	
	/**获取批注信息，传递的是当前任务ID，获取历史任务ID对应的批注*/
	@Override
	public List<Comment> findCommentByTaskId(String taskId) {
		List<Comment> list = new ArrayList<Comment>();
		//使用当前的任务ID，查询当前流程对应的历史任务ID
		//使用当前任务ID，获取当前任务对象
		Task task = taskService.createTaskQuery()//
				.taskId(taskId)//使用任务ID查询
				.singleResult();
		//获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		//使用流程实例ID，查询历史任务，获取历史任务对应的每个任务ID
		List<HistoricTaskInstance> htiList = historyService.createHistoricTaskInstanceQuery()//历史任务表查询
						.processInstanceId(processInstanceId)//使用流程实例ID查询
						.list();
//		//遍历集合，获取每个任务ID
//		if(htiList!=null && htiList.size()>0){
//			for(HistoricTaskInstance hti:htiList){
//				//任务ID
//				String htaskId = hti.getId();
//				//获取批注信息
//				List<Comment> taskList = taskService.getTaskComments(htaskId);//对用历史完成后的任务ID
//				list.addAll(taskList);
//			}
//		}
		list = taskService.getProcessInstanceComments(processInstanceId);
		return list;
	}
	@Override
	public Task findTaskByBusinesskey(String businesskey){
			ProcessInstance pi=this.runtimeService.createProcessInstanceQuery()
							.processInstanceBusinessKey(businesskey)
							.singleResult();
			if(pi!=null){
				return taskService.createTaskQuery()
						.processInstanceId(pi.getId())
						.singleResult();
			}else{
				return null;
			}
	}
	
	
	/**使用请假单ID，查询历史批注信息*/
	@Override
	public List<HistoryEntity> findHistoryByBusinesskey(String businesskey) {		
		/**1:使用历史的流程实例查询，返回历史的流程实例对象，获取流程实例ID*/
		HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()//对应历史的流程实例表
						.processInstanceBusinessKey(businesskey)//使用BusinessKey字段查询
						.singleResult();
//		//流程实例ID
//		String processInstanceId = hpi.getId();
		/**2:使用历史的流程变量查询，返回历史的流程变量的对象，获取流程实例ID*/
//		HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery()//对应历史的流程变量表
//						.variableValueEquals("objId", objId)//使用流程变量的名称和流程变量的值查询
//						.singleResult();
		//流程实例ID
		String processInstanceId = hpi.getId();		
		List<HistoricTaskInstance> htiList = historyService.createHistoricTaskInstanceQuery()//历史任务表查询
				.processInstanceId(processInstanceId)//使用流程实例ID查询
				.list();
		List<Comment> list = taskService.getProcessInstanceComments(processInstanceId);
		List<HistoryEntity> listHistory=new ArrayList<HistoryEntity>();
		Iterator<HistoricTaskInstance> it = htiList.iterator();		
		 while(it.hasNext()) {
			 HistoricTaskInstance history= it.next();
			 HistoryEntity entity=new HistoryEntity();
			 entity.setName(history.getName());
			 entity.setAssigee(history.getAssignee());
			 entity.setStart_date(dateToStrLong(history.getStartTime()));
			 entity.setEnd_date(dateToStrLong(history.getEndTime()));
			 entity.setProcessdefinitionid( history.getProcessDefinitionId());
			 for(Comment com:list){
				 if(com.getTaskId().equals(history.getId())){
					 entity.setFullmessage(com.getFullMessage());				
					 break;
				 }
			 }
			 listHistory.add(entity);
		 }
		 return listHistory;
	}
	public static String dateToStrLong(java.util.Date dateDate) {
			if(dateDate==null){
				return "";
			}
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String dateString = formatter.format(dateDate);
		   return dateString;
		}
	/**1：获取任务ID，获取任务对象，使用任务对象获取流程定义ID，查询流程定义对象*/
	@Override
	public ProcessDefinition findProcessDefinitionByTaskId(String taskId) {
		//使用任务ID，查询任务对象
		Task task = taskService.createTaskQuery()//
					.taskId(taskId)//使用任务ID查询
					.singleResult();
		//获取流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		//查询流程定义的对象
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()//创建流程定义查询对象，对应表act_re_procdef 
					.processDefinitionId(processDefinitionId)//使用流程定义ID查询
					.singleResult();
		return pd;
	}
	
	/**
	 * 二：查看当前活动，获取当期活动对应的坐标x,y,width,height，将4个值存放到Map<String,Object>中
		 map集合的key：表示坐标x,y,width,height
		 map集合的value：表示坐标对应的值
	 */
	@Override
	public Map<String, Object> findCoordingByTask(String taskId) {
		//存放坐标
		Map<String, Object> map = new HashMap<String,Object>();
		//使用任务ID，查询任务对象
		Task task = taskService.createTaskQuery()//
					.taskId(taskId)//使用任务ID查询
					.singleResult();
		//获取流程定义的ID
		String processDefinitionId = task.getProcessDefinitionId();
		//获取流程定义的实体对象（对应.bpmn文件中的数据）
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processDefinitionId);
		//流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		//使用流程实例ID，查询正在执行的执行对象表，获取当前活动对应的流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//创建流程实例查询
					.processInstanceId(processInstanceId)//使用流程实例ID查询
					.singleResult();
		//获取当前活动的ID
		String activityId = pi.getActivityId();
		//获取当前活动对象
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);//活动ID
		//获取坐标
		map.put("x", activityImpl.getX());
		map.put("y", activityImpl.getY());
		map.put("width", activityImpl.getWidth());
		map.put("height", activityImpl.getHeight());
		return map;
	}
	
	/*
	 * 驳回
	 * *
	 * */
	@Override
	public void rejecttoPreTask(String taskId) throws Exception{		  
              Map<String, Object> variables;
              // 取得当前任务
              HistoricTaskInstance currTask = historyService
                              .createHistoricTaskInstanceQuery().taskId(taskId)
                              .singleResult();
              // 取得流程实例
              ProcessInstance instance = runtimeService
                              .createProcessInstanceQuery()
                              .processInstanceId(currTask.getProcessInstanceId())
                              .singleResult();
              if (instance == null) {                     
                      throw new Exception("流程已经结束");
              }
              variables = instance.getProcessVariables();              
              variables.put("processor",this.findLastSubmitInfo(currTask.getId()).getAssignee() );              
              // 取得流程定义
              ProcessDefinitionEntity definition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                              .getDeployedProcessDefinition(currTask
                                              .getProcessDefinitionId());
              if (definition == null) {                      
                      throw new Exception("流程定义未找到");
              }
              // 取得上一步活动
              ActivityImpl currActivity = ((ProcessDefinitionImpl) definition)
                              .findActivity(currTask.getTaskDefinitionKey());
              List<PvmTransition> nextTransitionList = currActivity
                              .getIncomingTransitions();
              // 清除当前活动的出口
              List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
              List<PvmTransition> pvmTransitionList = currActivity
                              .getOutgoingTransitions();
              for (PvmTransition pvmTransition : pvmTransitionList) {
                      oriPvmTransitionList.add(pvmTransition);
              }
              pvmTransitionList.clear();

              // 建立新出口
              List<TransitionImpl> newTransitions = new ArrayList<TransitionImpl>();
              for (PvmTransition nextTransition : nextTransitionList) {
                      PvmActivity nextActivity = nextTransition.getSource();
                      ActivityImpl nextActivityImpl = ((ProcessDefinitionImpl) definition)
                                      .findActivity(nextActivity.getId());
                      TransitionImpl newTransition = currActivity
                                      .createOutgoingTransition();
                      newTransition.setDestination(nextActivityImpl);
                      newTransitions.add(newTransition);
              }
              // 完成任务
              List<Task> tasks = taskService.createTaskQuery()
                              .processInstanceId(instance.getId())
                              .taskDefinitionKey(currTask.getTaskDefinitionKey()).list();
              for (Task task : tasks) {
                      taskService.complete(task.getId(), variables);
//                      historyService.deleteHistoricTaskInstance(task.getId());
              }
              // 恢复方向
              for (TransitionImpl transitionImpl : newTransitions) {
                      currActivity.getOutgoingTransitions().remove(transitionImpl);
              }
              for (PvmTransition pvmTransition : oriPvmTransitionList) {
                      pvmTransitionList.add(pvmTransition);
              }
              
              Task ntask = taskService.createTaskQuery().processInstanceId(instance.getId())  
  	                .singleResult(); 
              if(ntask.getTaskDefinitionKey().equals("start")){
            	  String businessKey=this.findBusinessKeyByTaskId(ntask.getId());
            	  if(!StringUtil.isBlank(businessKey)){
            		  this.billService.setBillStatus(businessKey.split("\\.")[1], "驳回");
            	  }
              }
	}
	
	
	
    /** 
     *  
     * @author: Longjun 
     * @Description: 获取所有下一节点 
     * @date:2016年3月18日 下午4:33:24 
     */
	@Override
	public List<TaskDefinition> nextTaskDefinition(String taskId){ 
        Task task = taskService.createTaskQuery()//
				.taskId(taskId)//使用任务ID查询
				.singleResult();
		//2：获取流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		//3：查询ProcessDefinitionEntiy对象
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
		//使用任务对象Task获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		//使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
					.processInstanceId(processInstanceId)//使用流程实例ID查询
					.singleResult();
		//获取当前活动的id
		String activityId = pi.getActivityId();
		//4：获取当前的活动
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
        return nextTaskDefinition(activityImpl,activityId);
    }
    /** 
     *  
     * @author: Longjun 
     * @Description: 获取所有下一节点 
     * @date:2016年3月18日 下午4:33:24 
     */  
    private List<TaskDefinition> nextTaskDefinition(ActivityImpl activityImpl, String activityId){  
        List<TaskDefinition> taskDefinitionList = new ArrayList<TaskDefinition>();//所有的任务实例  
        List<TaskDefinition> nextTaskDefinition = new ArrayList<TaskDefinition>();//逐个获取的任务实例  
        TaskDefinition taskDefinition = null;  
        if("userTask".equals(activityImpl.getProperty("type")) && !activityId.equals(activityImpl.getId())){  
            taskDefinition = ((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition();  
            taskDefinitionList.add(taskDefinition);  
        }else{  
            List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();  
            List<PvmTransition> outTransitionsTemp = null;  
            for(PvmTransition tr:outTransitions){    
                PvmActivity ac = tr.getDestination(); //获取线路的终点节点    
                //如果是互斥网关或者是并行网关  
                if("exclusiveGateway".equals(ac.getProperty("type"))||"parallelGateway".equals(ac.getProperty("type"))){  
                    outTransitionsTemp = ac.getOutgoingTransitions();  
                    if(outTransitionsTemp.size() == 1){                           
                        nextTaskDefinition = nextTaskDefinition((ActivityImpl)outTransitionsTemp.get(0).getDestination(), activityId);  
                        taskDefinitionList.addAll(nextTaskDefinition);  
                    }else if(outTransitionsTemp.size() > 1){  
                        for(PvmTransition tr1 : outTransitionsTemp){  
                            nextTaskDefinition = nextTaskDefinition((ActivityImpl)tr1.getDestination(), activityId);  
                            taskDefinitionList.addAll(nextTaskDefinition);  
                        }                             
                    }  
                }else if("userTask".equals(ac.getProperty("type"))){  
                    taskDefinition = ((UserTaskActivityBehavior)((ActivityImpl)ac).getActivityBehavior()).getTaskDefinition();  
                    taskDefinitionList.add(taskDefinition);  
                }
            }         
        }  
        return taskDefinitionList;  
    }  
}
