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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.mapping.model.JdbcPersistentProperty;
import org.springframework.data.jdbc.mapping.model.NamingStrategy;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.HashMap;
import java.util.Map;

// tag::main[]
@Configuration
@EnableJdbcRepositories
public class SpringleticsConfiguration {

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(
				new EmbeddedDatabaseBuilder()
						.setType(EmbeddedDatabaseType.HSQL)
						.addScript("schema.sql")
						.build());
	}
	// end::main[]

	// tag::naming[]
	@Bean
	public NamingStrategy namingStrategy() {
		return new PrefixNamingStrategy();
	}

	private static class PrefixNamingStrategy implements NamingStrategy {
		private Map<Class, String> columnPrefix = new HashMap<>();

		{columnPrefix.put(Workout.class, "WO");	}

		@Override
		public String getColumnName(JdbcPersistentProperty property) {
			return columnPrefix.get(property.getOwner().getType())
					+ "_"
					+ NamingStrategy.super.getColumnName(property);
		}

		@Override
		public String getTableName(Class<?> type) {
			return "T_" + NamingStrategy.super.getTableName(type);
		}
	}
// end::naming[]
}

