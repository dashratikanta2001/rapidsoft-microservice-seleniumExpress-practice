package com.employeeService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeeService.response.EmployeeResponse;
import com.employeeService.service.EmployeeService;

@RestController
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService employeeService;

	
	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable("id") int id)
	{
		EmployeeResponse employee = employeeService.getEmployeeById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}
	
	@GetMapping("/employees")
	public ResponseEntity<?> getEmployees() {
		
		List<EmployeeResponse> employeeList = employeeService.getAllEmployees();
		
		return ResponseEntity.status(HttpStatus.OK).body(employeeList);
	}
	
	@PostMapping("/employee")
	public ResponseEntity<?> saveEmployee(@RequestBody EmployeeResponse employeeResponse) {
		//TODO: process POST request
		EmployeeResponse response = employeeService.saveEmployee(employeeResponse);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
}
