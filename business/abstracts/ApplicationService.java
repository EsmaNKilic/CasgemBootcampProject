package com.kodlama.io.BootcampProject.business.abstracts;

import java.util.List;

import com.kodlama.io.BootcampProject.business.requests.Applications.CreateApplicationRequest;
import com.kodlama.io.BootcampProject.business.requests.Applications.UpdateApplicationRequest;
import com.kodlama.io.BootcampProject.business.responses.Applications.CreateApplicationResponse;
import com.kodlama.io.BootcampProject.business.responses.Applications.GetAllApplicationResponse;
import com.kodlama.io.BootcampProject.business.responses.Applications.GetApplicationResponse;
import com.kodlama.io.BootcampProject.business.responses.Applications.UpdateApplicationResponse;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;

public interface ApplicationService {
	DataResult<List<GetAllApplicationResponse>> getAll();
	DataResult<GetApplicationResponse> getById(int id);
	DataResult<CreateApplicationResponse> add(CreateApplicationRequest createApplicationRequest);
	DataResult<UpdateApplicationResponse> update(UpdateApplicationRequest updateApplicantRequest);
	Result delete(int id);

}
