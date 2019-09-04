CREATE TABLE Customer (
  id     IDENTITY PRIMARY KEY,
  name   VARCHAR(200),
  dob    DATE,
  gender VARCHAR(200)
);





















create TABLE Address (
  customer     INTEGER,
  customer_key VARCHAR(200),
  city         VARCHAR(200),
  PRIMARY KEY (customer, customer_key)
);

ALTER TABLE Address
ADD CONSTRAINT FK_ADDRESS_CUSTOMER FOREIGN KEY (customer) REFERENCES Customer (ID);





















create TABLE Book (
  id     IDENTITY,
  title         VARCHAR(200)
);

create TABLE Book_Author (
  book INTEGER,
  author INTEGER
);

create table AUTHOR (
  id IDENTITY,
  name VARchar(200)
);