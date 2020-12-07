/*written for oracle db*/

DROP TABLE cart;

CREATE TABLE cart (
  cart_id int NOT NULL,
  cart_offer_price float DEFAULT NULL,
  category varchar(255) DEFAULT NULL,
  date1 date DEFAULT NULL,
  delivery_charge float DEFAULT NULL,
  display_name varchar(255) DEFAULT NULL,
  price float DEFAULT NULL,
  quantity int DEFAULT NULL,
  seller_name varchar(255) DEFAULT NULL,
  total float DEFAULT NULL,
  user_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (cart_id)
);

select * from cart;

insert into cart values(1,0.0,'accessories','01-Dec-2018',10.0,'phone',50.0,2,'sellerOne',60.0,2);

/*-------------*/