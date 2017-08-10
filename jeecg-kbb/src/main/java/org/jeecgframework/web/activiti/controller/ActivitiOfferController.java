package org.jeecgframework.web.activiti.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.task.Task;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.web.activiti.entity.WorkflowBean;
import org.jeecgframework.web.activiti.service.IBillService;
import org.jeecgframework.web.activiti.service.IWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
	public String submitTask(HttpServletRequest request, HttpServletResponse response){	
		try{
			String id=request.getParameter("id");
			WorkflowBean workflowBean=new WorkflowBean();
			workflowBean.setBillType("Offer");
			workflowBean.setId(id);
			workflowService.setBillService(offerBillService);
			workflowService.saveStartProcess(workflowBean);
			return "OK";
		}catch(Exception e){
			System.out.println(e.toString());
			return e.getMessage();
		}
	}
	
	@RequestMapping(params="findTaskList",method = RequestMethod.GET)	
	public String findTaskList(HttpServletRequest request, HttpServletResponse response){
		workflowService.setBillService(offerBillService);
		String name=request.getParameter("name");
		List<Task> list=workflowService.findTaskListByName(name);
		
		return "";
	}
}
