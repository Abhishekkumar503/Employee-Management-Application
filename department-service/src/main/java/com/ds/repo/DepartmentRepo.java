package com.ds.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long>{

	Department findByDepartmentCode(String code);

}
