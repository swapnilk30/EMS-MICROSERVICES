package com.example.employee.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.service.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
