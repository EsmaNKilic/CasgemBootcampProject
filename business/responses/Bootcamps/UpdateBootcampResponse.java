package com.kodlama.io.BootcampProject.business.responses.Bootcamps;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBootcampResponse {
	private int id;
	private String name;
	private int state;
	private LocalDate dateStart;
	private LocalDate dateEnd;
	private int instructorId;
}
