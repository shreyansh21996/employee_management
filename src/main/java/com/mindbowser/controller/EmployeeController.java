package com.mindbowser.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mindbowser.dto.EmployeeDTO;
import com.mindbowser.service.EmployeeService;
import com.mindbowser.utility.Messages;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author shreyansh_soni
 * @version 1.0
 */
@Api(tags = "Employee Controller RESTfulApi")
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;
   

    /**
     * @param EmployeeDTO
     * @return EmployeeDTO
     */
    @PostMapping
    @ApiOperation(value = "Add Employee", notes = "POST API to Add Employee")
    public ResponseEntity<?> createEmployee(
            @ApiParam(value = "Employee Object to Add") @RequestBody EmployeeDTO employeeDTO) {
        logger.info("Inside EmployeeController.createEmployee()");
        if (employeeService.existsByEmployeeeMobileNo(employeeDTO.getMobile())) {
            return new ResponseEntity<>("Employee " + employeeDTO.getMobile() + Messages.ALREADY_REGISTERED, HttpStatus.CONFLICT);
        }
        EmployeeDTO employeeDTOResponce = employeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>(employeeDTOResponce, HttpStatus.OK);
    }
    
    
    /**
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param sortIn
     * @return Page<EmployeeDTO>
     */
    @GetMapping(value = "/page")
    @ApiOperation(value = "Get all employee Records", notes = "GET API to Fatch All Employee ? By Page")
    public ResponseEntity<Page<EmployeeDTO>> retrieveAllEmployeesWithPagination(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "firstName") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortIn) {
        logger.info("inside EmployeeController.retrieveAllEmployeesWithPagination()");
        Page<EmployeeDTO> employeeDTO = employeeService.retrieveAllEmployees(pageNo,pageSize, sortBy,sortIn);
        if (employeeDTO.isEmpty()) {
        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }
    
    /**
     * @param EmployeeDTO
     * @return EmployeeDTO
     */
    @PutMapping
	@ApiOperation(value = "Update Employee Record by Id", notes = "PUT API to Update Employee Object By Id")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public ResponseEntity<?> updateEmployeeById(
			@ApiParam(value = "Update Employee object") @RequestBody EmployeeDTO employeeDTO) {
		logger.info("inside EmployeeController.updateEmployeeById()");
		EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(employeeDTO);
		if (updatedEmployeeDTO!=null) {
			return new ResponseEntity<>(updatedEmployeeDTO, HttpStatus.OK);	
		} else {
			return new ResponseEntity<>("Employee" + employeeDTO.getId() + Messages.NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}
    
    /**
     * @param id
     * @return boolean
     */
    @DeleteMapping
    @ApiOperation(value = "Delete Employee Record by Id", notes = "DELETE API to Delete Employee Record By Id")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> DeleteById(@ApiParam(value = "Employee Record By Id from which Employee object will delete from database table") @RequestParam(value = "id") Long id) {
        logger.info("inside EmployeeController.DeleteById()");
        if (employeeService.deleteEmployeeById(id)) {
            return new ResponseEntity<>("Employee id :" + id + Messages.DELETE_SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee id : " + id +Messages.DELETE_FAILURE, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    /**
     * @return List<EmployeeDTO>
     */
    @GetMapping
    @ApiOperation(value = "Get All Employee Record", notes = "GET API to Fetch All Employee Record's")
    public ResponseEntity<?> getAllEmployee() {
        logger.info("Inside EmployeeController.getAllEmployee()");
        List<EmployeeDTO> listOfEmployee = employeeService.getAllEmployees();
        if (!listOfEmployee.isEmpty()) {
            return new ResponseEntity<>(listOfEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee Record's"+Messages.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

}
