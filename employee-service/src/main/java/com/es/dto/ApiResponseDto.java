package com.es.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto {
	private EmployeeDTO employee;
	private DepartmentDTO department;
	private OrganizationDto organization;
}
