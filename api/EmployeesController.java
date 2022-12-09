package com.kodlama.io.BootcampProject.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlama.io.BootcampProject.business.abstracts.EmployeeService;
import com.kodlama.io.BootcampProject.business.requests.Employees.CreateEmployeeRequest;
import com.kodlama.io.BootcampProject.business.requests.Employees.UpdateEmployeeRequest;
import com.kodlama.io.BootcampProject.business.responses.Employees.CreateEmployeeResponse;
import com.kodlama.io.BootcampProject.business.responses.Employees.GetAllEmployeeResponse;
import com.kodlama.io.BootcampProject.business.responses.Employees.GetEmployeeResponse;
import com.kodlama.io.BootcampProject.business.responses.Employees.UpdateEmployeeResponse;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController 
@RequestMapping("/api/employees")
public class EmployeesController {

	private EmployeeService employeeService;
	
	  @GetMapping()
	  public DataResult<List<GetAllEmployeeResponse>> getAll(){
		  return this.employeeService.getAll();
		}
		
	  @GetMapping("/{id}")
	  public DataResult<GetEmployeeResponse> getById(@PathVariable int id){
		  return this.employeeService.getById(id);
		}
	
	  @PostMapping() 
	  public DataResult<CreateEmployeeResponse> add(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest) { 
		  return this.employeeService.add(createEmployeeRequest); }
	  
	  @PutMapping() 
	  public DataResult<UpdateEmployeeResponse> update(@Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest) { 
		  return this.employeeService.update(updateEmployeeRequest); }
	  
	  @DeleteMapping("/{id}") 
	  public Result delete(@PathVariable int id) { 
		  return this.employeeService.delete(id); 
		  
	  }
	 
}
