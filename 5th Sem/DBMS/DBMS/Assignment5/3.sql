declare
	cursor emp_cur is
	select * from employee order by basic desc;
	emp_rec employee%rowtype;
	cnt int;
begin
	open emp_cur;
	cnt := 0;
	dbms_output.put_line('EMP_NAME'||'            ' || ' BASIC');
	loop
		fetch emp_cur into emp_rec;
		cnt := cnt + 1;
		DBMS_OUTPUT.put_line(emp_rec.emp_name || ' ' || emp_rec.basic);
		exit when cnt = 4;
	end loop;
end;
/
