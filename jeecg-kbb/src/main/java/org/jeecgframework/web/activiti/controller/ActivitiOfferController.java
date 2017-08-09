package org.jeecgframework.web.activiti.controller;

import org.jeecgframework.core.common.controller.BaseController;
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
	
	@RequestMapping(params="deploymentProcessDefinition",method = RequestMethod.GET)	
	public void deploymentProcessDefinition(){
		workflowService.deploymentProcessDefinition("activiti/offer/OfferProcess");
	}

	
	@RequestMapping(params="submitTask",method = RequestMethod.POST)	
	public String submitTask(String id){
		return "OK";
	}
}
