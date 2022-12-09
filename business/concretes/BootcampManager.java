package com.kodlama.io.BootcampProject.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.kodlama.io.BootcampProject.business.abstracts.BootcampService;
import com.kodlama.io.BootcampProject.business.abstracts.InstructorService;
import com.kodlama.io.BootcampProject.business.constants.Messages;
import com.kodlama.io.BootcampProject.business.requests.Bootcamps.CreateBootcampRequest;
import com.kodlama.io.BootcampProject.business.requests.Bootcamps.UpdateBootcampRequest;
import com.kodlama.io.BootcampProject.business.responses.Bootcamps.CreateBootcampResponse;
import com.kodlama.io.BootcampProject.business.responses.Bootcamps.GetAllBootcampResponse;
import com.kodlama.io.BootcampProject.business.responses.Bootcamps.GetBootcampResponse;
import com.kodlama.io.BootcampProject.business.responses.Bootcamps.UpdateBootcampResponse;
import com.kodlama.io.BootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlama.io.BootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;
import com.kodlama.io.BootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.SuccessResult;
import com.kodlama.io.BootcampProject.dataAccess.BootcampRepository;
import com.kodlama.io.BootcampProject.entities.Bootcamp;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BootcampManager implements BootcampService {
	
	private InstructorService instructorService;
	private BootcampRepository bootcampRepository;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllBootcampResponse>> getAll() {
		
		List<Bootcamp> bootcamps = this.bootcampRepository.findAll();
		
		List<GetAllBootcampResponse> response = bootcamps.stream()
				.map(bootcamp->this.modelMapperService.forResponse()
				.map(bootcamp, GetAllBootcampResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllBootcampResponse>>(response);
	}

	@Override
	public DataResult<GetBootcampResponse> getById(int id) {
		
		checkIfBootcampExistById(id);
		
		Bootcamp bootcamp = this.bootcampRepository.findById(id).get();
		
		GetBootcampResponse response = this.modelMapperService.forResponse().map(bootcamp, GetBootcampResponse.class);

		return new SuccessDataResult<GetBootcampResponse>(response);
	}

	@Override
	public DataResult<CreateBootcampResponse> add(CreateBootcampRequest createBootcampRequest) {
		
		Bootcamp bootcamp = this.modelMapperService.forRequest()
				.map(createBootcampRequest, Bootcamp.class);
		bootcamp.setId(0);
		
		this.bootcampRepository.save(bootcamp);
		
		CreateBootcampResponse createBootcampResponse= this.modelMapperService.forResponse()
				.map(bootcamp, CreateBootcampResponse.class);
		
		return new SuccessDataResult<CreateBootcampResponse>(createBootcampResponse,Messages.BootcampCreated);
	}

	@Override
	public DataResult<UpdateBootcampResponse> update(UpdateBootcampRequest updateBootcampRequest) {
		
		checkIfBootcampExistById(updateBootcampRequest.getId());
		
		checkIfInstructorExistsById(updateBootcampRequest.getInstructorId());
		checkIfDateStartBiggerThanDateEnd(updateBootcampRequest.getDateStart(), updateBootcampRequest.getDateEnd());
		
		Bootcamp bootcamp = this.modelMapperService.forRequest()
				.map(updateBootcampRequest, Bootcamp.class);
		bootcamp.setId(updateBootcampRequest.getId());
		
		this.bootcampRepository.save(bootcamp);
		
		UpdateBootcampResponse updateBootcampResponse = this.modelMapperService.forResponse()
				.map(bootcamp, UpdateBootcampResponse.class);
		
		return new SuccessDataResult<UpdateBootcampResponse>(updateBootcampResponse,Messages.BootcampUpdated);
	}

	@Override
	public Result delete(int id) {
		
		checkIfBootcampExistById(id);
		
		this.bootcampRepository.deleteById(id);
		
		return new SuccessResult(Messages.BootcampDeleted);
	}


	
	
	// CONTROLS
	
	
	public void checkIfBootcampExistById(int id) {
		
		if(!this.bootcampRepository.existsById(id)) {
			
			throw new BusinessException(Messages.BootcampNotExists);
		}	
	}
	
	
	private void checkIfDateStartBiggerThanDateEnd(@NotNull LocalDate dateStart, LocalDate dateEnd) {
		if(dateStart.isAfter(dateEnd) || dateStart.isEqual(dateEnd)) {
			throw new BusinessException("Başlama tarihi bitiş tarihinden önce olamaz!");
		}
	}
	
	public void checkIfBootcampActivated(int id) {
		
		Bootcamp bootcamp = bootcampRepository.findById(id).get();
		
		if(bootcamp.getState() == 2) {
			
			throw new BusinessException(Messages.BootcampNotActivated);
		}
	}

	
	private void checkIfInstructorExistsById(int id) {
		
		instructorService.getById(id);
	}


}
