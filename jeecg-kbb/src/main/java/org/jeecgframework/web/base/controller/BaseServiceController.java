package org.jeecgframework.web.base.controller;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.web.base.service.BaseServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/baseServiceController")
public class BaseServiceController extends BaseController {
	@Autowired
	private BaseServiceI baseService;
	@RequestMapping(params = "getStandardBillNo")
	@ResponseBody
	public AjaxJson getStandardBillNo(){
		AjaxJson j = new AjaxJson();	
		String fnumber= baseService.getBillNo("t_base_standard");
		j.setMsg(fnumber);
		j.setSuccess(true);
		return j;
	}
}
