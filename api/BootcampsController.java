package com.kodlama.io.BootcampProject.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kodlama.io.BootcampProject.business.abstracts.BootcampService;
import com.kodlama.io.BootcampProject.business.requests.Bootcamps.CreateBootcampRequest;
import com.kodlama.io.BootcampProject.business.requests.Bootcamps.UpdateBootcampRequest;
import com.kodlama.io.BootcampProject.business.responses.Bootcamps.CreateBootcampResponse;
import com.kodlama.io.BootcampProject.business.responses.Bootcamps.GetAllBootcampResponse;
import com.kodlama.io.BootcampProject.business.responses.Bootcamps.GetBootcampResponse;
import com.kodlama.io.BootcampProject.business.responses.Bootcamps.UpdateBootcampResponse;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("api/bootcamps")
@RestController
public class BootcampsController {

	private BootcampService bootcampService;
	
	@GetMapping("/{id}")
	public DataResult<GetBootcampResponse> getById(@PathVariable int id){
		return this.bootcampService.getById(id);
	}
	
	@GetMapping()
	public DataResult<List<GetAllBootcampResponse>> getAll(){
		return this.bootcampService.getAll();
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public DataResult<CreateBootcampResponse> add(@Valid @RequestBody CreateBootcampRequest createBootcampRequest){
		return this.bootcampService.add(createBootcampRequest);
	}
	
	@PutMapping()
	public DataResult<UpdateBootcampResponse> update(@Valid @RequestBody UpdateBootcampRequest updateBootcampRequest){
		return this.bootcampService.update(updateBootcampRequest);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id){
		return this.bootcampService.delete(id);
	}
	
}
