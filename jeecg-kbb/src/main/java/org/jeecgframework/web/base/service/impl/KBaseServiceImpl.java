package org.jeecgframework.web.base.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.base.entity.BillRuleEntity;
import org.jeecgframework.web.base.service.KBaseServiceI;
import org.jeecgframework.web.system.sms.entity.TSSmsEntity;
import org.jeecgframework.web.system.sms.service.TSSmsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("baseService")
@Transactional
public class KBaseServiceImpl extends CommonServiceImpl  implements KBaseServiceI {
	@Autowired
	public TSSmsServiceI tSSmsService;
	
	
	@Override
	public String getBillNo(String tableName){
		BillRuleEntity wxBillNoRule=getBillRule(tableName);
		String strRule="";
		if(wxBillNoRule.getFrule()!=null){
			strRule=wxBillNoRule.getFrule();
			if(strRule.indexOf("[")>-1 && strRule.indexOf("]")>-1){
				String strFormat=strRule.substring(strRule.indexOf("[")+1, strRule.indexOf("]"));
				strRule=strRule.replace("["+strFormat+"]", formateDate(strFormat));
			}
			String strNum=wxBillNoRule.getFnum().toString();
			strRule=strRule.substring(0, strRule.length()-strNum.length())+strNum;
		}else{
			strRule=wxBillNoRule.getFnum().toString();
		}
		updateBillRule(tableName);
		return strRule;
	}
	
	BillRuleEntity getBillRule(String tablname){
		List<Map<String,Object>> rs=this.commonDao.findForJdbc("select * from t_config_billno where ftablename=?", tablname);
		 BillRuleEntity entity=new BillRuleEntity();
		 Iterator<Map<String,Object>> it = rs.iterator();
		 while(it.hasNext()) {
			 Map<String,Object> dr= it.next();
			 entity.setFnum(Integer.parseInt(dr.get("fnum").toString()));
			 entity.setFrule(dr.get("frule").toString());
			 break;
		 }
		return entity;
	}
	void updateBillRule(String tablename){
		this.commonDao.executeSql("update t_config_billno set fnum=fnum+1 where ftablename=?", tablename);
	}
	
	public static String formateDate(String strFormat) {
		 Date currentTime = new Date();
		   SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
		   return formatter.format(currentTime);
		}
	
	@Override
	public void sendMessage(String user,String msgType,String msg){
		
		TSSmsEntity tSSms=new TSSmsEntity();
		tSSmsService.save(tSSms);
		
	}
}
