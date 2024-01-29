package com.employeeService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.employeeService.response.EmployeeResponse;

@RestController
public class EmployeeController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee")
	public String getEmployee()
	{
		//return the address data along with the employee data

		//get the data from address service by hitting this http://localhost:8080/address
		String address =restTemplate.getForObject("http://localhost:8080/address", String.class);
		
		return "Name: Ratikanta, email: ratikanta@gmail.com " + address;
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable("id") int id)
	{
		EmployeeResponse employee = employeeService.getEmployeeById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}
}
