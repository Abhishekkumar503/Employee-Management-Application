package com.ds.controller;

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

import com.ds.dto.DepartmentDTO;
import com.ds.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name= "Department Service - DepartmentController")
@RestController
@RequestMapping("/api/ds")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@Operation(
			
			summary = "Save Department Rest API",
			description = "Save Department REST API is used to save department object in database")
	
	@ApiResponse(
			responseCode = "201",
			description = "HTTP status 201 Created" 
			)
	@PostMapping
	public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO)
	{
		return new ResponseEntity<DepartmentDTO>(departmentService.createNewDepartment(departmentDTO),HttpStatus.CREATED);
	}
	
	@Operation(
			
			summary = "Get Department Rest API",
			description = "Get Department REST API is used to get department object from a database")
	
	@ApiResponse(
			responseCode = "200",
			description = "HTTP status 200 SUCCESS" 
			)
	@GetMapping
	public ResponseEntity<List<DepartmentDTO>> getAllDepartment ()
	{
		return new ResponseEntity<List<DepartmentDTO>>(departmentService.getAlldepartment(),HttpStatus.OK);
	}
	
	@Operation(
		
		summary = "Get Department by Code Rest API",
		description = "Get Department REST API is used to get department object from a database")

	@ApiResponse(
		responseCode = "200",
		description = "HTTP status 200 SUCCESS" 
		)
	@GetMapping("{code}")
	public ResponseEntity<DepartmentDTO> getDepartmentByCode(@PathVariable String code)
	{
		return new ResponseEntity<DepartmentDTO>(departmentService.getDepartmentByCode(code),HttpStatus.OK);
	}

}
