package com.kodlama.io.BootcampProject.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlama.io.BootcampProject.entities.users.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer>{
	
	boolean existsByNationality(String nationality);
	
}
