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

import de.schauderhaft.spring.data.jdbc.talk.mybatis.WithMyBatisConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.*;

/**
 * @author Jens Schauder
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringleticsConfiguration.class, WithMyBatisConfiguration.class})
public class SpringleticsWithMyBatisTest {

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
		workout.name = "Marc Hekler";
		workout.focus = Focus.ENDURANCE;

		Workout saved = repository.save(workout);

		Optional<Workout> loaded = repository.findById(saved.id);
		assertThat(loaded.isPresent()).isTrue();
		assertThat(loaded.get().getName()).startsWith("Name based on an id-");
// end::create[]

// tag::update[]
		saved.name = "Mark Heckler";
		repository.save(saved);

		repository.deleteById(saved.id);
// end::update[]

		assertThat(repository.findById(saved.id)
				.isPresent()).isFalse();
	}

}
