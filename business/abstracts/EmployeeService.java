package com.kodlama.io.BootcampProject.business.abstracts;

import java.util.List;

import com.kodlama.io.BootcampProject.business.requests.Employees.CreateEmployeeRequest;
import com.kodlama.io.BootcampProject.business.requests.Employees.UpdateEmployeeRequest;
import com.kodlama.io.BootcampProject.business.responses.Employees.CreateEmployeeResponse;
import com.kodlama.io.BootcampProject.business.responses.Employees.GetAllEmployeeResponse;
import com.kodlama.io.BootcampProject.business.responses.Employees.GetEmployeeResponse;
import com.kodlama.io.BootcampProject.business.responses.Employees.UpdateEmployeeResponse;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;

public interface EmployeeService {
	DataResult<List<GetAllEmployeeResponse>> getAll();
	DataResult<GetEmployeeResponse> getById(int id);
	DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest createEmployeeRequest);
	DataResult<UpdateEmployeeResponse> update(UpdateEmployeeRequest updateEmployeeRequest);
	Result delete(int id);
	
}
