set serveroutput on;
declare 
	y number;
	x number;
	emp_rec employee%rowtype;
	dup_emp_code exception;
	no_dept_code exception;	

begin
	emp_rec.emp_code := '&emp_code';
	emp_rec.emp_name := '&emp_name';
	emp_rec.dept_code := '&dept_code';
	emp_rec.basic := '&basic';
	emp_rec.desig_code := '&desig_code';
	emp_rec.gender := '&gender';
	select count(*) into x from department where dept_code = emp_rec.dept_code;
	select count(*) into y from employee where emp_code = emp_rec.emp_code;

	if x = 0 then
		raise no_dept_code;
	end if;
	if y > 0 then
		raise dup_emp_code;
	else
		insert into employee values emp_rec; 
	end if;
exception
	when no_data_found then
		dbms_output.put_line('No data found');
	when dup_emp_code then
		dbms_output.put_line('Dup emp code found');
	when no_dept_code then
		dbms_output.put_line('No such dept code found');
end;
/
