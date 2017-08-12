package org.jeecgframework.web.activiti.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.activiti.service.IBillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("offerBillService")
@Transactional
public class OfferBillServiceImpl extends CommonServiceImpl  implements IBillService {

	@Override
	public void setBillStatus(String id, String status) {
		// TODO 自动生成的方法存根
		String sql="update t_offers set fstatus=? where id=?";
		this.commonDao.executeSql(sql,status,id);
	}


}
