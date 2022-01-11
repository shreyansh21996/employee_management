package com.mindbowser.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 2239515276543078484L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "PK ID of Employee", required = true)
	@Column(name = "ID", unique = true)
	private Long id;

	@ApiModelProperty(value = "First Name of Employee", required = true)
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	private String firstName;

	@ApiModelProperty(value = "Last Name of Employee", required = true)
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	private String lastName;

	@ApiModelProperty(value = "Address of Employee", required = true)
	@Column(name = "ADDRESS", length = 255, nullable = false)
	private String address;

	@ApiModelProperty(value = "Birth Date of Employee", required = true)
	@Column(name = "BIRTH_DATE", length = 50, nullable = false)
	private Date birthDate;

	@ApiModelProperty(value = "Mobile No. of Employee", required = true)
	@Column(name = "MOBILE", length = 11, nullable = false)
	private Long mobile;

	@ApiModelProperty(value = "City of Employee", required = true)
	@Column(name = "CITY", length = 50, nullable = false)
	private String city;
	
	public EmployeeEntity() {
		
	}

	public EmployeeEntity(Long id, String firstName, String lastName, String address, Date birthDate, Long mobile,
			String city) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.birthDate = birthDate;
		this.mobile = mobile;
		this.city = city;
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

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}


