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
package de.schauderhaft.spring.data.jdbc.talk.mybatis;

import de.schauderhaft.spring.data.jdbc.talk.Workout;
import de.schauderhaft.spring.data.jdbc.talk.WorkoutMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.DataAccessStrategy;
import org.springframework.data.jdbc.mybatis.MyBatisContext;
import org.springframework.data.jdbc.mybatis.MyBatisDataAccessStrategy;
import org.springframework.data.relational.core.conversion.RelationalConverter;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import javax.sql.DataSource;

public class WithMyBatisConfiguration {

	// tag::mybatis1[]
	@Bean
	SqlSessionFactoryBean sessionFactory(DataSource db) {

		org.apache.ibatis.session.Configuration config =
				new org.apache.ibatis.session.Configuration();
		config.getTypeAliasRegistry()
				.registerAlias("MyBatisContext", MyBatisContext.class);

		config.getTypeAliasRegistry().registerAlias(Workout.class);
		config.addMapper(WorkoutMapper.class);

		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(db);
		bean.setConfiguration(config);

		return bean;
	}
	// end::mybatis1[]

	// tag::mybatis2[]
	@Bean
	SqlSessionTemplate session(SqlSessionFactory factory) {
		return new SqlSessionTemplate(factory);
	}
	// end::mybatis2[]

	// tag::mybatis3[]
	@Bean
	DataAccessStrategy dataAccessStrategy(
			RelationalMappingContext context,
			RelationalConverter converter, NamedParameterJdbcOperations operations, SqlSession sqlSession) {
		return MyBatisDataAccessStrategy
				.createCombinedAccessStrategy(context, converter, operations, sqlSession);
	}
	// tag::mybatis3[]
}

