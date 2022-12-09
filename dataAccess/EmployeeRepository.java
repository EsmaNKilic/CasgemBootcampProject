package com.kodlama.io.BootcampProject.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlama.io.BootcampProject.entities.users.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	boolean existsByNationality(String nationality);
}
