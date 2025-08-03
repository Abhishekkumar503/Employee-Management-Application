package com.es.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.es.dto.DepartmentDTO;
import com.es.dto.OrganizationDto;


//@FeignClient(url = "http://localhost:8080" , value ="DEPARTMENT-SERVICE") // this is for version > 2022
@FeignClient("ORGANIZATION-SERVICE")
public interface APIClientForOrganiztion {

	@GetMapping("api/os/{code}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("code") String organizationCode);
}
