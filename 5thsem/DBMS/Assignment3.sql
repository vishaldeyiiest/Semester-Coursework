create table dept
(
	dept_code char(5) primary key,
	dept_name varchar(20)
);
create table emp
(
	EMP_CODE char(5) primary key,
	EMP_NAME char(20),
	DEPT_CODE char(5),
	GENDER char(1),
	ADDRESS char(25),
	CITY char(20),
	BASIC Number,
	JN_DT Date default sysdate,
	GRADE char(1),
	constraint chkcase check (EMP_NAME = upper(EMP_NAME)),
	constraint check_basic check (BASIC between 5000 and 9000),
	constraint check_grade check (GRADE in ('A','B','C')),
	constraint fk foreign key (DEPT_CODE) references dept(DEPT_CODE) 
);
create table leave
(
	LEAVE_CODE char(5) primary key,
	EMP_CODE char(5),
	LEAVE_TYPE char(2),
	FROM_DT date,
	TO_DT date,
	foreign key (EMP_CODE) references emp(EMP_CODE) on delete cascade
);
insert into dept values('01', 'personnel'),('02', 'production'),('03','purchase'),('04','Finance');
insert into leave values('01','006','CL','30-aug-2016', '02-sep-2016');
insert into leave values('02','006','ML','02-aug-2016', '04-sep-2016');
insert into leave values('03','001','CL','30-aug-2016', '02-sep-2016');
insert into leave values('04','002','ML','30-aug-2016', '02-sep-2016');
insert into leave values('05','003','ML','30-aug-2016', '02-sep-2016');
insert into leave values('06','004','CL','30-aug-2016', '02-sep-2016');
insert into leave values('07','005','CL','30-aug-2016', '02-sep-2016');
insert into emp(emp_code, emp_name, dept_code, address, city, basic, grade) values ('001','VISHAL','01','iiest','Howrah',6000,'A');
insert into emp(emp_code, emp_name, dept_code, address, city, basic, grade) values ('003','AMIT','03','iiest','Howrah',5000,'A');
insert into emp(emp_code, emp_name, dept_code, address, city, basic, grade) values ('004','SAPTARSHI','01','iiest','Howrah',5000,'B');
insert into emp(emp_code, emp_name, dept_code, address, city, basic, grade) values ('002','TRISHNENDU','03','iiest','Howrah',5000,'A');
insert into emp(emp_code, emp_name, dept_code, address, city, basic, grade) values ('005','SOHAM','01','Barasat','Kolkata',5500,'A');
insert into emp(emp_code, emp_name, dept_code, address, city, basic, grade) values ('006','MILAN','01','iiest','Kolkata',5500,'A');

/*
select emp_name from employee e where desig_code = 'man' and basic > (select avg(basic) from employee e1 where e.dept_code = e1.dept_code)
and (select count(*) from employee e2 where e2.dept_code = e.dept_code) >= 2; 

select emp_name,basic,dept_name from employee e,designation d,department 
where desig_desc = 'manager' and e.desig_code = d.desig_code 
and e.dept_code = department.dept_code
and basic > (select avg(basic) from employee e1 where e.dept_code = e1.dept_code)
and (select count(*) from employee e2 where e2.dept_code = e.dept_code) >= 2; 
*/
create view dept_view as 
select emp_code, emp_name, dept_code, basic from emp where dept_code = '01';

insert into dept_view values('006', 'NIL', '01', 5000);
insert into dept_view values('007', 'POOJA', '05', 6000);		--parent key not found error

update dept_view set basic = basic + 100;		--parent table gets updated

delete dept_view where emp_code = '006';	

create view view_leave_emp as 
select emp.emp_code, emp_name, dept_name, basic, leave_type, from_dt, to_dt from emp, dept, leave 
where emp.dept_code = dept.dept_code and emp.emp_code = leave.emp_code;
/*
create view view_ as 
select emp.emp_code, dept.dept_code, emp_name, dept_name, basic, leave_code, leave_type, from_dt, to_dt from emp, dept, leave 
where emp.dept_code = dept.dept_code and emp.emp_code = leave.emp_code;
insert into view_ values('008','03','SUBHAM','purchase',5000,'09','CL','30-aug-2016','03-sep-2016');
*/

insert into view_leave_emp values('008','SUBHAM','purchase',5000,'CL','30-aug-2016','03-sep-2016');
	---cannot modify a column which maps to a non key-preserved table

update view_leave_emp set basic = basic + 100;		--- cannot modify a column which maps to a non key-preserved table

create table emp1 as select emp_code, emp_name, dept_name, basic from emp e, dept d
where e.dept_code = d.dept_code and dept_name = 'personnel' and basic = 7000;
	
insert into emp1 select emp_code, emp_name, dept_name, basic from emp e, dept d where e.dept_code = d.dept_code and basic >= 7000;

alter table emp1 add netpay int;
update emp1 set netpay = basic*1.5; 
alter table emp1 drop column netpay;
