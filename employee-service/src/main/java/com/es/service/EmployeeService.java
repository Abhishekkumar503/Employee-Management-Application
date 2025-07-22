package com.es.service;

import java.util.List;

import com.es.dto.EmployeeDTO;

public interface EmployeeService {

	EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);

	List<EmployeeDTO> getAllEmployee();

	EmployeeDTO getEmployeeById(Long id);

}
