package com.employeeService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeService.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{

}
