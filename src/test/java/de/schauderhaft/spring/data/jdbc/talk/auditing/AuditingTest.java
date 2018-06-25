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
package de.schauderhaft.spring.data.jdbc.talk.auditing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Schauder
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AuditingConfiguration.class)
public class AuditingTest {

	@Autowired
	AuditingWorkoutRepository repository;

	@Test
	public void testConfiguration() {
		assertThat(repository).isNotNull();
	}

	@Test
	public void simpleCrud() {

		Workout workout = new Workout();
		workout.name = "Juergen Hoeller";
		workout.focus = Focus.ENDURANCE;

		Workout saved = repository.save(workout);

		Optional<Workout> reloaded = repository.findById(saved.id);
		assertThat(reloaded
				.isPresent()).isTrue();

		assertThat(saved.created).isNotNull();
		assertThat(saved.createdBy).isNotNull();

		assertThat(reloaded.get().created).isNotNull();
		assertThat(reloaded.get().createdBy).isNotNull();

		saved.name = "Jürgen Höller";
		repository.save(saved);

		reloaded = repository.findById(saved.id);

		assertThat(saved.created).isNotNull();
		assertThat(saved.createdBy).isNotNull();
		assertThat(saved.modified).isNotNull();
		assertThat(saved.modifiedBy).isNotNull();

		assertThat(reloaded.get().created).isNotNull();
		assertThat(reloaded.get().createdBy).isNotNull();
		assertThat(reloaded.get().modified).isNotNull();
		assertThat(reloaded.get().modifiedBy).isNotNull();

	}
}
