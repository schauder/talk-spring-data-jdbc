CREATE TABLE Workout (
  id IDENTITY,
  name VARCHAR(200),
  focus VARCHAR(20),
  created DATETIME,
  modified DATETIME,
  createdby VARCHAR(20),
  modifiedby VARCHAR(20)
);
INSERT INTO workout (name) VALUES ('Starbuxman');