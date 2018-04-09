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

import de.schauderhaft.spring.data.jdbc.talk.Focus;
import de.schauderhaft.spring.data.jdbc.talk.SpringleticsConfiguration;
import de.schauderhaft.spring.data.jdbc.talk.Workout;
import de.schauderhaft.spring.data.jdbc.talk.WorkoutRepository;
import de.schauderhaft.spring.data.jdbc.talk.namingstrategy.WithPrefixesConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Java6Assertions.*;

/**
 * @author Jens Schauder
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringleticsConfiguration.class, WithPrefixesConfiguration.class})
public class SpringleticsWithPrefixesTest {

	@Autowired
	WorkoutRepository repository;

	@Test
	public void testConfiguration(){
		assertThat(repository).isNotNull();
	}

	@Test
	public void simpleCrud(){

// tag::create[]
 		Workout workout = new Workout();
		workout.name = "Red Jonson";
		workout.focus = Focus.ENDURANCE;

		Workout saved = repository.save(workout);

		assertThat(repository.findById(saved.id)
				.isPresent()).isTrue();
// end::create[]

// tag::update[]
		saved.name = "Rod Johnson";
		repository.save(saved);

		repository.deleteById(saved.id);
// end::update[]

		assertThat(repository.findById(saved.id)
				.isPresent()).isFalse();
	}

}
