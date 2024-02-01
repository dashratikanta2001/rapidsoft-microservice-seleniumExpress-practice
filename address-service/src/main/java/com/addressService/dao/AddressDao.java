package com.addressService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.addressService.entity.Address;


public interface AddressDao extends JpaRepository<Address, Integer>{

	@Query(nativeQuery = true, value = "select ea.* from address ea join employee e on e.id = ea.employee_id where ea.employee_id=:employeeId")
	Address findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
