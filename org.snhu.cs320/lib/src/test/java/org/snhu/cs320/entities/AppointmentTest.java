package org.snhu.cs320.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.io.*;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class AppointmentTest {
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	Date futureDate = new Date(2023, 3, 1);
	Date pastDate = new Date(1000);
	
	
	//Id tests
	@Test
	void testId_Valid() {
		assertTrue(validator.validate(new Appointment("1" , futureDate, "Description")).isEmpty());
	}
	
	@Test
	void testId_Null() {
		assertEquals("Id is a required field", 
				validator.validate(new Appointment(null , futureDate, "Description")).stream().findFirst().get().getMessage());
	}
	
	@Test
	void testId_TooLong() {
		assertEquals("Id cannot be longer than 10 characters",
				validator.validate(new Appointment("12345678901" , futureDate, "Description")).stream().findFirst().get().getMessage());
	}
	
	//Appointment Date Tests
	@Test
	void testAppointmentDate_Valid() {
			assertTrue(validator.validate(new Appointment("1" , futureDate, "Description")).isEmpty());
	}
	
	@Test
	void testAppointmentDate_Null() {
			assertEquals("Date is a required field", 
					validator.validate(new Appointment("1" , null, "Description")).stream().findFirst().get().getMessage());
	}
	
	@Test
	void testAppointmentDate_InPast() {
			assertEquals("Date must be in the future or present",
					validator.validate(new Appointment("1" , pastDate, "Description")).stream().findFirst().get().getMessage());
	}
	
	//Description Tests
	@Test
	void testDescription_Valid() {
			assertTrue(validator.validate(new Appointment("1" , futureDate, "Description")).isEmpty());
	}
	
	@Test
	void testDescription_Null() {
			assertEquals("Description is a required field", 
					validator.validate(new Appointment("1" , futureDate, null)).stream().findFirst().get().getMessage());
	}
	
	@Test
	void testDescription_TooLong() {
			assertEquals("Description cannot be longer than 50 characters",
					validator.validate(new Appointment("1", futureDate, "Description123456789012345678901234567890123456789012345678901234567890")).stream().findFirst().get().getMessage());
	}
}
