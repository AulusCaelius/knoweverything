package com.mashensoft.model;

import com.mashensoft.service.BusinessSocketService;

public class Mobile {
	private String phoneNumber;
	private String address;
	private String brand;

	public Mobile(String phoneNumber, String address, String brand) {
		super();
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.brand = brand;
	}

	public Mobile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		String content = "";
		content += "手机号码:" + phoneNumber + BusinessSocketService.enter;
		content += "品牌:" + brand + BusinessSocketService.enter;
		content += "归属地:" + address + BusinessSocketService.enter;
		return content;
	}

}
