package org.jeecgframework.web.activiti.controller;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.web.activiti.entity.ActivitiTaskEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/activitiOffer")
public class ActivitiOfferController extends BaseController {
	
	@RequestMapping(params="submitTask",method = RequestMethod.POST)	
	public String submitTask(String task){
		return "OK";
	}
}
