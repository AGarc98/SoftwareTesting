package org.snhu.cs320.services;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.snhu.cs320.entities.Contact;
import org.snhu.cs320.validations.EntityValidator;


public class ContactService {
	
	static ContactService INSTANCE;
	
	final Map<String, Contact> entityRepository;
	final EntityValidator validator;
	
	private ContactService() {
		entityRepository = new ConcurrentHashMap<>();
		this.validator = new EntityValidator();
	}
	
	public static synchronized ContactService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ContactService();
		}
		return INSTANCE;
	}
	
	public Contact create(final Contact contact) {
		Objects.requireNonNull(contact);
		
		return validator.validateAndDoOrThrow(
				contact,
				c -> {
					if(entityRepository.containsKey(contact.contactId())) {
						throw new IllegalArgumentException(String.format("An entry with ID [%s] already exists", contact.contactId()));
						}
					return entityRepository.put(contact.contactId(), contact);
					}
		);
	}
	
	public Optional<Contact> deleteById(final String id){
		Objects.requireNonNull(id);
		 
		if(entityRepository.containsKey(id)) {
			final Contact c = entityRepository.get(id);
			entityRepository.remove(id);
			return Optional.of(c);
		}
		throw new IllegalArgumentException(String.format("No entry with ID [%s] exists", id));
	}
	
	public Optional<Contact> findById(final String id){
		Objects.requireNonNull(id);
		 
		if(entityRepository.containsKey(id)) {
			final Contact c = entityRepository.get(id);
			System.out.println(c.toString());
			return Optional.of(c);
		}
		throw new IllegalArgumentException(String.format("No entry with ID [%s] exists", id));
	}
	
	public void update(final Contact contact){
		Objects.requireNonNull(contact);
		
		if(entityRepository.containsKey(contact.contactId())) {
			entityRepository.remove(contact.contactId());
		}
		
		create(contact);
	}
}
