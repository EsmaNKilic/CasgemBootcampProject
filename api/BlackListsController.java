package com.kodlama.io.BootcampProject.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlama.io.BootcampProject.business.abstracts.BlackListService;
import com.kodlama.io.BootcampProject.business.requests.BlackList.CreateBlackListRequest;
import com.kodlama.io.BootcampProject.business.requests.BlackList.UpdateBlackListRequest;
import com.kodlama.io.BootcampProject.business.responses.BlackList.CreateBlackListResponse;
import com.kodlama.io.BootcampProject.business.responses.BlackList.GetAllBlackListResponse;
import com.kodlama.io.BootcampProject.business.responses.BlackList.GetBlackListResponse;
import com.kodlama.io.BootcampProject.business.responses.BlackList.UpdateBlackListResponse;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController 
@RequestMapping("/api/blacklists")
public class BlackListsController {
	
	private BlackListService blackListService;
	
	 @GetMapping("/{id}")
	  public DataResult<GetBlackListResponse> getById(@PathVariable int id){
		  return this.blackListService.getById(id);
	  }
	  
	  @GetMapping()
	  public DataResult<List<GetAllBlackListResponse>> getAll(){
		  return this.blackListService.getAll();
	  }
	  
	
	  @PostMapping() 
	  public DataResult<CreateBlackListResponse> add(@Valid CreateBlackListRequest createBlackListRequest) { 
		  return this.blackListService.add(createBlackListRequest); 
		  
	  }
	  
	  @PutMapping() 
	  public DataResult<UpdateBlackListResponse> update(@Valid UpdateBlackListRequest updateBlackListRequest) { 
		  return this.blackListService.update(updateBlackListRequest);  
	  }
	  
	  @DeleteMapping("/{id}") 
	  public Result delete(@PathVariable int id) { 
		  return this.blackListService.delete(id); 
	  }
	  
	  @GetMapping("/{id}")
		public DataResult<GetBlackListResponse> getByApplicantId(@PathVariable int applicantId) {
			return blackListService.getByApplicantId(applicantId);
		}
}
