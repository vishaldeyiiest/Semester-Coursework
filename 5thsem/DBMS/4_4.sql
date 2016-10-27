create table ORDERMAST
(
	ORDER_NO CHAR(5) PRIMARY KEY,
	ORDER_DT DATE
);

create table ORDERDETAILS
(
	ORDER_NO CHAR(5),
	ITEM_NO CHAR(5),
	QTY NUMBER,
	Primary key (ORDER_NO, ITEM_NO),
	constraint fk1 foreign key (order_no) references ordermast(order_no)
);

create table DELIVERYMAST
(
	DELV_NO CHAR(5) Primary key,
	ORDER_NO CHAR(5),
	DELV_DT DATE,
	constraint fk2 foreign key (order_no) references ordermast(order_no)
);

create table DELIVERY_DETAILS
(
	DELV_NO CHAR(5),
	ITEM_NO CHAR(5),
	QTY NUMBER,
	Primary key (DELV_NO, ITEM_NO),
	constraint fk3 foreign key (delv_no) references deliverymast(delv_no)
);

insert into ordermast values ('001','01-jan-2016');
insert into ordermast values ('002','03-jul-2016');
insert into ordermast values ('003','10-feb-2016');
insert into ordermast values ('004','07-mar-2016');
insert into ordermast values ('005','20-sep-2016');
insert into orderdetails values ('001','tv',3);
insert into orderdetails values ('001','tab',10);
insert into orderdetails values ('003','ref',5);
insert into orderdetails values ('002','was',8);
insert into orderdetails values ('002','mob',30);
insert into deliverymast values ('001','001','10-jan-2016');
insert into deliverymast values ('001','002','20-jul-2016');
insert into deliverymast values ('003','003','20-feb-2016');
insert into deliverymast values ('002','004','17-mar-2016');
insert into deliverymast values ('002','005','30-sep-2016');
insert into delivery_details values ('001','001',3);
insert into delivery_details values ('001','002',10);
insert into delivery_details values ('001','003',5);
insert into delivery_details values ('002','004',8);
insert into delivery_details values ('002','005',30);

accept date s prompt('Enter a date');
accept date d prompt('Enter a date');
declare
	ordt ordermast.order_dt%type;
	dldt ordermast.order_dt%type;
begin
	ordt := '&order_dt';
	dldt := '&order_dt';
	
