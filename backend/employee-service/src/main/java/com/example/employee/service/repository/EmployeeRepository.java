package com.example.employee.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.service.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    boolean existsByEmail(String email);
    
    Optional<Employee> findByEmpCodeAndCompanyName(String empCode,String companyName);
}
