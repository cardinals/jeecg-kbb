SELECT t0.id,t0.fbillno,t0.fcustid,t0.fprojectid,t2.fname as fcust_name,
 t0.fremark,t0.famount,t0.fstatus,t0.fapplicant,t0.fapplicant_date,t0.fcurrent_approver ,t0.fdiscountrate,t0.fafteramount
 FROM t_offers t0 
 inner join t_base_customer t2 on t0.fcustid=t2.id
 where 1=1
<#include "WxOfferDao_condition.sql">
<#if filter ?exists && filter ?length gt 0>
	and t0.fapplicant in (:filter)
</#if>
order by fbillno desc