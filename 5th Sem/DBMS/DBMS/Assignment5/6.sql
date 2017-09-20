declare 
	cursor leave_cur is
	select emp_code, (from_dt-to_dt+1) no_of_days from leave;
	leave_rec leave%rowtype;
	days int;
	temp_basic number;
begin
	select 1 + trunc(last_day(sysdate)) - trunc(sysdate, 'MM') into days from (select sysdate from dual);
	---open leave_cur;
	for leave_rec in leave_cur
		loop
			select basic into temp_basic from employee where employee.emp_code = leave_rec.emp_code;
			dbms_output.put_line(temp_basic - temp_basic * leave_rec.no_of_days / days);
		end loop;
end;
/
