insert into 
	t_offers
      ( id ,fremark ,fbillno ,fcustid ,famount ,fstatus ,fapplicant ,fapplicant_date ,fprojectid ,fcurrent_approver ) 
values
      ('${act.id}',
       :act.fremark,
       :act.fbillno,
	   :act.fcustid,
   	   :act.famount,
   	   :act.fstatus,
       :act.fapplicant,
	   :act.fapplicant_date,
   	   :act.fprojectid,
   	   :act.fcurrent_approver
      )