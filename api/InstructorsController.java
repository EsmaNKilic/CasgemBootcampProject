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

import com.kodlama.io.BootcampProject.business.abstracts.InstructorService;
import com.kodlama.io.BootcampProject.business.requests.Instructors.CreateInstructorRequest;
import com.kodlama.io.BootcampProject.business.requests.Instructors.UpdateInstructorRequest;
import com.kodlama.io.BootcampProject.business.responses.Instructors.CreateInstructorResponse;
import com.kodlama.io.BootcampProject.business.responses.Instructors.GetAllInstructorResponse;
import com.kodlama.io.BootcampProject.business.responses.Instructors.GetInstructorResponse;
import com.kodlama.io.BootcampProject.business.responses.Instructors.UpdateInstructorResponse;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@AllArgsConstructor 
@RestController 
@RequestMapping("/api/instructors")
public class InstructorsController {

	private InstructorService instructorService;
	
	@GetMapping("/{id}")
	public DataResult<GetInstructorResponse> getById(@PathVariable int id){
	return this.instructorService.getById(id);
	}
	
	@GetMapping()
	public DataResult<List<GetAllInstructorResponse>> getAll(){
	return this.instructorService.getAll();
	}
	
	@PostMapping()
	public DataResult<CreateInstructorResponse> add(@Valid @RequestBody CreateInstructorRequest creatInstructorRequest) {
		return this.instructorService.add(creatInstructorRequest);
	}
	
	@PutMapping()
	public DataResult<UpdateInstructorResponse> update(@Valid @RequestBody UpdateInstructorRequest updateInstructorRequest) {
		return this.instructorService.update(updateInstructorRequest);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return this.instructorService.delete(id);
	}
}
 