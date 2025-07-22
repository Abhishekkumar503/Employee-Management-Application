package com.es.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.es.dto.ApiResponseDto;
import com.es.dto.EmployeeDTO;
import com.es.service.EmployeeService;

@RestController
@RequestMapping("api/es")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<EmployeeDTO> createEmployeeEntry(@RequestBody EmployeeDTO employeeDTO)
	{
		return new ResponseEntity<EmployeeDTO>(employeeService.createNewEmployee(employeeDTO),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAllDepartment ()
	{
		return new ResponseEntity<List<EmployeeDTO>>(employeeService.getAllEmployee(),HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id)
	{
		return new ResponseEntity<EmployeeDTO>(employeeService.getEmployeeById(id),HttpStatus.OK);
	}
	
	@GetMapping("detail/{id}")
	public ResponseEntity<ApiResponseDto> getEmployeDetails(@PathVariable Long id)
	{
		return new ResponseEntity<ApiResponseDto>(employeeService.getEmployeDetails(id),HttpStatus.OK);
	}

}
