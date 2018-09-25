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

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.JdbcConfiguration;
import org.springframework.data.relational.core.mapping.event.AfterLoadEvent;

/**
 * @author Jens Schauder
 */
@SpringBootConfiguration
@AutoConfigurationPackage
@EnableJdbcAuditing
public class CustomerConfig extends JdbcConfiguration {

	// Events
	@Bean
	ApplicationListener<AfterLoadEvent> injectRepository(ApplicationContext context) {

		return event -> {
			Object entity = event.getEntity();
			if (entity instanceof Customer) {
				((Customer) entity).setPurchaseOrderRepository(context.getBean(PurchaseOrderRepository.class));
			}
		};
	}

	// tag::auditor[]
	@Bean
	AuditorAware<String> auditorAware() {
		return new ModifiableAuditorAware();
	}
	// end::auditor[]
}
