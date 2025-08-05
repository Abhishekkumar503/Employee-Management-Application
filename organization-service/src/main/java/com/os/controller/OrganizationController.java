package com.os.controller;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.os.dto.OrganizationDto;
import com.os.service.OrganizationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name= "Organization Service - OrganizationController")
@RestController
@RequestMapping("api/os")
public class OrganizationController {

	@Autowired
    private OrganizationService organizationService;

    // Build Save Organization REST API
	@Operation(
			
			summary = "Save Organization Rest API",
			description = "Save Organization REST API is used to save organization object in database")
	
	@ApiResponse(
			responseCode = "201",
			description = "HTTP status 201 Created" 
			)
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    // Build Get Organization by Code REST API
	@Operation(
			
			summary = "Get Organization by Code Rest API",
			description = "Get Organization REST API is used to get organization object from a database")

		@ApiResponse(
			responseCode = "200",
			description = "HTTP status 200 SUCCESS" 
			)
    @GetMapping("{code}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("code") String organizationCode){
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return ResponseEntity.ok(organizationDto);
    }

}