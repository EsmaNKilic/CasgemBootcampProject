package com.kodlama.io.BootcampProject.business.abstracts;

import java.util.List;

import com.kodlama.io.BootcampProject.business.requests.Bootcamps.CreateBootcampRequest;
import com.kodlama.io.BootcampProject.business.requests.Bootcamps.UpdateBootcampRequest;
import com.kodlama.io.BootcampProject.business.responses.Bootcamps.CreateBootcampResponse;
import com.kodlama.io.BootcampProject.business.responses.Bootcamps.GetAllBootcampResponse;
import com.kodlama.io.BootcampProject.business.responses.Bootcamps.GetBootcampResponse;
import com.kodlama.io.BootcampProject.business.responses.Bootcamps.UpdateBootcampResponse;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;

public interface BootcampService {
	DataResult<List<GetAllBootcampResponse>> getAll();
	DataResult<GetBootcampResponse> getById(int id);
	DataResult<CreateBootcampResponse> add(CreateBootcampRequest createBootcampRequest);
	DataResult<UpdateBootcampResponse> update(UpdateBootcampRequest updateBootcampRequest);
	Result delete(int id);

	void checkIfBootcampActivated(int id);
}
