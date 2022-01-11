package com.mindbowser.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mindbowser.dto.EmployeeDTO;
import com.mindbowser.entity.EmployeeEntity;
import com.mindbowser.mapper.EmployeeMapper;
import com.mindbowser.repository.EmployeeRepository;
import com.mindbowser.service.EmployeeService;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
	private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public EmployeeDTO addEmployee(EmployeeDTO EmployeeDTO) {
		logger.info("addEmployee() in EmployeeService");
		EmployeeEntity EmployeeEntity = employeeRepository.save(EmployeeMapper.INSTANCE.getEntityFromDTO(EmployeeDTO));
		return EmployeeMapper.INSTANCE.getDTOFromEntity(EmployeeEntity);
	}
	
	@Override
	public Page<EmployeeDTO> retrieveAllEmployees(Integer pageNo, Integer pageSize, String sortBy, String sortIn) {
		logger.info("retrieveAllEmployees() in EmployeeService");
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.Direction.fromString(sortIn), sortBy);
		Page<EmployeeEntity> pagedResult = employeeRepository.findAll(paging);
		return pagedResult.map(property -> EmployeeMapper.INSTANCE.getDTOFromEntity(property));
	}

	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
		logger.info("updateEmployee() in EmployeeService");
		Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(employeeDTO.getId());
			EmployeeDTO existEmployeeDTO = EmployeeMapper.INSTANCE.getDTOFromEntity(employeeEntityOptional.get());
			existEmployeeDTO.setFirstName(employeeDTO.getFirstName());
			existEmployeeDTO.setLastName(employeeDTO.getLastName());
			existEmployeeDTO.setAddress(employeeDTO.getAddress());
			existEmployeeDTO.setBirthDate(employeeDTO.getBirthDate());
			existEmployeeDTO.setMobile(employeeDTO.getMobile());
			existEmployeeDTO.setCity(employeeDTO.getCity());
			existEmployeeDTO.setModifiedBy(employeeDTO.getModifiedBy());
			existEmployeeDTO.setUpdatedAt(employeeDTO.getUpdatedAt());
			EmployeeEntity employeeEntity = employeeRepository.save(EmployeeMapper.INSTANCE.getEntityFromDTO(existEmployeeDTO));
			return EmployeeMapper.INSTANCE.getDTOFromEntity(employeeEntity);
	}
	
	@Override
	public boolean deleteEmployeeById(Long id) {
		logger.info("deleteEmployeeById() in Employeeservice - Id: {}", id);
		Optional<EmployeeEntity> EmployeeEntityOptional = employeeRepository.findById(id);
		if (EmployeeEntityOptional.isPresent()) {
			employeeRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Override
	public List<EmployeeDTO> getAllEmployees() {
		logger.info("getAllEmployees() in EmployeeService");
		List<EmployeeEntity> listOfEmployees = employeeRepository.findAll();
		List<EmployeeDTO> EmployeeDTO = EmployeeMapper.INSTANCE.getAllDTOsFromEntities(listOfEmployees);
		return EmployeeDTO;
	}

	@Override
	public boolean existsByEmployeeeMobileNo(Long mobileNo) {
		if (employeeRepository.existsByMobile(mobileNo)) {
			return true;
		}
		return false;
	}

}
