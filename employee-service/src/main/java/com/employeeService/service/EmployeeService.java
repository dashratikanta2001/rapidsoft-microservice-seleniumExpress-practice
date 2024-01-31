package com.employeeService.service;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.employeeService.dao.EmployeeDao;
import com.employeeService.entity.Employee;
import com.employeeService.response.AddressResponse;
import com.employeeService.response.EmployeeResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WebClient webClient;
	
//	@Autowired
//	private DiscoveryClient discoveryClient;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	
	public EmployeeResponse getEmployeeById(int id)
	{
		//AddressResponse -> set data by making a rest api call
		
		
		Employee employee = employeeDao.findById(id).get();
		
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		
		AddressResponse addressResponse = callingAddressServiceUsingRESTTemplate(id);
				
				
		
		employeeResponse.setAddressResponse(addressResponse);
		
		return employeeResponse;
	}


private AddressResponse callToAddressServiceUsingWebClient(int id) {
	// TODO Auto-generated method stub
	return webClient
			.get()
			.uri("/address/"+id)
			.retrieve()
			.bodyToMono(AddressResponse.class)
			.block();
}

	private AddressResponse callingAddressServiceUsingRESTTemplate(int id) {
		// Get me the details for the ip and a port number for address service.
//		List<ServiceInstance> instances = discoveryClient.getInstances("ADDRESS-SERVICE");
//		
//		ServiceInstance serviceInstance = instances.get(0);
//		String uri = serviceInstance.getUri().toString();
//		System.out.println("Uri = "+uri);
		
		ServiceInstance serviceInstance = loadBalancerClient.choose("ADDRESS-SERVICE");
		String uri = serviceInstance.getUri().toString();
		String contextPath = serviceInstance.getMetadata().get("configPath");
		System.out.println(serviceInstance.getMetadata().get("user"));
		System.out.println(serviceInstance.getMetadata().get("password"));
		
		
		System.out.println("URI ---------->"+uri+contextPath);
		
		return 	restTemplate.getForObject(uri+contextPath+"/address/{id}", AddressResponse.class, id);
	}
}
