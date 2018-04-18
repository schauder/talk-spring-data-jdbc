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
package de.schauderhaft.spring.data.jdbc.talk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.Java6Assertions.*;

/**
 * @author Jens Schauder
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringleticsConfiguration.class)
public class SpringleticsTest {

	@Autowired
	WorkoutRepository repository;

	@Test
	public void testConfiguration() {
		assertThat(repository).isNotNull();
	}

	@Test
	public void simpleCrud() {

// tag::create[]
		Workout workout = new Workout();
		workout.name = "Juergen Hoeller";
		workout.focus = Focus.ENDURANCE;

		Workout saved = repository.save(workout);

		assertThat(repository.findById(saved.id)
				.isPresent()).isTrue();
// end::create[]

// tag::update[]
		saved.name = "Jürgen Höller";
		repository.save(saved);

		repository.deleteById(saved.id);
// end::update[]

		assertThat(repository.findById(saved.id)
				.isPresent()).isFalse();
	}

	@Test
	public void demonstrateQuery() {

		Workout workout = new Workout();
		workout.name = "Oliver Gierke";
		workout.focus = Focus.ENDURANCE;

		repository.save(workout);

		assertThat(repository.findByName("ver"))
				.hasSize(1);
		assertThat(repository.deleteByName("ver")).isEqualTo(1);
		assertThat(repository.deleteByName("ver")).isEqualTo(0);
	}

	@Test
	public void demonstrateCustomRowMapper() {
		assertThat(repository.wonkyWorkout().name).isEqualTo("Dummy-Workout");
	}


	@Test
	public void auditing() {

		Workout workout = new Workout();
		workout.name = "Oliver Gierke";
		workout.focus = Focus.ENDURANCE;

		repository.save(workout);

		assertThat(workout.getCreatedAt())
				.isBetween(
						Instant.now().minus(Duration.ofSeconds(1)),
						Instant.now().plus(Duration.ofSeconds(1)));

		assertThat(repository.findById(workout.id).get().getCreatedAt()).isEqualTo(workout.getCreatedAt());
	}

}
