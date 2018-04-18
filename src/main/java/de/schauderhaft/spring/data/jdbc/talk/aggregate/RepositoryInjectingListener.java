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

import org.springframework.context.ApplicationListener;
import org.springframework.data.jdbc.mapping.event.AfterLoadEvent;
import org.springframework.stereotype.Component;

/**
 * @author Jens Schauder
 */
@Component
public class RepositoryInjectingListener implements ApplicationListener<AfterLoadEvent> {
	private final ExerciseRepository exerciseRepository;

	public RepositoryInjectingListener(ExerciseRepository exerciseRepository) {
		this.exerciseRepository = exerciseRepository;
	}

	@Override
	public void onApplicationEvent(AfterLoadEvent event) {

		Object source = event.getEntity();
		if (source instanceof Workout) {
			((Workout) source).allExercises = exerciseRepository;
		}
	}
}
