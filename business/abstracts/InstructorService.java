package com.kodlama.io.BootcampProject.business.abstracts;

import java.util.List;

import com.kodlama.io.BootcampProject.business.requests.Instructors.CreateInstructorRequest;
import com.kodlama.io.BootcampProject.business.requests.Instructors.UpdateInstructorRequest;
import com.kodlama.io.BootcampProject.business.responses.Instructors.CreateInstructorResponse;
import com.kodlama.io.BootcampProject.business.responses.Instructors.GetAllInstructorResponse;
import com.kodlama.io.BootcampProject.business.responses.Instructors.GetInstructorResponse;
import com.kodlama.io.BootcampProject.business.responses.Instructors.UpdateInstructorResponse;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;

public interface InstructorService {
	DataResult<List<GetAllInstructorResponse>> getAll();
	DataResult<GetInstructorResponse> getById(int id);
	DataResult<CreateInstructorResponse> add(CreateInstructorRequest createInstructorRequest);
	DataResult<UpdateInstructorResponse> update(UpdateInstructorRequest updateInstructorRequest);
	Result delete(int id);
}
