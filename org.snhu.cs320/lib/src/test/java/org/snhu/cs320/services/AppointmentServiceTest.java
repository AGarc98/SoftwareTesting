package org.snhu.cs320.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.snhu.cs320.entities.Appointment;
import org.snhu.cs320.entities.Task;

public class AppointmentServiceTest {

	AppointmentService service;
	
	@BeforeEach
	void init() {
		AppointmentService.INSTANCE = null;
		service = AppointmentService.getInstance();
	}
	
	//Create Tests
	
	@Test void create_Success() {
		final Appointment a = new Appointment("1", new Date(2023,3,1), "123");
		service.create(a);
		
		assertTrue(service.entityRepository.containsKey("1"));
	}
	
	@Test
	public void create_PreventsAddingExistingContact() {
		final Appointment a = new Appointment("1", new Date(2023,3,1), "123");
		service.create(a);
		
		assertTrue(service.entityRepository.containsKey("1"));
		
		assertThrows(IllegalArgumentException.class, () -> service.create(a));
	}
	
	//Delete Tests
	@Test
	public void deleteById_Success() {
		final Appointment a = new Appointment("1", new Date(2023,3,1), "123");
		
		service.create(a);
		
		assertTrue(service.entityRepository.containsKey("1"));
		service.deleteById("1");
		assertTrue(!service.entityRepository.containsKey("1"));
	}
	@Test
	public void deleteById_IdNotFound() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteById("1"));
	}
	
}
