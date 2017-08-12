package org.jeecgframework.web.activiti.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.jeecgframework.web.activiti.entity.WorkflowBean;



public interface IWorkflowService{

	void saveNewDeploye(File file, String filename);

	List<Deployment> findDeploymentList();

	List<ProcessDefinition> findProcessDefinitionList();

	InputStream findImageInputStream(String deploymentId, String imageName);

	void deploymentProcessDefinition(String fileUrl);
	
	void deleteProcessDefinitionByDeploymentId(String deploymentId);

	String saveStartProcess(WorkflowBean workflowBean) throws Exception;

	List<Task> findTaskListByName(String name);

	String findTaskFormKeyByTaskId(String taskId);

//	LeaveBill findLeaveBillByTaskId(String taskId);

	List<String> findOutComeListByTaskId(String taskId);

	void saveSubmitTask(WorkflowBean workflowBean);

	List<Comment> findCommentByTaskId(String taskId);

	List<Comment> findCommentByLeaveBillId(Long id);

	ProcessDefinition findProcessDefinitionByTaskId(String taskId);

	Map<String, Object> findCoordingByTask(String taskId);

	void setBillService(IBillService offerBillService);

	String findBusinessKeyByTaskId(String taskId);

	public void rejecttoPreTask(String taskId) throws Exception;

}
