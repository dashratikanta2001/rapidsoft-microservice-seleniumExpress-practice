package com.employeeService.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.employeeService.dao.EmployeeDao;
import com.employeeService.entity.Employee;
import com.employeeService.response.AddressResponse;
import com.employeeService.response.EmployeeResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
//	private RestTemplate restTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WebClient webClient;
	
	
//	public EmployeeService(@Value("${addressservice.base.url}") String addressBaseUrl,RestTemplateBuilder builder) {
//
//		this.restTemplate= builder
//							.rootUri(addressBaseUrl)
//							.build();
//	}
	
	public EmployeeResponse getEmployeeById(int id)
	{
		//AddressResponse -> set data by making a rest api call
		
		
		Employee employee = employeeDao.findById(id).get();
		
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		
		AddressResponse addressResponse = webClient
												.get()
												.uri("/address/"+id)
												.retrieve()
												.bodyToMono(AddressResponse.class)
												.block();
		
		employeeResponse.setAddressResponse(addressResponse);
		
		return employeeResponse;
	}

//	private AddressResponse callingAddressServiceUsingRESTTemplate(int id) {
//		// TODO Auto-generated method stub
//		return 	restTemplate.getForObject("/address/{id}", AddressResponse.class, id);
//	}
}
