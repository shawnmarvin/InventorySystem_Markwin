package com.InventorySystem.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.InventorySystem.springboot.model.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Optional<Employee> findByEmployeeNumber(String employeeNum);
}

