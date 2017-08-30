UPDATE t_offers 
SET 
	<#if act.id ?exists && act.id ?length gt 0>
	    id = :act.id
	</#if>
	<#if act.fbillno ?exists && act.fbillno ?length gt 0>
	    ,fbillno = :act.fbillno
	</#if>
	<#if act.fprojectid ?exists && act.fprojectid ?length gt 0>
	    ,fprojectid = :act.fprojectid
	</#if>
	<#if act.fcustid ?exists && act.fcustid ?length gt 0>
	    ,fcustid = :act.fcustid
	</#if>
	<#if act.famount ?exists && act.famount ?length gt 0>
	    ,famount = :act.famount
	</#if>
	<#if act.fstatus ?exists && act.fstatus ?length gt 0>
	    ,fstatus = :act.fstatus
	</#if>	
	<#if act.fcurrent_approver ?exists && act.fcurrent_approver ?length gt 0>
	    ,fcurrent_approver = :act.fcurrent_approver
	</#if>	
	<#if act.fapplicant ?exists && act.fapplicant ?length gt 0>
	    ,fapplicant = :act.fapplicant
	</#if>	
	<#if act.fapplicant_date ?exists>
	    ,fapplicant_date = :act.fapplicant_date
	</#if>	
	<#if act.fremark ?exists && act.fremark ?length gt 0>
		,fremark = :act.fremark
	</#if>
WHERE id = :act.id