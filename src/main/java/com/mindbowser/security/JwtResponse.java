
package com.mindbowser.security;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class JwtResponse implements Serializable {
	
	private static final long serialVersionUID = -5831397033480755831L;
	
	private String token;
	private String type = "Bearer";
	private Long id;
    private String firstName;
	private String lastName;
	private String email;
	private List<String> roles;
	private String address;
	private Date birthDate;
	private String companyName;
	
	public JwtResponse() {
		
	}
	
	public JwtResponse(String token, Long id, String firstName, String lastName, String email,
			List<String> roles, String address, Date birthDate, String companyName) {
		this.token = token;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
		this.address = address;
		this.birthDate = birthDate;
		this.companyName = companyName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
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
	
}
