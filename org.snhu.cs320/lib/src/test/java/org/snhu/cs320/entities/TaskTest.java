package org.snhu.cs320.entities;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class TaskTest {
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	//Id tests
	@Test
	void testId_Valid() {
		assertTrue(validator.validate(new Task("1", "Task", "Description")).isEmpty());
	}
	
	@Test
	void testId_Null() {
		assertEquals("Id is a required field", 
				validator.validate(new Task(null, "Task", "Description")).stream().findFirst().get().getMessage());
	}
	
	@Test
	void testId_TooLong() {
		assertEquals("Id cannot be longer than 10 characters",
				validator.validate(new Task("12345678901", "Task", "Description")).stream().findFirst().get().getMessage());
	}
	
	//Task Name Tests
	@Test
	void testTaskName_Valid() {
			assertTrue(validator.validate(new Task("1", "Task", "Description")).isEmpty());
	}
	
	@Test
	void testTaskName_Null() {
			assertEquals("Task Name is a required field", 
					validator.validate(new Task("1", null, "Description")).stream().findFirst().get().getMessage());
	}
	
	@Test
	void testTaskName_TooLong() {
			assertEquals("Task Name cannot be longer than 20 characters",
					validator.validate(new Task("1", "Task12345678901234567890", "Description")).stream().findFirst().get().getMessage());
	}
	
	//Description Tests
	@Test
	void testDescription_Valid() {
			assertTrue(validator.validate(new Task("1", "Task", "Description")).isEmpty());
	}
	
	@Test
	void testDescription_Null() {
			assertEquals("Description is a required field", 
					validator.validate(new Task("1", "Task", null)).stream().findFirst().get().getMessage());
	}
	
	@Test
	void testDescription_TooLong() {
			assertEquals("Description cannot be longer than 50 characters",
					validator.validate(new Task("1", "Task", "Description123456789012345678901234567890123456789012345678901234567890")).stream().findFirst().get().getMessage());
	}
	
}
