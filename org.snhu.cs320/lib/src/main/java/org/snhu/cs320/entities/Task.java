package org.snhu.cs320.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record Task(
	@NotBlank(message = "Id is a required field")
	@Size(max = 10, message = "Id cannot be longer than {max} characters")
	String taskId,
	@NotBlank(message = "Task Name is a required field")
	@Size(max = 20, message = "Task Name cannot be longer than {max} characters")
	String taskName,
	@NotBlank(message = "Description is a required field")
	@Size(max = 50, message = "Description cannot be longer than {max} characters")
	String description
		) {
	
}
