package org.jeecgframework.web.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.web.base.service.KBaseServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/baseServiceController")
public class BaseServiceController extends BaseController {
	@Autowired
	private KBaseServiceI baseService;	
	
	@RequestMapping(params = "getNo")
	@ResponseBody
	public String getNo(HttpServletRequest request){	
		String fnumber= baseService.getBillNo(request.getParameter("tableName"),true);	
		return fnumber;
	}
}
