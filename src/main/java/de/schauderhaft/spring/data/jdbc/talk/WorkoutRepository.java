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

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Jens Schauder
 */
// tag::main[]
public interface WorkoutRepository
		extends CrudRepository<Workout, Long>
// end::main[]
{
	// tag::query[]
	@Query("SELECT * FROM WORKOUT WHERE NAME like '%' || :name || '%'")
	List<Workout> findByName(@Param("name") String name);
    // end::query[]

	// tag::modifying[]
	@Modifying
	@Query("DELETE FROM WORKOUT WHERE NAME like '%' || :name || '%'")
	Long deleteByName(@Param("name") String name);
    // end::modifying[]


	// tag::rowmapper1[]
	@Query(value = "VALUES ('Dummy-Workout')",
			rowMapperClass = DummyRowMapper.class)
	Workout wonkyWorkout();
    // end::rowmapper1[]

	// tag::rowmapper2[]
	class DummyRowMapper implements RowMapper<Workout> {
		@Override
		public Workout mapRow(ResultSet rs, int rowNum)
				throws SQLException {

			Workout workout = new Workout();
			workout.name = rs.getString(1);
			return workout;
		}
	}
	// end::rowmapper2[]
}
