package com.kodlama.io.BootcampProject.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlama.io.BootcampProject.entities.users.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer>{
	
	boolean existsByNationality(String nationality);
}
