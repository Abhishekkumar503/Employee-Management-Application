package com.es.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.es.dto.DepartmentDTO;

@FeignClient(url = "http://localhost:8080" , value ="DEPARTMENT-SERVICE")
public interface APIClient {

	@GetMapping("/api/ds/{code}")
	public ResponseEntity<DepartmentDTO> getDepartmentByCode(@PathVariable String code);
}
