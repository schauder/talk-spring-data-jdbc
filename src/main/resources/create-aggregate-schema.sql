CREATE TABLE Workout (
  id IDENTITY,
  name VARCHAR(200),
  focus VARCHAR(20)
);

CREATE TABLE Exercise_Interval (
  workout INTEGER,
  workout_key INTEGER,
  repetitions INTEGER,
  exercise_Id INTEGER
);

CREATE TABLE Exercise (
  id IDENTITY,
  name VARCHAR(200),
)