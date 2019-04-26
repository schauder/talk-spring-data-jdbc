/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.schauderhaft.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Jens Schauder
 */
@RunWith(SpringRunner.class)
@DataJdbcTest
public class BookAndAuthorsTests {

	@Autowired
	BookRepository books;

	@Autowired
	AuthorRepository authors;

	@Test
	public void createAuthorsAndBooks() {

		Author tolkien = new Author();
		tolkien.name = "J.R.R. Tolkien";

		tolkien = authors.save(tolkien);

		Book lordOfTheRings = new Book();
		lordOfTheRings.title = "Lord of the Rings";
		lordOfTheRings.addAuthor(tolkien);

		Book simarillion = new Book();
		simarillion.title = "Simarillion";
		simarillion.addAuthor(tolkien);

		books.saveAll(asList(lordOfTheRings, simarillion));

		assertThat(books.count()).isEqualTo(2);
		assertThat(authors.count()).isEqualTo(1);

		books.delete(simarillion);

		assertThat(books.count()).isEqualTo(1);
		assertThat(authors.count()).isEqualTo(1);

	}
}
