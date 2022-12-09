package com.kodlama.io.BootcampProject.business.requests.Applicants;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicantRequest{
	
	@NotBlank(message = "This field cannot be left blank.")
	@Length(min = 11, max = 11, message = "National identity must be 11 characther")
	private String nationality;
	
	@NotBlank(message = "Bu alan boş bırakılamaz")
    @Length(min = 2, max = 50, message = "isim en az 2 en fazla 50 karakter olmalı.")
	private String firstName;
	
	@NotBlank(message = "Bu alan boş bırakılamaz")
    @Length(min = 2, max = 50, message = "soyisim en az 2 en fazla 50 karakter olmalı.")
	private String lastName;
	
	@NotNull(message="bu alan boş bırakılamaz")
	private LocalDate dateOfBirth;
	
	@Email(regexp = ".+[@].+[\\\\.].+", message = "Email must be valid")
	@NotBlank(message = "This field cannot be left blank.")
	private String email;
	
	@NotBlank(message = "This field cannot be left blank.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", 
	message = "Password must be minimum 8 characters, at least one letter, one number and one special character:")
	private String password;
	
	@NotBlank(message = "This field cannot be left blank.")
	private String about;
}
