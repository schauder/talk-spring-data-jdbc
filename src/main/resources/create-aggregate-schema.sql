CREATE TABLE Workout (
  id IDENTITY,
  name VARCHAR(200),
  focus VARCHAR(20)
);

CREATE TABLE ExerciseInterval (
  workout INTEGER,
  workout_key INTEGER,
  repetitions INTEGER,
  exerciseId INTEGER
);

CREATE TABLE Exercise (
  id IDENTITY,
  name VARCHAR(200),
)