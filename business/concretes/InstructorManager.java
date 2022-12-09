package com.kodlama.io.BootcampProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlama.io.BootcampProject.business.abstracts.InstructorService;
import com.kodlama.io.BootcampProject.business.constants.Messages;
import com.kodlama.io.BootcampProject.business.requests.Instructors.CreateInstructorRequest;
import com.kodlama.io.BootcampProject.business.requests.Instructors.UpdateInstructorRequest;
import com.kodlama.io.BootcampProject.business.responses.Instructors.CreateInstructorResponse;
import com.kodlama.io.BootcampProject.business.responses.Instructors.GetAllInstructorResponse;
import com.kodlama.io.BootcampProject.business.responses.Instructors.GetInstructorResponse;
import com.kodlama.io.BootcampProject.business.responses.Instructors.UpdateInstructorResponse;
import com.kodlama.io.BootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlama.io.BootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;
import com.kodlama.io.BootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.SuccessResult;
import com.kodlama.io.BootcampProject.dataAccess.InstructorRepository;
import com.kodlama.io.BootcampProject.entities.users.Instructor;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class InstructorManager implements InstructorService {
	
	private InstructorRepository instructorRepository;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllInstructorResponse>> getAll() {
		
		List<Instructor> instructors = this.instructorRepository.findAll();
		
		List<GetAllInstructorResponse> response = instructors.stream()
				.map(instructor->this.modelMapperService.forResponse()
				.map(instructor, GetAllInstructorResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllInstructorResponse>>(response);
	}

	@Override
	public DataResult<GetInstructorResponse> getById(int id) {
		
		checkIfInstructorExistsById(id);
		
		Instructor instructors = this.instructorRepository.findById(id).get();
		
		GetInstructorResponse response = this.modelMapperService.forResponse().map(instructors, GetInstructorResponse.class);
		
		return new SuccessDataResult<GetInstructorResponse>(response);
	}

	@Override
	public DataResult<CreateInstructorResponse> add(CreateInstructorRequest createInstructorRequest) {
		
		checkIfInstructorExistsByNationality(createInstructorRequest.getNationality());
		
		Instructor instructor = this.modelMapperService.forRequest()
				.map(createInstructorRequest, Instructor.class);
		
		this.instructorRepository.save(instructor);
		
		CreateInstructorResponse createInstructorResponse = this.modelMapperService.forResponse()
				.map(instructor, CreateInstructorResponse.class);
		
		return new SuccessDataResult<CreateInstructorResponse>(createInstructorResponse,Messages.InstructorCreated);
	}
	
	@Override
	public DataResult<UpdateInstructorResponse> update(UpdateInstructorRequest updateInstructorRequest) {
		
		checkIfInstructorExistsById(updateInstructorRequest.getId());
		checkIfInstructorExistsByNationality(updateInstructorRequest.getNationality());
		
		Instructor instructor = this.modelMapperService.forRequest()
				.map(updateInstructorRequest, Instructor.class);
		
		instructor.setId(updateInstructorRequest.getId());
		
		this.instructorRepository.save(instructor);
		
		UpdateInstructorResponse updateInstructorResponse = this.modelMapperService.forResponse()
				.map(instructor, UpdateInstructorResponse.class);
		
		return new SuccessDataResult<UpdateInstructorResponse>(updateInstructorResponse,Messages.InstructorUpdated);
	}

	@Override
	public Result delete(int id) {
		
		checkIfInstructorExistsById(id);
		
		this.instructorRepository.deleteById(id);
		
		return new SuccessResult(Messages.InstructorDeleted);
	}
	
		
	// CONTROLS
	
	private void checkIfInstructorExistsByNationality(String nationality) {

		if(this.instructorRepository.existsByNationality(nationality)) {
			
			throw new BusinessException(Messages.InstructorExists);
		}
	}
	
	private void checkIfInstructorExistsById(int id) {

		if(!this.instructorRepository.existsById(id)) {
			
			throw new BusinessException(Messages.InstructorNotExists);
		}
	}
}
