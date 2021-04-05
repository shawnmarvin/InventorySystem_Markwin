package com.InventorySystem.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.InventorySystem.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}

