package com.addressService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "lane1")
	private String lane1;

	@Column(name = "lane2")
	private String lane2;

	@Column(name = "zip")
	private String zip;

	@Column(name = "state")
	private String state;
	
	@Column(name = "employee_id")
	private int employeeId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLane1() {
		return lane1;
	}

	public void setLane1(String lane1) {
		this.lane1 = lane1;
	}

	public String getLane2() {
		return lane2;
	}

	public void setLane2(String lane2) {
		this.lane2 = lane2;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Address(int id, String lane1, String lane2, String zip, String state, int employeeId) {
		super();
		this.id = id;
		this.lane1 = lane1;
		this.lane2 = lane2;
		this.zip = zip;
		this.state = state;
		this.employeeId = employeeId;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

}
