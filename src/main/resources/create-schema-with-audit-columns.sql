CREATE TABLE Workout (
  id IDENTITY,
  name VARCHAR(200),
  focus VARCHAR(20),
  created DATETIME,
  modified DATETIME,
  created_by VARCHAR(20),
  modified_by VARCHAR(20)
);
INSERT INTO workout (name) VALUES ('Starbuxman');