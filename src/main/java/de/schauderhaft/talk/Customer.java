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

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jens Schauder
 */
class Customer {

	@Id
	Long id;

	String firstName;

	LocalDate dob;

	Map<String, Address> addresses = new HashMap<>();

	// relevant for Auditing only
	// tag::fields[]
	@CreatedBy
	String createdBy;
	@CreatedDate
	Instant createdAt;
	@LastModifiedBy
	String updatedBy;
	@LastModifiedDate
	Instant updatedAt;
	// end::fields[]


	// part of the event examples
	@Transient
	private PurchaseOrderRepository purchaseOrderRepository;

	void setPurchaseOrderRepository(PurchaseOrderRepository purchaseOrderRepository) {

		this.purchaseOrderRepository = purchaseOrderRepository;
	}
}
