package com.kodlama.io.BootcampProject.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlama.io.BootcampProject.entities.application.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{
	
	Application findByApplicantId(int applicantId);
}
