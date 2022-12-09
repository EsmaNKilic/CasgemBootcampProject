package com.kodlama.io.BootcampProject.entities.application;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kodlama.io.BootcampProject.entities.Bootcamp;
import com.kodlama.io.BootcampProject.entities.users.Applicant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="applications")
public class Application {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;
	
	@Column(name="state")
	private int state;
	
	@ManyToOne
	@JoinColumn(name="applicant_id")
	private Applicant applicant;
	
	@ManyToOne
	@JoinColumn(name="bootcamp_id")
	private Bootcamp bootcamp;

}