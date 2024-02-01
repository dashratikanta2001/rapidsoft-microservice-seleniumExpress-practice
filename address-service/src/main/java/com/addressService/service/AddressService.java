package com.addressService.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addressService.dao.AddressDao;
import com.addressService.entity.Address;
import com.addressService.response.AddressResponse;

@Service
@Transactional
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

	public List<AddressResponse> getAllAddress() {
		// TODO Auto-generated method stub
		
		List<Address> allAddress = addressDao.findAll();
		List<AddressResponse> addressResponse = allAddress.stream().map(address-> modelMapper.map(address, AddressResponse.class)).collect(Collectors.toList());
		return addressResponse;
	}

	public AddressResponse addAddress(AddressResponse addressResponse) {
		// TODO Auto-generated method stub
		Address address = modelMapper.map(addressResponse, Address.class);
		Address savedAddress = addressDao.save(address);
		AddressResponse response = modelMapper.map(savedAddress, AddressResponse.class);
		
		return response;
	}

}
