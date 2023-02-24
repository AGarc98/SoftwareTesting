package org.snhu.cs320.entities;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record Contact(
		@NotBlank(message = "Id is a required field")
		@Size(max = 10, message = "Id cannot be longer than {max} characters")
		String contactId,
		@NotBlank(message = "First Name is a required field")
		@Size(max = 10, message = "First Name cannot be longer than {max} characters")
		String firstName,
		@NotBlank(message = "Last Name is a required field")
		@Size(max = 10, message = "Last Name cannot be longer than {max} characters")
		String lastName,
		@NotBlank(message = "Phone Number is a required field")
		@Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
		String phoneNumber,
		@NotBlank(message = "Address is a required field")
		@Size(max = 30, message = "Address cannot be longer than {max} characters")
		String address
		) {

}
