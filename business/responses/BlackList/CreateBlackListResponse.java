package com.kodlama.io.BootcampProject.business.responses.BlackList;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBlackListResponse {
	private int id;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private String reason;
	private int applicantId;
}
