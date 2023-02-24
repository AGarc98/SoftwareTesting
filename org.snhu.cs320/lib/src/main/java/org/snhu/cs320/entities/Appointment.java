package org.snhu.cs320.entities;

import java.util.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record Appointment(
	@NotBlank(message = "Id is a required field")
	@Size(max = 10, message = "Id cannot be longer than {max} characters")
	String appointmentId,
	@NotNull(message = "Date is a required field")
	@FutureOrPresent(message = "Date must be in the future or present")
	Date date,
	@NotBlank(message = "Description is a required field")
	@Size(max = 50, message = "Description cannot be longer than {max} characters")
	String description
		) {
	
}

