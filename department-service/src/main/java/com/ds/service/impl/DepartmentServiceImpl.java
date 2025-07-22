package com.ds.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.dto.DepartmentDTO;
import com.ds.entity.Department;
import com.ds.repo.DepartmentRepo;
import com.ds.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	DepartmentRepo departmentRepo;

	@Override
	public DepartmentDTO createNewDepartment(DepartmentDTO departmentDTO) {
		// TODO Auto-generated method stub
		return modelMapper.map(departmentRepo.save(modelMapper.map(departmentDTO, Department.class)),DepartmentDTO.class);
	}

	@Override
	public List<DepartmentDTO> getAlldepartment() {
		// TODO Auto-generated method stub
		return departmentRepo.findAll().stream().map((Department) -> modelMapper.map(Department, DepartmentDTO.class)).collect(Collectors.toList());
	}

	@Override
	public DepartmentDTO getDepartmentByCode(String code) {
		// TODO Auto-generated method stub
		return modelMapper.map(departmentRepo.findByDepartmentCode(code),DepartmentDTO.class);
	}

}
