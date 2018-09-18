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
package de.schauderhaft.talk;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Jens Schauder
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	// tag::query[]
	@Query("select count(*) from address")
	int countAddresses();
	// end::query[]


	@Query("select id, first_name, dob " +
			", created_at, created_by, updated_by, updated_at " +
			"from customer where upper(first_name) like '%' || upper(:name) || '%' ")
	List<Customer> findByName(@Param("name") String name);
}
