SELECT DEPT_CODE, min(BASIC), max(BASIC), avg(BASIC) from EMPLOYEE group by DEPT_CODE;

SELECT DEPT_CODE, count(*) FEM_COUNT from EMPLOYEE where GENDER = 'F' group by DEPT_CODE;

SELECT CITY, E.DEPT_CODE, DEPT_NAME count(E.EMP_CODE) COUNT_EMP from EMPLOYEE E,DEPARTMENT D 
where E.DEPT_CODE = D.DEPT_CODE group by E.DEPT_CODE, CITY; 

SELECT DESIG_CODE, count(EMP_CODE) Count_EMP from EMPLOYEE where JN_DT LIKE '%2000' group by DESIG_CODE order by Count_EMP;

SELECT DEPT_CODE, sum(BASIC) total from EMPLOYEE where GENDER = 'M' group by DEPT_CODE having total > 6000 order by total DESC;

SELECT EMP_NAME, DESIG_DESC, BASIC from EMPLOYEE E, DESIGNATION D where E.DESIG_CODE = D.DESIG_CODE;

SELECT EMP_NAME, DESIG_DESC, DEPT_NAME, BASIC from EMPLOYEE E, DESIGNATION D, DEPARTMENT DT 
where E.DESIG_CODE = D.DESIG_CODE and E.DEPT_CODE = DT.DEPT_CODE;

SELECT DEPT_NAME from DEPARTMENT D where (SELECT count(*) from EMPLOYEE E where E.DEPT_CODE = D.DEPT_CODE) = 0;

SELECT DEPT_NAME from DEPARTMENT D where (SELECT count(*) from EMPLOYEE E where E.DEPT_CODE = D.DEPT_CODE) >= 1;

SELECT DEPT_NAME from DEPARTMENT D where (SELECT count(*) from EMPLOYEE E where E.DEPT_CODE = D.DEPT_CODE) >= 10;

SELECT DEPT_NAME from DEPARTMENT D , EMPLOYEE E1 where BASIC = (SELECT max(BASIC) from EMPLOYEE E2) and D.DEPT_CODE = E1.DEPT_CODE;

SELECT DESIG_DESC from DESIGNATION D, EMPLOYEE E where BASIC = (SELECT max(BASIC) from EMPLOYEE) and D.DESIG_CODE = E.DESIG_CODE;

SELECT DEPT_CODE, count(*) from EMPLOYEE where DESIG_CODE = 'man' group by DEPT_CODE;

SELECT BASIC from EMPLOYEE where BASIC >= ALL(SELECT distinct BASIC from EMPLOYEE);

SELECT BASIC from EMPLOYEE where BASIC <= ALL(SELECT distinct BASIC from EMPLOYEE);

with temp as
(
select d.dept_code, sum(basic) total from employee e, department d where e.dept_code = d.dept_code group by d.dept_code 
having sum(BASIC) >= all(select sum(basic) from employee group by employee.dept_code)
)
select dept_name, department.dept_code, temp.total from department, temp where department.dept_code = temp.dept_code; 

with temp as
(
select d.dept_code, avg(BASIC) AVERAGE from employee e, department d where e.depT_code = d.dept_code group by d.dept_code 
having avg(BASIC) >= all(select avg(basic) from employee group by employee.dept_code)
)
select department.dept_name, department.dept_code, temp.AVERAGE from department, temp where department.dept_code = temp.dept_code; 

with temp as
(
select d.dept_code, count(e.EMP_CODE) count_emp from employee e, department d where e.depT_code = d.dept_code group by d.dept_code 
having count(*) >= all(select count(*) from employee group by employee.dept_code)
)
select department.dept_name, department.dept_code, temp.count_emp from department, temp where department.dept_code = temp.dept_code; 

/*
with temp as (select dept_name, sum(basic) total from employee e inner join department d on e.dept_code = d.dept_code group by d.dept_code)
select * from temp where total = (select max(total) from temp);
*/

insert into employee values('012', 'ishita', '07', 'clk', 'F', 'Belgharia', 'Kolkata', 'West Bengal', 3500,700013, '09-jun-2010');
select * from employee where desig_code not in (select desig_code from designation);

select emp_name, basic, dept_code from employee e1 where gender = 'F' and BASIC > (select avg(basic) from employee e2 where e1.dept_code = e2.dept_code);

SELECT count(*) FEM_MAN from EMPLOYEE where GENDER ='F' and DESIG_CODE = 'man';

/*
with temp as (SELECT * from EMPLOYEE inner join DEPARTMENT on EMPLOYEE.DEPT_CODE = DEPARTMENT.DEPT_CODE)
  Select temp.dept_name, sum(temp.BASIC) from temp group by temp.DEPT_CODE having sum(temp.BASIC) >= all(select sum(BASIC) from temp)
*/

