CREATE TABLE EMPLOYEE(
	EMP_CODE char(5),
	EMP_NAME char(20),
	DEPT_CODE char(5) PRIMARY KEY,
	DESIG_CODE char(5),
	GENDER char(1),
	ADDRESS char(25),
	CITY char(20),
	STATE char(20),
	PIN char(6),	
	BASIC NUMBER,
	JN_DT Date
	);
CREATE TABLE DESIGNATION(
	DESIG_CODE char(5) Primary Key,
	DESIG_DESC char(20)
	);
CREATE TABLE DEPARTMENT(
	DEPT_CODE char(5),
	DEPT_NAME char(15) Primary key
	);

INSERT into DESIGNATION values('man',"Manager");
INSERT into DESIGNATION values('exe','Executive');
INSERT into DESIGNATION values('off','officer');
INSERT into DESIGNATION values('clk','clerk');
INSERT into DESIGNATION values('hlp','helper');

INSERT into DEPARTMENT values('1','Personnel');
INSERT into DEPARTMENT values('2','Production');
INSERT into DEPARTMENT values('3','Purchase');
INSERT into DEPARTMENT values('4','Finance');
INSERT into DEPARTMENT values('5','Research');

INSERT into EMPLOYEE values
('1','Aman Khan','2','exe','M','adsada asd','Kolkata','WB',700001,3000,'12-july-2015');
