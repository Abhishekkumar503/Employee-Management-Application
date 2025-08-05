package com.ds.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DepartmentDTO model Information")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO
{
	@Schema(description = "Department ID")
	private long id;
	
	@Schema(description = "Department Name")
	private String departmentName;
	
	@Schema(description = "Department Description")
	private String departmentDescription;
	
	@Schema(description = "Department Code")
	private String departmentCode;
}
