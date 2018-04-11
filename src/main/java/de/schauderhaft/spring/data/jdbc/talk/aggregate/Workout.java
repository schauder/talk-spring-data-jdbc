/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.schauderhaft.spring.data.jdbc.talk.aggregate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain class representing a workout
 *
 * @author Jens Schauder
 */
// tag::main[]
public class Workout {

	@Transient
	ExerciseRepository allExercises = null;

	@Id
	private Long id;

	public void setName(String name) {
		this.name = name;
	}

	void setFocus(Focus focus) {
		this.focus = focus;
	}

	private String name;
	private Focus focus;

	private final List<ExerciseInterval> exercises = new ArrayList<>();

	Workout(String name, Focus focus) {
		this.name = name;
		this.focus = focus;
	}

	void add(int i, Exercise exercise) {

		Assert.notNull(exercise, "Exercise must not be null");
		Assert.notNull(exercise.id, "Exercise must have an id");

		exercises.add(new ExerciseInterval(i, exercise.id));
	}

	Long getId() {
		return id;
	}

	String getName() {
		return name;
	}

	Focus getFocus() {
		return focus;
	}

	int totalCount(Exercise exercise) {

		return exercises.stream()
				.filter(e -> e.getExerciseId() == exercise.id)
				.mapToInt(ExerciseInterval::getRepetitions)
				.sum();
	}

	public Exercise getExercise(int i) {

		Assert.notNull(allExercises, "Before using `getExercise(int)` the repository in the `Workout` must be injected");

		return allExercises
				.findById(exercises.get(i).getExerciseId())
				.orElseThrow(() -> new RuntimeException(
						String.format(
								"Workout '%s' references a not existing exercise with id %s.",
								name,
								exercises.get(i).getExerciseId()
						)
				));
	}
}
// end::main[]