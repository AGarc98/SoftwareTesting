package org.snhu.cs320.services;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.snhu.cs320.entities.Appointment;
import org.snhu.cs320.validations.EntityValidator;

public class AppointmentService {
	
	static AppointmentService INSTANCE;
	
	final Map<String, Appointment> entityRepository;
	final EntityValidator validator;
	
	private AppointmentService() {
		entityRepository = new ConcurrentHashMap<>();
		this.validator = new EntityValidator();
	}
	
	public static synchronized AppointmentService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AppointmentService();
		}
		return INSTANCE;
	}
	
	public Appointment create(final Appointment appointment) {
		Objects.requireNonNull(appointment);
		
		return validator.validateAndDoOrThrow(
				appointment,
				c -> {
					if(entityRepository.containsKey(appointment.appointmentId())) {
						throw new IllegalArgumentException(String.format("An entry with ID [%s] already exists", appointment.appointmentId()));
						}
					return entityRepository.put(appointment.appointmentId(), appointment);
					}
		);
	}
	
	public Optional<Appointment> deleteById(final String id){
		Objects.requireNonNull(id);
		 
		if(entityRepository.containsKey(id)) {
			final Appointment a = entityRepository.get(id);
			entityRepository.remove(id);
			return Optional.of(a);
		}
		throw new IllegalArgumentException(String.format("No entry with ID [%s] exists", id));
	}
}
