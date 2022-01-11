package com.mindbowser.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mindbowser.dto.EmployeeDTO;

public interface EmployeeService {

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    
    public Page<EmployeeDTO> retrieveAllEmployees(Integer pageNo, Integer pageSize,String sortBy, String sortIn);
    
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);
    
    public boolean deleteEmployeeById(Long id);

    public List<EmployeeDTO> getAllEmployees();
    
    public boolean existsByEmployeeeMobileNo(Long mobileNo);

}
