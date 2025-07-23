package com.es.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.es.dto.ApiResponseDto;
import com.es.dto.DepartmentDTO;
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
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	WebClient webCleint;
	

	@Override
	public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
		// TODO Auto-generated method stub
		return modelMapper.map(employeeRepo.save(modelMapper.map(employeeDTO, Employee.class)), EmployeeDTO.class);
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

	@Override
	public ApiResponseDto getEmployeDetails(Long id) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepo.findById(id).orElseThrow(()-> new RuntimeException("Id not found"));
//		below lone return in response entity so need to convert in DepartmentDTO
		ResponseEntity<DepartmentDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/ds/"+ emp.getDepartmentCode() , DepartmentDTO.class);
		
//		Conversion into DepartmentDTo
		DepartmentDTO  departmentDTO = responseEntity.getBody();
		System.out.println("In RestTemplate!!" + departmentDTO.toString());
		
//		Providing values into API respose 
		ApiResponseDto apiResponseDto = new ApiResponseDto();
		apiResponseDto.setEmployeeDTO(modelMapper.map(emp,EmployeeDTO.class));
		apiResponseDto.setDepartmentDTO(departmentDTO);
		
		return apiResponseDto;
	}
	
	@Override
	public ApiResponseDto getEmployeDetailsWebClient(Long id) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepo.findById(id).orElseThrow(()-> new RuntimeException("Id not found"));

//		fetch by Webclient instance
		DepartmentDTO departmentDTO = webCleint.get()
		.uri("http://localhost:8080/api/ds/"+ emp.getDepartmentCode())
		.retrieve()
		.bodyToMono(DepartmentDTO.class)
		.block();
		
		System.out.println("In WebClient!!" + departmentDTO.toString());
		
//		Providing values into API respose 
		ApiResponseDto apiResponseDto = new ApiResponseDto();
		apiResponseDto.setEmployeeDTO(modelMapper.map(emp,EmployeeDTO.class));
		apiResponseDto.setDepartmentDTO(departmentDTO);
		
		return apiResponseDto;
	}
	
	

}
