package com.mindbowser.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;


public class LoginRequest implements Serializable {
	private static final long serialVersionUID = 774361628448131285L;
	
	@Email
    private String email;
    private String password;
    
    public LoginRequest() {
		
	}

	public LoginRequest(@Email String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
