package com.kodlama.io.BootcampProject.business.requests.Applications;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicationRequest {
	@NotBlank(message = "This field cannot be left blank.")
	private int state;
	
	@NotBlank(message = "This field cannot be left blank.")
	private int bootcampId;
	
	@NotBlank(message = "This field cannot be left blank.")
	private int applicantId;
}
