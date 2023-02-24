package org.snhu.cs320.services;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.snhu.cs320.entities.Task;
import org.snhu.cs320.validations.EntityValidator;

public class TaskService {

	static TaskService INSTANCE;
	
	final Map<String, Task> entityRepository;
	final EntityValidator validator;
	
	private TaskService() {
		entityRepository = new ConcurrentHashMap<>();
		this.validator = new EntityValidator();
	}
	
	public static synchronized TaskService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TaskService();
		}
		return INSTANCE;
	}
	
	public Task create(final Task task) {
		Objects.requireNonNull(task);
		
		return validator.validateAndDoOrThrow(
				task,
				c -> {
					if(entityRepository.containsKey(task.taskId())) {
						throw new IllegalArgumentException(String.format("An entry with ID [%s] already exists", task.taskId()));
						}
					return entityRepository.put(task.taskId(), task);
					}
		);
	}
	
	public Optional<Task> deleteById(final String id){
		Objects.requireNonNull(id);
		 
		if(entityRepository.containsKey(id)) {
			final Task t = entityRepository.get(id);
			entityRepository.remove(id);
			return Optional.of(t);
		}
		throw new IllegalArgumentException(String.format("No entry with ID [%s] exists", id));
	}
	
	
	public void update(final Task task){
		Objects.requireNonNull(task);
		
		if(entityRepository.containsKey(task.taskId())) {
			entityRepository.remove(task.taskId());
		}
		
		create(task);
	}
	
	
}
