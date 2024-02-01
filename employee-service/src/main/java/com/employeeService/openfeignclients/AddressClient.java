package com.employeeService.openfeignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.employeeService.response.AddressResponse;

@FeignClient(name = "ADDRESS-SERVICE", path = "/address-app/api")
public interface AddressClient { //proxy

	@GetMapping("/address/{employeeId}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int id);
	
	@GetMapping("/address")
	public ResponseEntity<List<AddressResponse>> getAllAddress();
	
	@PostMapping("/address")
	public ResponseEntity<AddressResponse> addAddress(@RequestBody AddressResponse addressResponse);
}
