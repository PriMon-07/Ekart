drop table product;

create table product(
	productId int primary key,
	displayName varchar2(50),
	shortDesc varchar2(50),
	description varchar2(50),
	category varchar2(50),
	price float,
	discount float,
	deliveryCharge float,
	sellerId  varchar2(50)
);
	

drop table seller;

create table seller(
	seller_id int,
	seller_name varchar2(20),
	product_name varchar2(50),
	price float,
	discount int,	
	delivery_charge float
);