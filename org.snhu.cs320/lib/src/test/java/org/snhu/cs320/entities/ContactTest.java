package org.snhu.cs320.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ContactTest{
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	//Id tests
	@Test
	void testId_Valid() {
		assertTrue(validator.validate(new Contact("1", "Bob", "Doe", "1234567890", "123 Street")).isEmpty());
	}
	
	@Test
	void testId_Null() {
		assertEquals("Id is a required field", 
				validator.validate(new Contact(null, "Bob", "Doe", "1234567890", "123 Street")).stream().findFirst().get().getMessage());
	}
	
	@Test
	void testId_TooLong() {
		assertEquals("Id cannot be longer than 10 characters",
				validator.validate(new Contact("12345678901", "Bob", "Doe", "1234567890", "123 Street")).stream().findFirst().get().getMessage());
	}
	
	//First Name Tests
	@Test
	void testFirstName_Valid() {
			assertTrue(validator.validate(new Contact("1", "Bob", "Doe", "1234567890", "123 Street")).isEmpty());
	}
	
	@Test
	void testFirstName_Null() {
			assertEquals("First Name is a required field", 
					validator.validate(new Contact("1", null, "Doe", "1234567890", "123 Street")).stream().findFirst().get().getMessage());
	}
	
	@Test
	void testFirstName_TooLong() {
			assertEquals("First Name cannot be longer than 10 characters",
					validator.validate(new Contact("1", "Bob1234567890", "Doe", "1234567890", "123 Street")).stream().findFirst().get().getMessage());
	}
	
	//Last Name Tests
	@Test
	void testLastName_Valid() {
			assertTrue(validator.validate(new Contact("1", "Bob", "Doe", "1234567890", "123 Street")).isEmpty());
	}
	
	@Test
	void testLastName_Null() {
			assertEquals("Last Name is a required field", 
					validator.validate(new Contact("1", "Bob", null, "1234567890", "123 Street")).stream().findFirst().get().getMessage());
	}
	
	@Test
	void testLastName_TooLong() {
			assertEquals("Last Name cannot be longer than 10 characters",
					validator.validate(new Contact("1", "Bob", "Doe1234567890", "1234567890", "123 Street")).stream().findFirst().get().getMessage());
	}
	
	//Phone Number Tests
	@Test
	void testPhoneNumber_Valid() {
			assertTrue(validator.validate(new Contact("1", "Bob", "Doe", "1234567890", "123 Street")).isEmpty());
	}
	
	@Test
	void testPhoneNumber_Null() {
			assertEquals("Phone Number is a required field", 
					validator.validate(new Contact("1", "Bob", "Doe", null, "123 Street")).stream().findFirst().get().getMessage());
	}
	
	@Test
	void testPhoneNumber_TooLong() {
			assertEquals("Phone number must be exactly 10 digits",
					validator.validate(new Contact("1", "Bob", "Doe", "12345678901", "123 Street")).stream().findFirst().get().getMessage());
	}
	
	@Test
	void testPhoneNumber_TooShort() {
			assertEquals("Phone number must be exactly 10 digits",
					validator.validate(new Contact("1", "Bob", "Doe", "123456789", "123 Street")).stream().findFirst().get().getMessage());
	}
	
	//Address Tests
	@Test
	void testAddress_Valid() {
			assertTrue(validator.validate(new Contact("1", "Bob", "Doe", "1234567890", "123 Street")).isEmpty());
	}
	
	@Test
	void testAddress_Null() {
			assertEquals("Address is a required field", 
					validator.validate(new Contact("1", "Bob", "Doe", "1234567890", null)).stream().findFirst().get().getMessage());
	}
	
	@Test
	void testAddress_TooLong() {
			assertEquals("Address cannot be longer than 30 characters",
					validator.validate(new Contact("1", "Bob", "Doe", "1234567890", "123 Street123456789012345678901234567890")).stream().findFirst().get().getMessage());
	}
}
