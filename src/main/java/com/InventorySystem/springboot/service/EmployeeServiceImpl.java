package com.InventorySystem.springboot.service;

import java.util.List;
import java.util.Optional;

import com.InventorySystem.springboot.Enum.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.InventorySystem.springboot.exception.ResourceNotFoundException;
import com.InventorySystem.springboot.model.Employee;
import com.InventorySystem.springboot.repository.EmployeeRepository;


@Service
@Transactional
public class EmployeeServiceImpl implements UserDetailsService,EmployeeService{


	@Autowired
	private EmployeeRepository employeeRepository;


	@Override
	public Employee createEmployee(Employee employee) {
		employee.setEmployeeRole(EmployeeRole.User);
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Optional<Employee> employeeDb = this.employeeRepository.findById(employee.getId());

		if(employeeDb.isPresent()) {
			Employee employeeUpdate = employeeDb.get();
			employeeUpdate.setId(employee.getId());
			employeeUpdate.setEmployeeNumber(employee.getEmployeeNumber());
			employeeUpdate.setName(employee.getName());
			employee.setPassword(employee.getPassword());
			employeeRepository.save(employeeUpdate);
			return employeeUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + employee.getId());
		}		
	}

	@Override
	public List<Employee> getAllEmployee() {
		return this.employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long employeeId) {

		Optional<Employee> employeeDb = this.employeeRepository.findById(employeeId);

		if(employeeDb.isPresent()) {
			return employeeDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + employeeId);
		}
	}

	@Override
	public void deleteEmployee(long employeeId) {
		Optional<Employee> employeeDb = this.employeeRepository.findById(employeeId);

		if(employeeDb.isPresent()) {
			this.employeeRepository.delete(employeeDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + employeeId);
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return employeeRepository.findByEmployeeNumber(username)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Doesn't Exist"));
	}
}