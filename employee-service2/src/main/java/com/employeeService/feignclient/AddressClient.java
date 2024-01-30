package com.employeeService.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employeeService.response.AddressResponse;

//http://localhost:8081/address-app/api/address/1

@FeignClient(name = "address-app", url = "http://localhost:8081", path = "/address-app/api")
public interface AddressClient { //This is a proxy type

	@GetMapping("/address/{id}")
	AddressResponse getAddressByEmployeeId(@PathVariable ("id") int id);
}
