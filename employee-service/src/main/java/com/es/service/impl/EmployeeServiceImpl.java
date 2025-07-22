package com.es.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.es.dto.EmployeeDTO;
import com.es.entity.Employee;
import com.es.repo.EmployeeRepo;
import com.es.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
		// TODO Auto-generated method stub
		return modelMapper.map(employeeRepo.save(modelMapper.map(employeeDTO, Employee.class)), EmployeeDTO.class) ;
	}

	@Override
	public List<EmployeeDTO> getAllEmployee() {
		// TODO Auto-generated method stub
		return  employeeRepo.findAll().stream().map((Employee) -> modelMapper.map(Employee, EmployeeDTO.class)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		return modelMapper.map(employeeRepo.findById(id).orElseThrow(()-> new RuntimeException("Id not found")),EmployeeDTO.class);
	}

}
