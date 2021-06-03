package com.gd.sakila.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.AddressService;

public class AddressRestapiController {
@Autowired AddressService addressService;
	
	@GetMapping("/getPhoneByCustomer")
	public String getPhoneByCustomer(@RequestParam(value = "phone", required=true) String phone) {
		return addressService.getPhoneByCustomer(phone);
	}
}
