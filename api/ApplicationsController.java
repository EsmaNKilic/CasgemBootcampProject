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

import com.kodlama.io.BootcampProject.business.abstracts.ApplicationService;
import com.kodlama.io.BootcampProject.business.requests.Applications.CreateApplicationRequest;
import com.kodlama.io.BootcampProject.business.requests.Applications.UpdateApplicationRequest;
import com.kodlama.io.BootcampProject.business.responses.Applications.CreateApplicationResponse;
import com.kodlama.io.BootcampProject.business.responses.Applications.GetAllApplicationResponse;
import com.kodlama.io.BootcampProject.business.responses.Applications.GetApplicationResponse;
import com.kodlama.io.BootcampProject.business.responses.Applications.UpdateApplicationResponse;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/applications")
public class ApplicationsController {
	
	private ApplicationService applicationService;
	
	@GetMapping("/{id}")
	public DataResult<GetApplicationResponse> getById(@PathVariable int id){
		return this.applicationService.getById(id);
	}
	
	@GetMapping()
	public DataResult<List<GetAllApplicationResponse>> getAll(){
		return this.applicationService.getAll();
	}
	
	@PostMapping()
	public DataResult<CreateApplicationResponse> add(@Valid @RequestBody CreateApplicationRequest createApplicationRequest){
		return this.applicationService.add(createApplicationRequest);
	}
	
	@PutMapping()
	public DataResult<UpdateApplicationResponse> update(@Valid @RequestBody UpdateApplicationRequest updateApplicationRequest){
		return this.applicationService.update(updateApplicationRequest);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return this.applicationService.delete(id);
	}
}
