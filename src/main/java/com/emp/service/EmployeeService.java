package com.emp.service;

import java.util.List;

import com.emp.dto.EmployeeDto;

public interface EmployeeService {
	// create
	EmployeeDto createEmployee(EmployeeDto employeeDto);

	// delete
	String deleteEmployee(int id);

	// update
	EmployeeDto updateEmployee(EmployeeDto employeeDto, int id);

	// getall
	List<EmployeeDto> getAllEmployee();

	// getbyid
	EmployeeDto getEmployeeById(int id);

	// getbyemail
	EmployeeDto getEmployeeByEmailId(String email);
	
	// get by name
}
