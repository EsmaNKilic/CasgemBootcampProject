package com.kodlama.io.BootcampProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlama.io.BootcampProject.business.abstracts.ApplicantService;
import com.kodlama.io.BootcampProject.business.abstracts.ApplicationService;
import com.kodlama.io.BootcampProject.business.abstracts.BlackListService;
import com.kodlama.io.BootcampProject.business.abstracts.BootcampService;
import com.kodlama.io.BootcampProject.business.constants.Messages;
import com.kodlama.io.BootcampProject.business.requests.Applications.CreateApplicationRequest;
import com.kodlama.io.BootcampProject.business.requests.Applications.UpdateApplicationRequest;
import com.kodlama.io.BootcampProject.business.responses.Applications.CreateApplicationResponse;
import com.kodlama.io.BootcampProject.business.responses.Applications.GetAllApplicationResponse;
import com.kodlama.io.BootcampProject.business.responses.Applications.GetApplicationResponse;
import com.kodlama.io.BootcampProject.business.responses.Applications.UpdateApplicationResponse;
import com.kodlama.io.BootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlama.io.BootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;
import com.kodlama.io.BootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.SuccessResult;
import com.kodlama.io.BootcampProject.dataAccess.ApplicationRepository;
import com.kodlama.io.BootcampProject.entities.application.Application;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ApplicationManager implements ApplicationService{
	
	private BlackListService blackListService;
	private ApplicantService applicantService;
	private BootcampService bootcampService;
	private ApplicationRepository applicationRepository;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllApplicationResponse>> getAll() {
		
		List<Application> applications = this.applicationRepository.findAll();
		
		List<GetAllApplicationResponse> response = applications.stream()
				.map(application->this.modelMapperService.forResponse()
				.map(application, GetAllApplicationResponse.class)).collect(Collectors.toList());
				
		return new SuccessDataResult<List<GetAllApplicationResponse>>(response);
	}

	@Override
	public DataResult<GetApplicationResponse> getById(int id) {
		
		checkIfApplicationExistsById(id);
		
		Application application = this.applicationRepository.findById(id).get();
		
		GetApplicationResponse response = this.modelMapperService.forResponse().map(application, GetApplicationResponse.class);
		
		return new SuccessDataResult<GetApplicationResponse>(response);
	}

	@Override
	public DataResult<CreateApplicationResponse> add(CreateApplicationRequest createApplicationRequest) {
		
		checkIfApplicantExistsById(createApplicationRequest.getApplicantId());
		checkIfBootcampExistsById(createApplicationRequest.getBootcampId());
		checkIfApplicantInBlackListById(createApplicationRequest.getApplicantId());
		checkIfBootcampActivated(createApplicationRequest.getBootcampId());
		checkIfApplicantAppliedBefore(createApplicationRequest.getApplicantId());

		
		Application application = this.modelMapperService.forRequest()
				.map(createApplicationRequest, Application.class);
		
		this.applicationRepository.save(application);
		
		CreateApplicationResponse createApplicationResponse = this.modelMapperService.forResponse()
				.map(application, CreateApplicationResponse.class);
		
		return new SuccessDataResult<CreateApplicationResponse>(createApplicationResponse,Messages.ApplicationCreated);
	}

	@Override
	public DataResult<UpdateApplicationResponse> update(UpdateApplicationRequest updateApplicantRequest) {
		
		checkIfApplicationExistsById(updateApplicantRequest.getId());
		
		Application application = this.modelMapperService.forRequest()
				.map(updateApplicantRequest, Application.class);
		
		this.applicationRepository.save(application);
		
		UpdateApplicationResponse updateApplicationResponse = this.modelMapperService.forResponse()
				.map(application, UpdateApplicationResponse.class);
		
		return new SuccessDataResult<UpdateApplicationResponse>(updateApplicationResponse,Messages.ApplicationUpdated);
	}

	@Override
	public Result delete(int id) {
		
		checkIfApplicationExistsById(id);
		
		this.applicationRepository.deleteById(id);
		
		return new SuccessResult(Messages.ApplicationDeleted);
	}
	

	
	// CONTROLS
	
	
	private void checkIfApplicationExistsById(int id) {
		
		if(!this.applicationRepository.existsById(id)) {
			
			throw new BusinessException(Messages.ApplicationNotExists);
		}
	}

	private void checkIfApplicantExistsById(int id) {
		
		applicantService.getById(id);
	}
	

	private void checkIfBootcampExistsById(int id) {
		
		bootcampService.getById(id);
	}
	
	private void checkIfApplicantAppliedBefore(int applicantId) {
		
		if(applicationRepository.findByApplicantId(applicantId) != null){
			
			throw new BusinessException(Messages.ApplicantAppliedBefore);
		}
	}

	private void checkIfApplicantInBlackListById(int id){
		
		blackListService.checkIfApplicantInBlackListById(id);
	}
	
	private void checkIfBootcampActivated(int id) {
		
		bootcampService.checkIfBootcampActivated(id);
	}

}
