<#if act.fbillno ?exists && act.fbillno ?length gt 0>
	and fbillno like CONCAT('%', :act.fbillno ,'%') 
</#if>
