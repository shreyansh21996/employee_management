package com.mindbowser.entity;

import java.util.Date;

import javax.persistence.Column;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


public abstract class BaseEntity {

    @ApiModelProperty(value = "CreatedAt of Employee Management", required = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CreatedDate
    @Column(updatable = false)
	private Date createdAt;

    @ApiModelProperty(value = "UpdatedAt of Employee Management", required = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @UpdateTimestamp
	private Date updatedAt;

    @ApiModelProperty(value = "CreatedBy of Employee Management", required = true)
	@CreatedBy
	@Column(updatable = false)
	private String createdBy;

    @ApiModelProperty(value = "ModifiedBy of Employee Management", required = true)
	@LastModifiedBy
	private String modifiedBy;
    
    public BaseEntity() {
		
	}

	public BaseEntity(Date createdAt, Date updatedAt, String createdBy, String modifiedBy) {
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
