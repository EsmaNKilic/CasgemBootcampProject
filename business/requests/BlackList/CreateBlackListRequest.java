package com.kodlama.io.BootcampProject.business.requests.BlackList;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBlackListRequest {
	@NotBlank(message = "This field cannot be left blank.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	@NotBlank(message = "This field cannot be left blank.")
	private String reason;
	
	@NotBlank(message = "This field cannot be left blank.")
	private int applicantId;
}
