package org.snhu.cs320.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.snhu.cs320.entities.Task;

public class TaskServiceTest {
	
	TaskService service;
	
	@BeforeEach
	void init() {
		TaskService.INSTANCE = null;
		service = TaskService.getInstance();
	}
	
	//Create Tests
	
	@Test void create_Success() {
		final Task t = new Task("1", "Task", "Description");
		service.create(t);
		
		assertTrue(service.entityRepository.containsKey("1"));
	}
	
	@Test
	public void create_PreventsAddingExistingContact() {
		final Task t = new Task("1", "Task", "Description");
		service.create(t);
		
		assertTrue(service.entityRepository.containsKey("1"));
		
		assertThrows(IllegalArgumentException.class, () -> service.create(t));
	}
	
	//Delete Tests
	@Test
	public void deleteById_Success() {
		final Task t = new Task("1", "Task", "Description");
		
		service.create(t);
		
		assertTrue(service.entityRepository.containsKey("1"));
		service.deleteById("1");
		assertTrue(!service.entityRepository.containsKey("1"));
	}
	@Test
	public void deleteById_IdNotFound() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteById("1"));
	}
	
	
	//Update tests
	@Test
	public void update_SuccessOnFoundId() {
		final Task t = new Task("1", "Task", "Description");
		final Task u = new Task("1", "Tasking", "Descriptioning");
		service.create(t);

		service.update(u);
		assertTrue(service.entityRepository.get("1") == u);
	}
	
	@Test
	public void update_SuccessOnNotFoundId() {
		final Task t = new Task("1", "Task", "Description");

		service.update(t);
		assertTrue(service.entityRepository.get("1") == t);
	}
	
}
