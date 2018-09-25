drop table Customer;
drop table address;
drop table purchase_order;

CREATE TABLE Customer (
  id         IDENTITY,
  first_name VARCHAR(200),
  last_name  VARCHAR(200),
  dob        DATE,
  created_by VARCHAR(200),
  created_at DATETIME,
  updated_by VARCHAR(200),
  updated_at  DATETIME
);

create TABLE address (
  street       VARCHAR(200),
  city         VARCHAR(200),
  zip          VARCHAR(200),
  customer_key VARCHAR(200),
  customer     INTEGER
);

CREATE TABLE purchase_order (
  id           IDENTITY,
  customer_ref INTEGER
)