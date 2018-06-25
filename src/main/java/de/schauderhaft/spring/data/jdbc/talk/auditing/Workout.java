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

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

/**
 * Domain class representing a workout
 *
 * @author Jens Schauder
 */
@Data
public class Workout {

	@Id
	Long id;
	String name;
	Focus focus;

	// tag::fields[]
	@CreatedDate Instant created;
	@LastModifiedDate Instant modified;

	@CreatedBy String createdBy;
	@LastModifiedBy	String modifiedBy;
// end::fields[]
}
