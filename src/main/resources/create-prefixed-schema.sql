CREATE TABLE T_Workout (
  WO_id IDENTITY,
  WO_name VARCHAR(200),
  WO_focus VARCHAR(20),
  WO_createdAt DATETIME
);
INSERT INTO T_workout (WO_name) VALUES ('Starbuxman');