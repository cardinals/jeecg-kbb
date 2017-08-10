package org.jeecgframework.web.activiti.service.impl;

import org.jeecgframework.web.activiti.service.IBillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("offerBillService")
@Transactional
public class OfferBillServiceImpl implements IBillService {

	@Override
	public void setBillStatus(String id, String status) {
		// TODO 自动生成的方法存根
		
	}

}
