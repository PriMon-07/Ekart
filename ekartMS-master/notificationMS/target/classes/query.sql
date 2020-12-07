drop table NOTIFICATION;

create table NOTIFICATION(
notificationId integer primary key,
message varchar2(100) not null,
messageType varchar2(50) not null,
timeStamp Date not null,
userId varchar2(40) not null,
readStatus varchar2(10) not null

);

insert into NOTIFICATION values(1,'one item went up','price inc',sysdate,'abcB@AHJ.COM','unread');
insert into NOTIFICATION values(2,'one item went DOWN','price DEC',sysdate,'abcB@AHJ.COM','unread');
insert into NOTIFICATION values(3,'one item went ntnh','price nthtn',sysdate,'abcB@AHJ.COM','read');