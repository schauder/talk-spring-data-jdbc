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

import org.springframework.data.jdbc.mapping.model.JdbcPersistentProperty;
import org.springframework.data.jdbc.mapping.model.NamingStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jens Schauder
 */
// tag::naming[]
class PrefixNamingStrategy implements NamingStrategy {
	private Map<Class, String> columnPrefix = new HashMap<>();

	{ columnPrefix.put(Workout.class, "WO"); }

	public String getColumnName(JdbcPersistentProperty property) {
		return columnPrefix.get(property.getOwner().getType())
				+ "_"
				+ NamingStrategy.super.getColumnName(property);
	}

	public String getTableName(Class<?> type) {
		return "T_" + NamingStrategy.super.getTableName(type);
	}
}
// end::naming[]
