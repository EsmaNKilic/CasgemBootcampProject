package com.kodlama.io.BootcampProject.business.abstracts;

import java.util.List;

import com.kodlama.io.BootcampProject.business.requests.BlackList.CreateBlackListRequest;
import com.kodlama.io.BootcampProject.business.requests.BlackList.UpdateBlackListRequest;
import com.kodlama.io.BootcampProject.business.responses.BlackList.CreateBlackListResponse;
import com.kodlama.io.BootcampProject.business.responses.BlackList.GetAllBlackListResponse;
import com.kodlama.io.BootcampProject.business.responses.BlackList.GetBlackListResponse;
import com.kodlama.io.BootcampProject.business.responses.BlackList.UpdateBlackListResponse;
import com.kodlama.io.BootcampProject.core.utilities.results.DataResult;
import com.kodlama.io.BootcampProject.core.utilities.results.Result;

public interface BlackListService {
	DataResult<List<GetAllBlackListResponse>> getAll();
	DataResult<GetBlackListResponse> getById(int id);
	DataResult<CreateBlackListResponse> add(CreateBlackListRequest createBlackListRequest);
	DataResult<UpdateBlackListResponse> update(UpdateBlackListRequest updateBlackListRequest);
	Result delete(int id);
	DataResult<GetBlackListResponse> getByApplicantId(int applicantId);
	
	void checkIfApplicantInBlackListById(int id);
	
	
}


