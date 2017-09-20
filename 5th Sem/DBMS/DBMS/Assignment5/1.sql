---accept x char prompt 'Enter an emp code: '
declare 
	a char(5);
	name varchar(20);
begin
	a := '&emp_code';
	select emp_name into name from employee where emp_code = a;
	dbms_output.put_line('Name: '||name);
exception
	when no_data_found then
		dbms_output.put_line('NO such employee');
end;
/
