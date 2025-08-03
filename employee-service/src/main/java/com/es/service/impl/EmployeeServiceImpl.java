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
import com.es.feign.APIClient;
import com.es.repo.EmployeeRepo;
import com.es.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

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
	
	@Autowired
	APIClient aPIClient;
	
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
	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartmentByRestTemplate")
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
	
	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartmentByWebClient")
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
	
	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartmentByFeignClient")
	@Override
	public ApiResponseDto getEmployeDetailsFeignClient(Long id) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepo.findById(id).orElseThrow(()-> new RuntimeException("Id not found"));

//		fetch by FeignClient instance
		DepartmentDTO departmentDTO = aPIClient.getDepartmentByCode(emp.getDepartmentCode()).getBody();
		
		System.out.println("In FeignClient!!" + departmentDTO.toString());
		
//		Providing values into API respose 
		ApiResponseDto apiResponseDto = new ApiResponseDto();
		apiResponseDto.setEmployeeDTO(modelMapper.map(emp,EmployeeDTO.class));
		apiResponseDto.setDepartmentDTO(departmentDTO);
		
		return apiResponseDto;
	}
	
	// Calling Default Department by  RestTemplate
	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	public ApiResponseDto getDefaultDepartmentByRestTemplate(Long id , Exception exception) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepo.findById(id).orElseThrow(()-> new RuntimeException("Id not found"));
		
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setId(404);
		departmentDTO.setDepartmentName("RestTemplate Default Department");
		departmentDTO.setDepartmentCode("RDD");
		departmentDTO.setDepartmentDescription("RestTemplate Default Department ( when Department service not working )");
		
		
		System.out.println("In RestTemplate!!" + departmentDTO.toString());
		
//		Providing values into API respose 
		ApiResponseDto apiResponseDto = new ApiResponseDto();
		apiResponseDto.setEmployeeDTO(modelMapper.map(emp,EmployeeDTO.class));
		apiResponseDto.setDepartmentDTO(departmentDTO);
		
		return apiResponseDto;
	}
	
	
	public ApiResponseDto getDefaultDepartmentByWebClient(Long id , Exception exception) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepo.findById(id).orElseThrow(()-> new RuntimeException("Id not found"));

		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setId(404);
		departmentDTO.setDepartmentName("WebClient Default Department");
		departmentDTO.setDepartmentCode("WDD");
		departmentDTO.setDepartmentDescription("WebClient Default Department ( when Department service not working )");
		
		
		System.out.println("In WebClient!!" + departmentDTO.toString());
		
//		Providing values into API respose 
		ApiResponseDto apiResponseDto = new ApiResponseDto();
		apiResponseDto.setEmployeeDTO(modelMapper.map(emp,EmployeeDTO.class));
		apiResponseDto.setDepartmentDTO(departmentDTO);
		
		return apiResponseDto;
	}
	
	
	public ApiResponseDto getDefaultDepartmentByFeignClient(Long id , Exception exception) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepo.findById(id).orElseThrow(()-> new RuntimeException("Id not found"));

		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setId(404);
		departmentDTO.setDepartmentName("Feign Default Department");
		departmentDTO.setDepartmentCode("FDD");
		departmentDTO.setDepartmentDescription("feign Default Department ( when Department service not working )");
		
		System.out.println("In FeignClient!!" + departmentDTO.toString());
		
//		Providing values into API respose 
		ApiResponseDto apiResponseDto = new ApiResponseDto();
		apiResponseDto.setEmployeeDTO(modelMapper.map(emp,EmployeeDTO.class));
		apiResponseDto.setDepartmentDTO(departmentDTO);
		
		return apiResponseDto;
	}
	

}
