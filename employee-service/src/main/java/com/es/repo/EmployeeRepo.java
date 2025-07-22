package com.es.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.es.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}
