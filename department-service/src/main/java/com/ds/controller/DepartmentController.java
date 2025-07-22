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

@RestController
@RequestMapping("/api/ds")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@PostMapping
	public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO)
	{
		return new ResponseEntity<DepartmentDTO>(departmentService.createNewDepartment(departmentDTO),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<DepartmentDTO>> getAllDepartment ()
	{
		return new ResponseEntity<List<DepartmentDTO>>(departmentService.getAlldepartment(),HttpStatus.OK);
	}
	
	@GetMapping("{code}")
	public ResponseEntity<DepartmentDTO> getDepartmentByCode(@PathVariable String code)
	{
		return new ResponseEntity<DepartmentDTO>(departmentService.getDepartmentByCode(code),HttpStatus.OK);
	}

}
