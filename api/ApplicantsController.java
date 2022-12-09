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

import com.kodlama.io.BootcampProject.business.abstracts.ApplicantService;
import com.kodlama.io.BootcampProject.business.requests.Applicants.CreateApplicantRequest;
import com.kodlama.io.BootcampProject.business.requests.Applicants.UpdateApplicantRequest;
import com.kodlama.io.BootcampProject.business.responses.Applicants.CreateApplicantResponse;
import com.kodlama.io.BootcampProject.business.responses.Applicants.GetAllApplicantResponse;
import com.kodlama.io.BootcampProject.business.responses.Applicants.GetApplicantResponse;
import com.kodlama.io.BootcampProject.business.responses.Applicants.UpdateApplicantResponse;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController 
@RequestMapping("/api/applicants")
public class ApplicantsController {
	
	private ApplicantService applicantService;
	
	  @GetMapping("/{id}")
	  public DataResult<GetApplicantResponse> getById(@PathVariable int id){
		  return this.applicantService.getById(id);
	  }
	  
	  @GetMapping()
	  public DataResult<List<GetAllApplicantResponse>> getAll(){
		  return this.applicantService.getAll();
	  }
	  
	
	  @PostMapping() 
	  public DataResult<CreateApplicantResponse> add(@Valid @RequestBody CreateApplicantRequest createApplicantRequest) { 
		  return this.applicantService.add(createApplicantRequest); 
		  
	  }
	  
	  @PutMapping() 
	  public DataResult<UpdateApplicantResponse> update(@Valid @RequestBody UpdateApplicantRequest updateApplicantRequest) { 
		  return this.applicantService.update(updateApplicantRequest);  
	  }
	  
	  @DeleteMapping("/{id}") 
	  public Result delete(@PathVariable int id) { 
		  return this.applicantService.delete(id); 
	  }

}
