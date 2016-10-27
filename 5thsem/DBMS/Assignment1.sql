CREATE TABLE EMPLOYEE
(
	EMP_CODE char(5) PRIMARY KEY,
	EMP_NAME char(20),
	DEPT_CODE char(5),
	DESIG_CODE char(5),
	GENDER char(1),
	ADDRESS char(25),
	CITY char(20),
	STATE char(20),
	BASIC Number,
	PIN char(6),
	JN_DT Date
);

CREATE TABLE DESIGNATION
(
	DESIG_CODE char(5) Primary key, 
	DESIG_DESC char(20)
);

CREATE TABLE DEPARTMENT
(
	DEPT_CODE char(5) Primary key,
	DEPT_NAME char(15)
);
insert into DEPARTMENT values('01', 'personnel'),('02', 'production'),('03','purchase'),('04','finance'),('05','research');

insert into DESIGNATION values('man','Manager'),('exe', 'Executive'),('off', 'officer'),('clk', 'clerk'),('hlp', 'helper');

INSERT into EMPLOYEE values(&EMP_CODE, &EMP_NAME, &DEPT_CODE, &DESIG_CODE, &GENDER, &ADDRESS, &CITY, &STATE, &PIN, &BASIC, &JN_DT);
insert into EMPLOYEE values
('001', 'Anita', '05', 'man', 'F', 'Shibpur', 'Howrah', 'West Bengal',6000, 711101, '17-jun-2015');
insert into EMPLOYEE values
('002', 'Anand', '01','clk', 'M', 'Bali', 'Howrah', 'West Bengal', 5000,711121, '17-jun-2003');
insert into EMPLOYEE values
('003', 'Vishal', '01','man', 'M', 'Ranaghat Rathtala', 'Ranaghat', 'West Bengal', 7000, 741254, '07-jan-2000');
insert into EMPLOYEE values
('004', 'Trishnendu', '01','man', 'M', 'Beleghata', 'Kolkata', 'West bengal',5500, 700054, '17-jun-2000');
insert into EMPLOYEE values
('005', 'Arnab', '04','man', 'M', 'Kharagpur', 'Kharagpur', 'West Bengal', 4000, 721254, '17-jan-2005');
insert into EMPLOYEE values
('006', 'Soham', '01','exe', 'M', 'Barasat', 'Kolkata', 'West bengal',4500, 700154, '17-jun-2010');
insert into EMPLOYEE values
('007', 'Nilanajana', '02','man', 'F', 'Kalyani', 'Kalyani', 'West Bengal', 7000, 731252, '07-dec-2010');
insert into EMPLOYEE values
('008', 'Pooja', '03','man', 'F', 'Agra Cantt', 'Agra', 'Uttar Pradesh', 6200, 400054, '17-jul-2007');
insert into EMPLOYEE values
('009', 'Subham', '02','exe', 'M', 'Raiganj', 'Raiganj', 'West Bengal', 5500, 751254, '07-oct-2005');
insert into EMPLOYEE values
('010', 'Aman', '01','hlp', 'M', 'B.garden', 'Howrah', 'West bengal', 5500, 700054, '17-jun-2014');
insert into EMPLOYEE values
('011', 'Neela', '03','exe', 'F', 'Mumbai Port', 'Mumbai', 'Maharashtra', 5200, 500054, '17-jul-2006');

				 
SELECT * from EMPLOYEE WHERE DEPT_CODE is NULL;

SELECT * from EMPLOYEE WHERE BASIC = 0;

SELECT * from EMPLOYEE WHERE BASIC is NULL;

SELECT avg(BASIC) from EMPLOYEE;

UPDATE EMPLOYEE set BASIC = 0 where BASIC is NULL;

SELECT avg(BASIC) from EMPLOYEE;
DELETE * from EMPLOYEE where DEPT_CODE is NULL;
SELECT EMP_NAME, BASIC + BASIC*0.2 + BASIC*0.6 AS NETPAY from EMPLOYEE;

SELECT upper(EMP_NAME), BASIC from EMPLOYEE order by DEPT_CODE asc;
SELECT EMP_NAME from EMPLOYEE where JN_DT > '1-jan-2013';
SELECT count(*) from EMPLOYEE where JN_DT like '%jan%';
SELECT max(BASIC), min(BASIC) from EMPLOYEE;
SELECT count(*) from EMPLOYEE where GENDER = 'F';
UPDATE EMPLOYEE set CITY = upper(CITY);
SELECT count(DISTINCT CITY) from EMPLOYEE;
SELECT * from EMPLOYEE order by DEPT_CODE, DEPT_NAME;
