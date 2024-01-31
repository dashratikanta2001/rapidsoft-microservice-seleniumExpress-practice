package com.employeeService.openfeignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employeeService.response.AddressResponse;

@FeignClient(name = "ADDRESS-SERVICE", path = "/address-app/api")
public interface AddressClient { //proxy

	@GetMapping("/address/{employeeId}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int id);
	
}
