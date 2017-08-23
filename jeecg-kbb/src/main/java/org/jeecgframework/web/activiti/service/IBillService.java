package org.jeecgframework.web.activiti.service;

import java.util.List;
import java.util.Map;

import org.jeecgframework.web.activiti.entity.ProcessorEntity;

/*
 * 单据操作接口
 * */
public interface IBillService {
	void setBillStatus(String id ,String status);	
	
	List<Map<String,Object>> findForJdbc(String sql,Object...param);

	List<ProcessorEntity> getNextprocessor(String userTask);
	
	void setBillCurrentApprover(String id,String approver);

	String getBillNo(String billId);
	
	Map<String,Object> getBillFieldValue(String billId,String fieldname);
}


