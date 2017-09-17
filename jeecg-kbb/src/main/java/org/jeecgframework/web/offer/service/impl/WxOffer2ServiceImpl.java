package org.jeecgframework.web.offer.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.offer.entity.WxOffer2Entry;
import org.jeecgframework.web.offer.service.WxOffer2Service;
import org.jeecgframework.web.system.util.DbReaderUtil;
import org.springframework.stereotype.Service;

@Service("wxOffer2Service")
public class WxOffer2ServiceImpl extends CommonServiceImpl implements WxOffer2Service {

	@Override
	public List<WxOffer2Entry> getOffer2Entry(String offerId, String type) {
		List<Map<String,Object>> rs=this.commonDao.findForJdbc(
				"select t1.id,t1.fofferid,t1.findex,t1.fitemid,t1.fmodel,t1.fbranch,t1.funit,t1.fqty,t1.fprice,t1.famount,t1.fremark,t1.famount,t1.fdetail2json,t1.ftype,"
+"t2.fname as fitemname,t2.fnumber as fitemnumber from t_offer2_entry t1 inner join  t_doors t2 on t1.fitemid=t2.id where t1.ftype=? and t1.fofferid=?",type, offerId);
		List<WxOffer2Entry> result=new ArrayList<WxOffer2Entry>();
		Iterator<Map<String,Object>> it = rs.iterator();
		while(it.hasNext()) {
		   Map<String,Object> dr=it.next();
		   WxOffer2Entry entry=new WxOffer2Entry();
		   entry.setId(DbReaderUtil.readString(dr.get("id")));
		   entry.setFitemid(DbReaderUtil.readString(dr.get("fitemId")));
		   entry.setFmodel(DbReaderUtil.readString(dr.get("fmodel")));		   
		   entry.setFunit(DbReaderUtil.readString(dr.get("funit")));
		   entry.setFbranch(DbReaderUtil.readString(dr.get("fbranch")));
		   entry.setFdetail2json(DbReaderUtil.readString(dr.get("fdetail2json")));
		   entry.setFofferid(DbReaderUtil.readString(dr.get("fofferid")));
		   entry.setFremark(DbReaderUtil.readString(dr.get("fremark")));
		   entry.setFtype(DbReaderUtil.readString(dr.get("ftype")));
		   entry.setFitemnumber(DbReaderUtil.readString(dr.get("fitemnumber")));
		   entry.setFitemname(DbReaderUtil.readString(dr.get("fitemname")));
		   
		   entry.setFqty(DbReaderUtil.readBigDecimal(dr.get("fqty")));
		   entry.setFprice(DbReaderUtil.readBigDecimal(dr.get("fprice")));
		   entry.setFamount(DbReaderUtil.readBigDecimal(dr.get("famount")));
		   entry.setFindex(DbReaderUtil.readInteger(dr.get("findex")));
		   result.add(entry);		   
		}
		return result;
	}

}
