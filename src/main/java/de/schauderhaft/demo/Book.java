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
package de.schauderhaft.demo;

import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

class Book {

	private @Id
	Long id;

	private String title;

	private Set<AuthorRef> authors = new HashSet<>();

	public void addAuthor(Author author) {
		authors.add(createAuthorRef(author));
	}

	private AuthorRef createAuthorRef(Author author) {

		Assert.notNull(author, "Author must not be null");
		Assert.notNull(author.id, "Author id, must not be null");

		AuthorRef authorRef = new AuthorRef();
		authorRef.author = author.id;
		return authorRef;
	}
}
