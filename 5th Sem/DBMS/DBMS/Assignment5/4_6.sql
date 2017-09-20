DECLARE

  dt char(9);

  DT_ char(3);

  basic number;

  Y number;

  Z number;

BEGIN

  Select TO_CHAR(sysdate, 'MON-DD-YY') into dt from dual;

  select add_months(sysdate, 1)-sysdate into Z from dual;

  --DBMS_OUTPUT.PUT_LINE('date : '||dt);

  DT_:=substr(dt,0,3);

  DBMS_OUTPUT.PUT_LINE('date : '||dt);
	DBMS_OUTPUT.PUT_LINE(DT_);
  select S.BASIC,Q.NO_OF_DAYS into basic,Y from EMPLOYEE S,LEAVE Q where Q.MONTH=DT_ and Q.EMP_NO=S.EMP_CODE; 

  DBMS_OUTPUT.PUT_LINE('basic '||basic||' '||'No of days '||Y);

 basic:=basic-(basic*Y)/Z;

 DBMS_OUTPUT.PUT_LINE(basic);

END;

/
