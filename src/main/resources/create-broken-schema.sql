CREATE TABLE Workout (
  id IDENTITY,
  name VARCHAR(200),
  focus VARCHAR(20)
);

CREATE TABLE Exercise (
  workout INTEGER,
  workout_key INTEGER,
  name VARCHAR(200),
)