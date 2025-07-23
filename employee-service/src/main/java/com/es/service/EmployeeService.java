package com.es.service;

import java.util.List;

import com.es.dto.ApiResponseDto;
import com.es.dto.EmployeeDTO;

public interface EmployeeService {

	EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);

	List<EmployeeDTO> getAllEmployee();

	EmployeeDTO getEmployeeById(Long id);

	ApiResponseDto getEmployeDetails(Long id);

	ApiResponseDto getEmployeDetailsWebClient(Long id);

	ApiResponseDto getEmployeDetailsFeignClient(Long id);

}
