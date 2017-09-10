<#if act.fbillno ?exists && act.fbillno ?length gt 0>
	and fbillno like CONCAT('%', :act.fbillno ,'%') 
</#if>
<#if act.fcust_name ?exists && act.fcust_name ?length gt 0>
	and t2.fname like CONCAT('%', :act.fcust_name ,'%') 
</#if>
<#if act.fproject_name ?exists && act.fproject_name ?length gt 0>
	and t1.fname like CONCAT('%', :act.fproject_name ,'%') 
</#if>
<#if act.fremark ?exists && act.fremark ?length gt 0>
	and t0.fremark like CONCAT('%', :act.fremark ,'%') 
</#if>
<#if act.fstatus ?exists && act.fstatus ?length gt 0 && act.fstatus!="empty">
	and ifnull(t0.fstatus,'')=''
</#if>
<#if act.fapplicant_date_begin ?exists >
	and t0.fapplicant_date >= :act.fapplicant_date_begin
</#if>
<#if act.fapplicant_date_end ?exists >
	and t0.fapplicant_date <=:act.fapplicant_date_end
</#if>


