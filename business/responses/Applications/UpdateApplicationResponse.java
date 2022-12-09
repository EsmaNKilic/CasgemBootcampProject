package com.kodlama.io.BootcampProject.business.responses.Applications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicationResponse {
	private int id;
	private int state;
	private int bootcampId;
	private int applicantId;
}
