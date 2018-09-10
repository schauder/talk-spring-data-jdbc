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

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.config.JdbcConfiguration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Optional;

// tag::enable[]
@EnableJdbcRepositories
@EnableJdbcAuditing
class AuditingConfiguration extends JdbcConfiguration {
// end::enable[]

	// tag::auditor[]
	@Bean
	AuditorAware<String> auditorAware() {
		return () -> Optional.of("Jens Schauder");
	}
	// end::auditor[]

	@Bean
	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("create-schema-with-audit-columns.sql")
				.generateUniqueName(true)
				.build();
	}

	@Bean
	NamedParameterJdbcTemplate template(
			DataSource db
	) {
		return new NamedParameterJdbcTemplate(db);
	}
}


