package com.mindbowser.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BaseDTO {
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date createdAt;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date updatedAt;
	private String createdBy;
	private String modifiedBy;
	
	public BaseDTO() {
		
	}

	public BaseDTO(Date createdAt, Date updatedAt, String createdBy, String modifiedBy) {
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
