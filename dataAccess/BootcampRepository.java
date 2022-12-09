package com.kodlama.io.BootcampProject.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlama.io.BootcampProject.entities.Bootcamp;

public interface BootcampRepository extends JpaRepository<Bootcamp, Integer> {

}
