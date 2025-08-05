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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/es")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Operation(
			
			summary = "Save Employee Rest API",
			description = "Save Employee REST API is used to save employee object in database")
	
	@ApiResponse(
			responseCode = "201",
			description = "HTTP status 201 Created" 
			)
	@PostMapping
	public ResponseEntity<EmployeeDTO> createEmployeeEntry(@RequestBody EmployeeDTO employeeDTO)
	{
		return new ResponseEntity<EmployeeDTO>(employeeService.createNewEmployee(employeeDTO),HttpStatus.CREATED);
	}
	
@Operation(
			
			summary = "Get Employee Rest API",
			description = "Get Employee REST API is used to get employee object from a database")
	
	@ApiResponse(
			responseCode = "200",
			description = "HTTP status 200 SUCCESS" 
			)
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAllDepartment ()
	{
		return new ResponseEntity<List<EmployeeDTO>>(employeeService.getAllEmployee(),HttpStatus.OK);
	}
	
	@Operation(
		
		summary = "Get Employee by ID Rest API",
		description = "Get Employee REST API is used to get employee object from a database")

	@ApiResponse(
		responseCode = "200",
		description = "HTTP status 200 SUCCESS" 
		)
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id)
	{
		return new ResponseEntity<EmployeeDTO>(employeeService.getEmployeeById(id),HttpStatus.OK);
	}
	
		@Operation(
			
			summary = "Get Employee by ID Rest API (rest-template)",
			description = "Get Employee REST API is used to get employee object from a database")

		@ApiResponse(
			responseCode = "200",
			description = "HTTP status 200 SUCCESS" 
			)
	@GetMapping("detail/rest-template/{id}")
	public ResponseEntity<ApiResponseDto> getEmployeDetails(@PathVariable Long id)
	{
		return new ResponseEntity<ApiResponseDto>(employeeService.getEmployeDetails(id),HttpStatus.OK);
	}
	
		@Operation(
				
			summary = "Get Employee by ID Rest API (web-client)",
			description = "Get Employee REST API is used to get employee object from a database")

		@ApiResponse(
			responseCode = "200",
			description = "HTTP status 200 SUCCESS" 
				)	
	@GetMapping("detail/web-client/{id}")
	public ResponseEntity<ApiResponseDto> getEmployeDetailsWebClient(@PathVariable Long id)
	{
		return new ResponseEntity<ApiResponseDto>(employeeService.getEmployeDetailsWebClient(id),HttpStatus.OK);
	}
	
		@Operation(
				
			summary = "Get Employee by ID Rest API (feign-client)",
			description = "Get Employee REST API is used to get employee object from a database")

		@ApiResponse(
			responseCode = "200",
			description = "HTTP status 200 SUCCESS" 
				)	
	@GetMapping("detail/feign-client/{id}")
	public ResponseEntity<ApiResponseDto> getEmployeDetailsFeignClient(@PathVariable Long id)
	{
		return new ResponseEntity<ApiResponseDto>(employeeService.getEmployeDetailsFeignClient(id),HttpStatus.OK);
	}

}
