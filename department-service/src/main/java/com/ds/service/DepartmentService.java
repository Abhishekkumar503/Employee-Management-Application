package com.ds.service;

import java.util.List;

import com.ds.dto.DepartmentDTO;

public interface DepartmentService {

	DepartmentDTO createNewDepartment(DepartmentDTO departmentDTO);

	List<DepartmentDTO> getAlldepartment();

	DepartmentDTO getDepartmentByCode(String code);

}
