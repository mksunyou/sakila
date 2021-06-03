package com.gd.sakila.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gd.sakila.mapper.AddressMapper;

@Service
public class AddressService {
	@Autowired AddressMapper addressMapper;
	
	public String getPhoneByCustomer(String phone) {
		return addressMapper.selectPhoneByCustomer(phone);
	}
}
