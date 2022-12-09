package com.kodlama.io.BootcampProject.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlama.io.BootcampProject.entities.BlackList;

public interface BlackListRepository extends JpaRepository<BlackList, Integer> {
	
	BlackList findByApplicantId(int id);
}
