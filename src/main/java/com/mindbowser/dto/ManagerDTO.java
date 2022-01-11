
package com.mindbowser.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindbowser.entity.ManagerEntity;


public class ManagerDTO implements UserDetails {
	
	private static final long serialVersionUID = -4653548989143119009L;

	private Long id;

	private String firstName;
	
	private String lastName;
	
	private String email;

	@JsonIgnore
	private String password;
	
	private String address;
	
	private Date birthDate;
	
	private String companyName;
	
	public ManagerDTO() {

	}
	
	public ManagerDTO(Long id, String firstName, String lastName, String email, String password, String address,
			Date birthDate, String companyName, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.birthDate = birthDate;
		this.companyName = companyName;
		this.authorities = authorities;
	}

	private Collection<? extends GrantedAuthority> authorities;

	public static ManagerDTO build(ManagerEntity user) {
		List<GrantedAuthority> authorities = user.getRole().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
				.collect(Collectors.toList());
		return new ManagerDTO(user.getId(), user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(), user.getAddress(),user.getBirthDate(), user.getCompanyname(), authorities);
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getCompanyName() {
		return companyName;
	}


	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return firstName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ManagerDTO user = (ManagerDTO) o;
		return Objects.equals(id, user.id);
	}

}
