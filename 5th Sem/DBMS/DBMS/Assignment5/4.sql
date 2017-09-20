declare
	code department.dept_code%type;
	x int;
	no_dept_code exception;
begin
	code := '&dept_code';
	select count(*) into x from department where dept_code = code;
	if x = 0 then
		raise no_dept_code;
	end if;
	delete from employee where dept_code = code;
	dbms_output.put_line('Number of rows deleted: '||x);
exception
	when no_dept_code then
		dbms_output.put_line('No such dept code');
end;
/

