set serveroutput on;
declare
	code char(5);
        name varchar(20);
        dpt_code char(5);
        desg_code char(5);
        g char(1);
        addrs char(25);
        city char(20);
        state char(20);
        pin char(6);
        basic number;
        jn date;
        x number;
        y number;
        dup_emp_code exception;
        no_dept_code exception;
--query 1
declare 
	code employee.emp_code%type;
	name employee.emp_name%type;
begin
	code := '&emp_code';
	select emp_name into name from employee where emp_code = code;
	dbms_output.put_line(name);
end;
/
---query 2	
begin
        code := '&emp_code';
        dpt_code := '&dept_code';
        select count(*) into x from department where dept_code = dpt_code;
        select count(*) into y from employee where emp_code = code;

        if x = 0 then
                raise no_dept_code;
        end if;
        if y > 0 then
                raise dup_emp_code;
        else
                insert into employee values(code, name, dpt_code, desg_code, g, addrs, city, state, pin, basic, jn);
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
---select emp_name, rank from (select emp_name, row_number() over (order by basic) as rank from employee) where rank <= 10;
---query 3
declare
	cursor emp_cur is
	select emp_name, basic from employee order by basic desc;
	emp_rec employee%rowtype;
	cnt int;
begin
	open emp_cur;
	cnt := 0;
	loop
		fetch emp_cur into emp_rec;
		cnt := cnt + 1
		DBMS_OUTPUT.put_line(emp_rec.emp_name || ' ' || emp_rec.basic);
		exit when cnt > 5;
	end loop
end;
/

---query 4
begin
	code := '&dept_code';
	select count(*) into x from department where dept_code = code;
	if x = 0 then
		delete from employee where dept_code = code;
	else
		raise no_dept_code;
	end if;
	dbms_output.put_line('Number of rows deleted: ', x);
end;
/

---query 6
declare 
	cursor leave_cur is
	select emp_code, day_of_leave, month from leave;
	leave_rec leave%rowtype;
begin
	open leave_cur;
	loop
		fetch leave_cur into leave_rec;
		dbms_output.put_line(basic - basic*no_of_day/);
		exit when leave_cur%notfound;
	end loop
end;
/
	

	
