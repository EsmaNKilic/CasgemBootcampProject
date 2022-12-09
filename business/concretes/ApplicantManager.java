package com.kodlama.io.BootcampProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlama.io.BootcampProject.business.abstracts.ApplicantService;
import com.kodlama.io.BootcampProject.business.constants.Messages;
import com.kodlama.io.BootcampProject.business.requests.Applicants.CreateApplicantRequest;
import com.kodlama.io.BootcampProject.business.requests.Applicants.UpdateApplicantRequest;
import com.kodlama.io.BootcampProject.business.responses.Applicants.CreateApplicantResponse;
import com.kodlama.io.BootcampProject.business.responses.Applicants.GetAllApplicantResponse;
import com.kodlama.io.BootcampProject.business.responses.Applicants.GetApplicantResponse;
import com.kodlama.io.BootcampProject.business.responses.Applicants.UpdateApplicantResponse;
import com.kodlama.io.BootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlama.io.BootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;
import com.kodlama.io.BootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.SuccessResult;
import com.kodlama.io.BootcampProject.dataAccess.ApplicantRepository;
import com.kodlama.io.BootcampProject.entities.users.Applicant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ApplicantManager implements ApplicantService {

	private ApplicantRepository applicantRepository;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllApplicantResponse>> getAll() {

		List<Applicant> applicants = this.applicantRepository.findAll();

		List<GetAllApplicantResponse> response = applicants.stream()
				.map(applicant -> this.modelMapperService.forResponse().map(applicant, GetAllApplicantResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllApplicantResponse>>(response);
	}

	@Override
	public DataResult<GetApplicantResponse> getById(int id) {
		
		checkIfApplicantExistsById(id);

		Applicant applicant = this.applicantRepository.findById(id).get();

		GetApplicantResponse response = this.modelMapperService.forResponse().map(applicant,GetApplicantResponse.class);

		return new SuccessDataResult<GetApplicantResponse>(response);
	}

	@Override
	public DataResult<CreateApplicantResponse> add(CreateApplicantRequest createApplicantRequest) {

		checkIfApplicantExistsByNationality(createApplicantRequest.getNationality());

		Applicant applicant = this.modelMapperService.forRequest().map(createApplicantRequest, Applicant.class);

		this.applicantRepository.save(applicant);

		CreateApplicantResponse createApplicantResponse = this.modelMapperService.forResponse().map(applicant,
				CreateApplicantResponse.class);

		return new SuccessDataResult<CreateApplicantResponse>(createApplicantResponse, Messages.ApplicantCreated);
	}

	@Override
	public DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest updateApplicantRequest) {

		checkIfApplicantExistsById(updateApplicantRequest.getId());
		checkIfApplicantExistsByNationality(updateApplicantRequest.getNationality());

		Applicant applicant = this.modelMapperService.forRequest().map(updateApplicantRequest, Applicant.class);
		applicant.setId(updateApplicantRequest.getId());
		
		this.applicantRepository.save(applicant);

		UpdateApplicantResponse updateApplicantResponse = this.modelMapperService.forResponse().map(applicant,
				UpdateApplicantResponse.class);

		return new SuccessDataResult<UpdateApplicantResponse>(updateApplicantResponse, Messages.ApplicantUpdated);

	}

	@Override
	public Result delete(int id) {

		checkIfApplicantExistsById(id);

		this.applicantRepository.deleteById(id);

		return new SuccessResult(Messages.InstructorDeleted);
	}
	

	
	
	// CONTROLS

	private void checkIfApplicantExistsByNationality(String nationality) {

		if (this.applicantRepository.existsByNationality(nationality)) {

			throw new BusinessException(Messages.ApplicantExists);
		}
	}
	
	
	private void checkIfApplicantExistsById(int id) {

		if (!this.applicantRepository.existsById(id)) {

			throw new BusinessException(Messages.ApplicantNotExists);
		}
	}

}
