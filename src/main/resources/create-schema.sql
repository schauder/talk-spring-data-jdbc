CREATE TABLE Customer (
  id     IDENTITY,
  name   VARCHAR(200),
  dob    DATE,
  gender VARCHAR(200)
);































































create TABLE Address (
  customer     INTEGER,
  customer_key VARCHAR(200),
  Street       VARCHAR(200),
  city         VARCHAR(200)
);

create TABLE Book (
  id     IDENTITY,
  name         VARCHAR(200)
);
