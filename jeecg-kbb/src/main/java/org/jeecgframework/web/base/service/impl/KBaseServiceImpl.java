package org.jeecgframework.web.base.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.base.entity.BillRuleEntity;
import org.jeecgframework.web.base.service.KBaseServiceI;
import org.jeecgframework.web.system.pojo.base.TSNotice;
import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityUser;
import org.jeecgframework.web.system.pojo.base.TSNoticeReadUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.NoticeAuthorityUserServiceI;
import org.jeecgframework.web.system.service.NoticeService;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.sms.entity.TSSmsEntity;
import org.jeecgframework.web.system.sms.service.TSSmsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("baseService")
@Transactional
public class KBaseServiceImpl extends CommonServiceImpl  implements KBaseServiceI {
	@Autowired
	private SystemService systemService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private NoticeAuthorityUserServiceI noticeAuthorityUserService;
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
		Map<String,Object> map=this.commonDao.findOneForJdbc("select * from t_config_billno where ftablename=?", tablname);
		 BillRuleEntity entity=new BillRuleEntity();		
		 entity.setFnum(Integer.parseInt(map.get("fnum").toString()));
		 entity.setFrule(map.get("frule").toString());			
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
	public void addNotice(String realName,String title,String content){
		TSNotice notice=new TSNotice();
		notice.setNoticeTitle(title);
		notice.setNoticeContent(content);
		notice.setNoticeType("1");
		notice.setNoticeLevel("3");
		notice.setNoticeTerm(null);
		notice.setCreateUser("系统");
		notice.setCreateTime(new Date());
		notice.setIsRead("0");
		Serializable noticeSerializable=noticeService.save(notice);
		
//		<script type="text/javascript">$.ajax({url: "activitiOffer.do?showWorkflow&businesskey=Offer.7419818A111341AF80CE895C86C1892B",  type: "get",  dataType: "html",  success: function (result) {$('.form:last').html(result);}});</script>
		
		String noticeId=noticeSerializable.toString();
		String userId=getUserIdbyRealName(realName);
		TSNoticeReadUser noticeRead = new TSNoticeReadUser();
		noticeRead.setNoticeId(noticeId);
		noticeRead.setUserId(userId);
		noticeRead.setCreateTime(new Date());
		systemService.save(noticeRead);
		
		TSNoticeAuthorityUser noticeAuthorityUser=new TSNoticeAuthorityUser();
		noticeAuthorityUser.setNoticeId(noticeId);
		TSUser tsUser=new TSUser();
		tsUser.setId(userId);
		noticeAuthorityUser.setUser(tsUser);
		noticeAuthorityUserService.save(noticeAuthorityUser);
	}
	
	@Override
	public String getUserIdbyRealName(String realName){
		Map<String,Object> map=this.commonDao.findOneForJdbc("select id from t_s_base_user where realname=? ", realName);
		return map.get("id").toString();
	}
	@Override
	public String getUserRole(String userId){		
		Map<String,Object> map=this.commonDao.findOneForJdbc("select t1.rolecode from t_s_role_user t0 inner join t_s_role t1 on t0.roleid=t1.id  where userid=? ", userId);
		return map.get("rolecode").toString();
	}
}
