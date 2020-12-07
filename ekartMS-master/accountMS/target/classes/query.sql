drop table user;

create table UserData(
	userID varchar2(50) primary key,
	name varchar2(50) not null,
	password varchar2(50) not null,
	accountType varchar2(15)
);
select * from userdata