package com.employeeService.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.employeeService.dao.EmployeeDao;
import com.employeeService.entity.Employee;
import com.employeeService.openfeignclients.AddressClient;
import com.employeeService.response.AddressResponse;
import com.employeeService.response.EmployeeResponse;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AddressClient addressClient;
		
	
	public EmployeeResponse getEmployeeById(int id)
	{
		//AddressResponse -> set data by making a rest api call
		
		
		Employee employee = employeeDao.findById(id).get();
		
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		try {
			ResponseEntity<AddressResponse> addressResponseEntity = addressClient.getAddressByEmployeeId(id);	
			AddressResponse addressResponse = addressResponseEntity.getBody();
			
			employeeResponse.setAddressResponse(addressResponse);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return employeeResponse;
	}
	
public List<EmployeeResponse> getAllEmployees() {
		List<Employee> employees = employeeDao.findAll();
		List<EmployeeResponse> employeeResponseList = employees.stream().map(emp -> modelMapper.map(emp, EmployeeResponse.class)).collect(Collectors.toList());
		
		//This is one approach.
//		employeeResponseList.forEach(emp ->{
//			emp.setAddressResponse(addressClient.getAddressByEmployeeId(emp.getId()).getBody());
//		});
		List<AddressResponse> addressResponse = addressClient.getAllAddress().getBody();
		
		employeeResponseList.forEach(employee->{
			for (AddressResponse addrResp : addressResponse) {
				if(addrResp.getId() == employee.getId())
				{
					employee.setAddressResponse(addrResp);
				}
			}
		});
		
		return employeeResponseList;
	}

//
//private AddressResponse callToAddressServiceUsingWebClient(int id) {
//	// TODO Auto-generated method stub
//	return webClient
//			.get()
//			.uri("/address/"+id)
//			.retrieve()
//			.bodyToMono(AddressResponse.class)
//			.block();
//}

	private AddressResponse callingAddressServiceUsingRESTTemplate(int id) {
		// Get me the details for the ip and a port number for address service.

		return 	restTemplate.getForObject("http://ADDRESS-SERVICE/address-app/api/address/{id}", AddressResponse.class, id);
	}

	public EmployeeResponse saveEmployee(EmployeeResponse employeeResponse) {
		// TODO Auto-generated method stub
		Employee employeeMap = modelMapper.map(employeeResponse, Employee.class);
		
		Employee employee = employeeDao.save(employeeMap);
		
		employeeResponse.getAddressResponse().setEmployeeId(employee.getId());
		
		System.out.println("Address's Emp  id = "+employeeResponse.getAddressResponse().getEmployeeId());
//		employeeResponse.setAddressResponse(employeeResponse.getAddressResponse());
		
		AddressResponse addressResponse = addressClient.addAddress(employeeResponse.getAddressResponse()).getBody();
		
		EmployeeResponse response = modelMapper.map(employee, EmployeeResponse.class);
		response.setAddressResponse(addressResponse);
		
		return response;
	}

	
}
