package com.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.dto.EmployeeDto;
import com.emp.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	// create
	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto createEmployee = employeeService.createEmployee(employeeDto);
		
		return new ResponseEntity<EmployeeDto>(createEmployee , HttpStatus.CREATED );
	}
	
	//update
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto ,@PathVariable int id ){
		EmployeeDto employee = employeeService.updateEmployee(employeeDto, id);
		
		return new ResponseEntity<EmployeeDto>(employee , HttpStatus.OK );
	}
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id){
		String msg = employeeService.deleteEmployee(id);
		
		return new ResponseEntity<String>(msg , HttpStatus.OK );
	}
	
	//getbyid
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int id){
		EmployeeDto employeeById = employeeService.getEmployeeById(id);
		
		return new ResponseEntity<EmployeeDto>(employeeById , HttpStatus.OK );
	}
	
	// getall
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
		return new ResponseEntity<List<EmployeeDto>>( employeeService.getAllEmployee() , HttpStatus.OK);
	}
	
 	//getbyemail
	@GetMapping("/email/{email}")
	public ResponseEntity<EmployeeDto> getEmployeeByEmailId(@PathVariable String email){
		EmployeeDto employeeByemail = employeeService.getEmployeeByEmailId(email);
		
		return new ResponseEntity<EmployeeDto>(employeeByemail , HttpStatus.OK );
	}
	
}
