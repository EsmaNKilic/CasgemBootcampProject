package com.kodlama.io.BootcampProject.business.requests.Bootcamps;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBootcampRequest {

	private int id;
	
	private int instructorId;
	
	@NotBlank(message = "This field cannot be left blank.")
	@Size(min = 3, message = "First name must be at least 3 characters")
	private String name;

	private int state;
	
	@NotNull(message = "This field cannot be left blank.")
	private LocalDate dateStart;
	
	@NotNull(message = "This field cannot be left blank.")
	private LocalDate dateEnd;
	
}
