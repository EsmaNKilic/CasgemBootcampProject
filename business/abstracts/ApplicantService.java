package com.kodlama.io.BootcampProject.business.abstracts;

import java.util.List;

import com.kodlama.io.BootcampProject.business.requests.Applicants.CreateApplicantRequest;
import com.kodlama.io.BootcampProject.business.requests.Applicants.UpdateApplicantRequest;
import com.kodlama.io.BootcampProject.business.responses.Applicants.CreateApplicantResponse;
import com.kodlama.io.BootcampProject.business.responses.Applicants.GetAllApplicantResponse;
import com.kodlama.io.BootcampProject.business.responses.Applicants.GetApplicantResponse;
import com.kodlama.io.BootcampProject.business.responses.Applicants.UpdateApplicantResponse;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;

public interface ApplicantService {
	DataResult<List<GetAllApplicantResponse>> getAll();
	DataResult<GetApplicantResponse> getById(int id);
	DataResult<CreateApplicantResponse> add(CreateApplicantRequest createApplicantRequest);
	DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest updateApplicantRequest);
	Result delete(int id);

}
