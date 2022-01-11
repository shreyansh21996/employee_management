package com.mindbowser.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

public class SignupRequest {

	private String firstName;
	
	private String lastName;
	
	private String email;

	private String address;
	
	private Date birthDate;
	
	private String companyName;

	private Set<String> roles;
	
	@NotBlank

	@Size(min = 6, max = 40)
	private String password;
	
	public SignupRequest() {
		
	}
	

	public SignupRequest(String firstName, String lastName, String email, String address, Date birthDate,
			String companyName, Set<String> roles, @NotBlank @Size(min = 6, max = 40) String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.birthDate = birthDate;
		this.companyName = companyName;
		this.roles = roles;
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public Set<String> getRoles() {
		return roles;
	}


	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return String.format("SignupRequest[firstName='%s', lastName='%s']",firstName, lastName);
	}
}
