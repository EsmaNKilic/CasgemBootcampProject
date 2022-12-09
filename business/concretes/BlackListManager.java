package com.kodlama.io.BootcampProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlama.io.BootcampProject.business.abstracts.ApplicantService;
import com.kodlama.io.BootcampProject.business.abstracts.BlackListService;
import com.kodlama.io.BootcampProject.business.constants.Messages;
import com.kodlama.io.BootcampProject.business.requests.BlackList.CreateBlackListRequest;
import com.kodlama.io.BootcampProject.business.requests.BlackList.UpdateBlackListRequest;
import com.kodlama.io.BootcampProject.business.responses.BlackList.CreateBlackListResponse;
import com.kodlama.io.BootcampProject.business.responses.BlackList.GetAllBlackListResponse;
import com.kodlama.io.BootcampProject.business.responses.BlackList.GetBlackListResponse;
import com.kodlama.io.BootcampProject.business.responses.BlackList.UpdateBlackListResponse;
import com.kodlama.io.BootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlama.io.BootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;
import com.kodlama.io.BootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.SuccessResult;
import com.kodlama.io.BootcampProject.dataAccess.BlackListRepository;
import com.kodlama.io.BootcampProject.entities.BlackList;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlackListManager implements BlackListService{

	private ApplicantService applicantService;
	private BlackListRepository blackListRepository;
	private ModelMapperService modelMapperService;
	
	@Override
	public DataResult<List<GetAllBlackListResponse>> getAll() {
		
		List<BlackList> blackLists = this.blackListRepository.findAll();
		
		List<GetAllBlackListResponse> response = blackLists.stream()
				.map(blackList->this.modelMapperService.forResponse()
				.map(blackList, GetAllBlackListResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllBlackListResponse>>(response);
	}

	@Override
	public DataResult<GetBlackListResponse> getById(int id) {
		
		checkIfBlackListExistsById(id);
		
		BlackList blackList = this.blackListRepository.findById(id).get();
		
		GetBlackListResponse response = this.modelMapperService.forResponse().map(blackList, GetBlackListResponse.class);
		
		return new SuccessDataResult<GetBlackListResponse>(response);
	}

	@Override
	public DataResult<CreateBlackListResponse> add(CreateBlackListRequest createBlackListRequest) {
		
		checkIfApplicantExistsById(createBlackListRequest.getApplicantId());
		checkIfApplicantInBlackListById(createBlackListRequest.getApplicantId());
		
		BlackList blackList = this.modelMapperService.forRequest()
				.map(createBlackListRequest, BlackList.class);
		
		this.blackListRepository.save(blackList);
		
		CreateBlackListResponse createBlackListResponse = this.modelMapperService.forResponse()
				.map(blackList, CreateBlackListResponse.class);
		
		return new SuccessDataResult<CreateBlackListResponse>(createBlackListResponse,Messages.BlackListCreated);
	}

	@Override
	public DataResult<UpdateBlackListResponse> update(UpdateBlackListRequest updateBlackListRequest) {
		
		checkIfBlackListExistsById(updateBlackListRequest.getId());
	
		BlackList blackList = this.modelMapperService.forRequest()
				.map(updateBlackListRequest, BlackList.class);
		
		this.blackListRepository.save(blackList);
		
		UpdateBlackListResponse updateBlackListResponse = this.modelMapperService.forResponse()
				.map(blackList, UpdateBlackListResponse.class);
		
		return new SuccessDataResult<UpdateBlackListResponse>(updateBlackListResponse,Messages.BlackListUpdated);
	}

	@Override
	public Result delete(int id) {
		
		checkIfBlackListExistsById(id);
		
		this.blackListRepository.deleteById(id);
		
		return new SuccessResult(Messages.BlackListDeleted);
	}
	
	
	@Override
	public DataResult<GetBlackListResponse> getByApplicantId(int applicantId) {
		checkIfApplicantExistsInBlackListById(applicantId);
		BlackList blackList = blackListRepository.findByApplicantId(applicantId);
		GetBlackListResponse blackListResponse = modelMapperService.forResponse().map(blackList, GetBlackListResponse.class);
		return new SuccessDataResult<GetBlackListResponse>(blackListResponse);
	}
	
	@Override
	public void checkIfApplicantInBlackListById(int id) {
		
		if(this.blackListRepository.findByApplicantId(id) != null) {
			
			throw new BusinessException(Messages.ApplicantExists);
		}
	}

	
	// CONTROLS

	
	private void checkIfBlackListExistsById(int id) {
		
		if(!this.blackListRepository.existsById(id)) {
			
			throw new BusinessException(Messages.BlackListNotExists);
		}
	}
	
	
	private void checkIfApplicantExistsById(int id) {
		
		applicantService.getById(id);
	}
	

	private void checkIfApplicantExistsInBlackListById(int id) {
		
		if(blackListRepository.findByApplicantId(id) == null) {
			
			throw new BusinessException(Messages.ApplicantNotExists);
		}
	}
}



