package com.addressService.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addressService.dao.AddressDao;
import com.addressService.entity.Address;
import com.addressService.response.AddressResponse;

@Service
public class AddressService {
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private ModelMapper modelMapper;

	public AddressResponse getAddressByEmployeeId(int id) {
		// TODO Auto-generated method stub
		System.out.println("Finding address for employee "+ id);

		Address address = addressDao.findAddressByEmployeeId(id);
		AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);
		
		return addressResponse;
		
		
	}

}
