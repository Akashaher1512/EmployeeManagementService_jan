package com.emp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.dto.EmployeeDto;
import com.emp.entity.Employee;
import com.emp.repository.EmployeeRepository;
import com.emp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee emp = dtoToEmp(employeeDto);
		
		Employee savedEmployee = employeeRepository.save(emp);
	
		return empToDto(savedEmployee);
	}

	
	@Override
	public String deleteEmployee(int id) {
		employeeRepository.deleteById(id);

		return String.format("Employee deleted successfully with id : %s", id);
	}
	

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto, int id) {
		
		Employee emp = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not fount with given id"));
		
		emp.setFirstName(employeeDto.getFirstName());
		emp.setEmail(employeeDto.getEmail());
		emp.setLastName(employeeDto.getLastName());
		emp.setPhone(employeeDto.getPhone());
		
		Employee updatedEmp = employeeRepository.save(emp);
		
		return empToDto(updatedEmp);
	}

	
	@Override
	public List<EmployeeDto> getAllEmployee() {
		
		List<Employee> emps = employeeRepository.findAll();
		
		List<EmployeeDto> empDtos = emps.stream().map((emp)-> empToDto(emp) ).collect(Collectors.toList());
		
		
		return empDtos;
	}

	@Override
	public EmployeeDto getEmployeeById(int id) {
		Employee emp = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not fount with given id"));

		return empToDto(emp);
	}

	@Override
	public EmployeeDto getEmployeeByEmailId(String email) {
		Employee employee = employeeRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Employee not fount with given id"));
		
		return empToDto(employee);
	}

	// entity to dto
	private EmployeeDto empToDto(Employee employee ) {
		
		EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
		return dto;
	}
	
	// dto to entity
	private Employee dtoToEmp(EmployeeDto employeeDto) {
		return modelMapper.map(employeeDto, Employee.class);
	}
	
	
}
