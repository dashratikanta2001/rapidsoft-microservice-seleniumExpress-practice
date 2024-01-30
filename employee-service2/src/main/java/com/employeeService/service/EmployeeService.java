package com.employeeService.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.employeeService.dao.EmployeeDao;
import com.employeeService.entity.Employee;
import com.employeeService.feignclient.AddressClient;
import com.employeeService.response.AddressResponse;
import com.employeeService.response.EmployeeResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	AddressClient addressClient;

	
	public EmployeeResponse getEmployeeById(int id)
	{
		//AddressResponse -> set data by making a rest api call
		
		
		Employee employee = employeeDao.findById(id).get();
		
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		
		AddressResponse addressResponse = addressClient.getAddressByEmployeeId(id);
		
		employeeResponse.setAddressResponse(addressResponse);
		
		return employeeResponse;
	}

}
