DECLARE
  d1 DATE;
  d2 DATE;
BEGIN
  d1:='&order_date_1';
  d2:='&order_date_2';
  dbms_output.put_line('The delivery details are as follows:');
  /*FOR delv IN (SELECT om.order_no, om.order_dt,od.item_no, od.qty
FROM ORDERMAST om,ORDERDETAILS od
WHERE om.order_dt BETWEEN d1 and d2 and om.order_no = od.order_no and om.order_no NOT IN (select dm.order_no from DELIVERYMAST dm))
  LOOP
    dbms_output.put_line('Order No.:'||delv.order_no||',Order Date:'||delv.order_dt||',Item No.:'||delv.item_no||',Quantity:'||delv.qty);
  END LOOP;*/
  FOR delv IN (SELECT om.order_no, om.order_dt,od.item_no, od.qty
FROM ORDERMAST om,ORDERDETAILS od, DELIVERYMAST dm, DELIVERY_DETAILS dd
WHERE om.order_dt BETWEEN d1 and d2 and om.order_no = od.order_no and om.order_no = dm.order_no and dm.delv_no = dd.delv_no and od.item_no NOT IN (SELECT dd.item_no from DELIVERYMAST dm, DELIVERY_DETAILS dd where dm.delv_no=dd.delv_no and dm.order_no=od.order_no))
  LOOP
    dbms_output.put_line('Order No.:'||delv.order_no||',Order Date:'||delv.order_dt||',Item No.:'||delv.item_no||',Quantity:'||delv.qty);
  END LOOP;
  FOR delv IN (SELECT om.order_no, om.order_dt,od.item_no, od.qty - dd.qty qty
FROM ORDERMAST om,ORDERDETAILS od, DELIVERYMAST dm, DELIVERY_DETAILS dd
WHERE om.order_dt BETWEEN d1 and d2 and om.order_no = od.order_no and om.order_no = dm.order_no and dm.delv_no = dd.delv_no and od.item_no = dd.item_no and od.qty > dd.qty)
  LOOP
    dbms_output.put_line('Order No.:'||delv.order_no||',Order Date:'||delv.order_dt||',Item No.:'||delv.item_no||',Quantity:'||delv.qty);
  END LOOP;
END;
/
	
