package com.addressService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.addressService.response.AddressResponse;
import com.addressService.service.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	private AddressService addressService;

	@GetMapping("/address/{employeeId}")
	public ResponseEntity<?> getAddressByEmployeeId(@PathVariable("employeeId") int id)
	{
		AddressResponse addressResponse = addressService.getAddressByEmployeeId(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
	}
	
	@GetMapping("/address")
	public ResponseEntity<?> getAllAddress() {
		List<AddressResponse> addressResponses =addressService.getAllAddress();
		return ResponseEntity.status(HttpStatus.OK).body(addressResponses);
	}
	
	@PostMapping("/address")
	public ResponseEntity<?> addAddress(@RequestBody AddressResponse addressResponse) {
		//TODO: process POST request
		System.out.println( "Address is calling and empid = "+addressResponse.getEmployeeId());
		AddressResponse response = addressService.addAddress(addressResponse);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
}
