package com.kodlama.io.BootcampProject.entities.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kodlama.io.BootcampProject.entities.Bootcamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instructors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends User {

	@Column(name = "companyName")
	private String companyName;
	
	@OneToMany(mappedBy="instructor")
	private List<Bootcamp> bootcamps;

}
