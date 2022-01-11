package com.mindbowser.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "USERS")
public class ManagerEntity implements Serializable {
	private static final long serialVersionUID = 2917891364624655974L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "PK ID of Manager", required = true)
	@Column(name = "ID", unique = true)
	private Long id;

	@ApiModelProperty(value = "First name of Manager", required = true)
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	private String firstName;

	@ApiModelProperty(value = "Last Name of Manager", required = true)
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	private String lastName;

	@Email
	@ApiModelProperty(value = "Email of Manager", required = true)
	@Column(name = "EMAIL", length = 255, unique = true, nullable = false)
	private String email;

	@Column(name = "PASSWORD", length = 60, nullable = false)
	@ApiModelProperty(value = "Password of Manager", required = true)
	@JsonIgnore
	private String password;

	@ApiModelProperty(value = "Role of Manager", required = true)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "USER_ROLES", 
				joinColumns = @JoinColumn(name = "USER_ID"), 
				inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<RoleEntity> role = new HashSet<>();

	@ApiModelProperty(value = "Address of Manager", required = true)
	@Column(name = "ADDRESS", length = 255, nullable = false)
	private String address;

	@ApiModelProperty(value = "Birth Date of Manager", required = true)
	@Column(name = "BIRTH_DATE", length = 50, nullable = false)
	private Date birthDate;

	@ApiModelProperty(value = "Company Name of Manager", required = true)
	@Column(name = "COMPANY_NAME", length = 50, nullable = false)
	private String companyname;

	public ManagerEntity() {
		
	}
	
	public ManagerEntity(@Email String email, String password) {
		this.email = email;
		this.password = password;
	}

	public ManagerEntity(String firstName, String lastName, @Email String email, String password,
			String address, Date birthDate, String companyname) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.birthDate = birthDate;
		this.companyname = companyname;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleEntity> getRole() {
		return role;
	}

	public void setRole(Set<RoleEntity> role) {
		this.role = role;
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

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

}
