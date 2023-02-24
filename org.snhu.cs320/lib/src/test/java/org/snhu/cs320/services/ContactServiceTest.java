package org.snhu.cs320.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.snhu.cs320.entities.Contact;


public class ContactServiceTest {
	
	ContactService service;
	
	@BeforeEach
	void init() {
		ContactService.INSTANCE = null;
		service = ContactService.getInstance();
	}
	
	//Create Tests
	
	@Test void create_Success() {
		final Contact c = new Contact("1", "Bob", "Doe", "1234567890", "123 Street");
		service.create(c);
		
		assertTrue(service.entityRepository.containsKey("1"));
	}
	@Test
	public void create_PreventsAddingExistingContact() {
		final Contact c = new Contact("1", "Bob", "Doe", "1234567890", "123 Street");
		
		service.create(c);
		
		assertThrows(IllegalArgumentException.class, () -> service.create(c));
	}
	
	//Delete Tests
	@Test
	public void deleteById_Success() {
		final Contact c = new Contact("1", "Bob", "Doe", "1234567890", "123 Street");
		
		service.create(c);
		
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
		final Contact c = new Contact("1", "Bob", "Doe", "1234567890", "123 Street");
		final Contact d = new Contact("1", "Jill", "Doe", "1234567890", "123 Street");
		service.create(c);

		service.update(d);
		assertTrue(service.entityRepository.get("1") == d);
	}
	@Test
	public void update_SuccessOnNotFoundId() {
		final Contact d = new Contact("1", "Jill", "Doe", "1234567890", "123 Street");

		service.update(d);
		assertTrue(service.entityRepository.get("1") == d);
	}
	
}
