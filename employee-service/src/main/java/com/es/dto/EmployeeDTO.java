package com.es.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class EmployeeDTO {

	private Long id;
	private String firstName;
	private String LastName;
	private String email;
	private String departmentCode;
	private String organizationCode;
}
