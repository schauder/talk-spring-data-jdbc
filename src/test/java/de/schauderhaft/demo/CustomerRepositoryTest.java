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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Schauder
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CustomerConfig.class)
public class CustomerRepositoryTest {

	@Autowired CustomerRepository customerRepo;
	@Autowired PurchaseOrderRepository poRepo;

	@Test
	public void contextLoads() {

	}


	@Test
	public void createACustomer() {

		Customer customer = createCustomer();
		customer.addresses.put("Main", createAddress());

		Customer saved = customerRepo.save(customer);

		assertThat(saved.id).isNotNull();

		saved.firstName = "Heinz Paul";

		customerRepo.save(saved);
	}

	@Test
	public void purchaseOrder() {
		Customer customer = customerRepo.save(createCustomer());
		PurchaseOrder po1 = new PurchaseOrder();
		po1.customerRef  = customer.id;

		PurchaseOrder po2 = new PurchaseOrder();
		po2.customerRef = customer.id;

		poRepo.saveAll(asList(po1, po2));
	}

	private Address createAddress() {
		Address address = new Address();
		address.city = "Braunschweig";
		address.zip = "38116";
		address.street = "None of your concern";
		return address;
	}

	private Customer createCustomer() {
		Customer customer = new Customer();
		customer.dob = LocalDate.now();
		customer.firstName = "Jens";
		return customer;
	}
}