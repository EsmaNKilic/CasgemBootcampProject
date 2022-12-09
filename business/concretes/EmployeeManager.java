package com.kodlama.io.BootcampProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlama.io.BootcampProject.business.abstracts.EmployeeService;
import com.kodlama.io.BootcampProject.business.constants.Messages;
import com.kodlama.io.BootcampProject.business.requests.Employees.CreateEmployeeRequest;
import com.kodlama.io.BootcampProject.business.requests.Employees.UpdateEmployeeRequest;
import com.kodlama.io.BootcampProject.business.responses.Employees.CreateEmployeeResponse;
import com.kodlama.io.BootcampProject.business.responses.Employees.GetAllEmployeeResponse;
import com.kodlama.io.BootcampProject.business.responses.Employees.GetEmployeeResponse;
import com.kodlama.io.BootcampProject.business.responses.Employees.UpdateEmployeeResponse;
import com.kodlama.io.BootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlama.io.BootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;
import com.kodlama.io.BootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.SuccessResult;
import com.kodlama.io.BootcampProject.dataAccess.EmployeeRepository;
import com.kodlama.io.BootcampProject.entities.users.Employee;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeManager implements EmployeeService{

	private EmployeeRepository employeeRepository;
	private ModelMapperService modelMapperService;

	
	@Override
	public DataResult<List<GetAllEmployeeResponse>> getAll() {
		
		List<Employee> employees = this.employeeRepository.findAll();
		
		List<GetAllEmployeeResponse> response = employees.stream()
				.map(employee->this.modelMapperService.forResponse()
				.map(employee, GetAllEmployeeResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllEmployeeResponse>>(response);
	}

	
	@Override
	public DataResult<GetEmployeeResponse> getById(int id) {
		
		checkIfEmployeeExistsById(id);
		
		Employee employee = this.employeeRepository.findById(id).get();
		
		GetEmployeeResponse response = this.modelMapperService.forResponse()
				.map(employee,GetEmployeeResponse.class);
		
		return new SuccessDataResult<GetEmployeeResponse>(response);
	}

	
	@Override 
	public DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest createEmployeeRequest) { 
		
		checkIfEmployeeExistsByNationality(createEmployeeRequest.getNationality());
		
		  Employee employee = this.modelMapperService.forRequest()
				  .map(createEmployeeRequest, Employee.class);
		  
		  this.employeeRepository.save(employee);
	  
		  CreateEmployeeResponse createEmployeeResponse = this.modelMapperService.forResponse()
				  .map(employee,CreateEmployeeResponse.class);
		  
		  return new SuccessDataResult<CreateEmployeeResponse>(createEmployeeResponse,Messages.EmployeeCreated);
		  
	}
	

	@Override 
	public DataResult<UpdateEmployeeResponse> update(UpdateEmployeeRequest updateEmployeeRequest) { 
		
		checkIfEmployeeExistsByNationality(updateEmployeeRequest.getNationality());
		
		  Employee employee = this.modelMapperService.forRequest()
				  .map(updateEmployeeRequest,Employee.class);
		  employee.setId(updateEmployeeRequest.getId());
		  
		  this.employeeRepository.save(employee);
		  
		  UpdateEmployeeResponse updateEmployeeResponse = this.modelMapperService.forResponse()
				  .map(employee, UpdateEmployeeResponse.class);
		  
		  return new SuccessDataResult<UpdateEmployeeResponse>(updateEmployeeResponse,Messages.EmployeeUpdated);
	  }
	 

	@Override
	public Result delete(int id) {
		
		checkIfEmployeeExistsById(id);
		
		this.employeeRepository.deleteById(id);
		
		return new SuccessResult(Messages.EmployeeDeleted);
	}
	
	
	// CONTROLS
	
	private void checkIfEmployeeExistsByNationality(String nationality) {

		if(this.employeeRepository.existsByNationality(nationality)) {
			
			throw new BusinessException(Messages.EmployeeExists);
		}
	}
	
	
	private void checkIfEmployeeExistsById(int id) {
		
		if(this.employeeRepository.existsById(id));
		
		throw new BusinessException(Messages.EmployeeNotExists);
	}


}
